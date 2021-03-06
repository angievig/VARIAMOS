package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

//import com.cfm.common.AbstractModel;
//import com.cfm.productline.io.SXFMReader;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.DefaultFileFilter;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;

import fm.FeatureModelException;

@SuppressWarnings("serial")
public class OpenAction extends AbstractEditorAction {
	/**
	 * 
	 */
	protected String lastDir;

	/**
	 * 
	 */
	protected void resetEditor(VariamosGraphEditor editor) {
		editor.setVisibleModel(0, -1);
		editor.setDefaultButton();
		editor.updateView();
		editor.setModified(false);
		editor.getUndoManager().clear();
		editor.getGraphComponent().zoomAndCenter();
	}

	@Deprecated
	protected void openSXFM(BasicGraphEditor editor, File file)
			throws IOException, FeatureModelException {

		VariamosGraphEditor variamosEditor = (VariamosGraphEditor) editor;
		variamosEditor.editModelReset();

		// SXFMReader reader = new SXFMReader();
		// AbstractModel pl = reader.readFile(file.getAbsolutePath());

		// variamosEditor.editModel(pl);

		editor.setCurrentFile(file);
		resetEditor(variamosEditor);
	}

	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		BasicGraphEditor editor = getEditor(e);

		if (editor != null) {
			VariamosGraphEditor variamosEditor = (VariamosGraphEditor) editor;
			if (variamosEditor.getPerspective() == 4) {
				JOptionPane.showMessageDialog(editor,
						mxResources.get("saveloadnewerror"),
						"Operation not supported",
						JOptionPane.INFORMATION_MESSAGE, null);

				return;
			}
			final BasicGraphEditor finalEditor = editor;
			((MainFrame) editor.getFrame()).waitingCursor(true);
			if (!editor.isModified()
					|| JOptionPane.showConfirmDialog(editor,
							mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
				mxGraph graph = editor.getGraphComponent().getGraph();

				if (graph != null) {
					String wd = (lastDir != null) ? lastDir : System
							.getProperty("user.dir");

					JFileChooser fc = new JFileChooser(wd);

					final String fileExtension = finalEditor.getFileExtension();
					final String fileExtensionName = finalEditor
							.getExtensionName();

					// Adds file filter for supported file format
					DefaultFileFilter defaultFilter = new DefaultFileFilter("."
							+ fileExtension, fileExtensionName + " (."
							+ fileExtension + ")") {

						public boolean accept(File file) {
							String lcase = file.getName().toLowerCase();

							((MainFrame) finalEditor.getFrame())
									.waitingCursor(false);
							return super.accept(file)
									|| lcase.endsWith("." + fileExtension);
						}
					};
					// fc.addChoosableFileFilter(defaultFilter);

					// fc.addChoosableFileFilter(new DefaultFileFilter(".sxfm",
					// mxResources.get("sxfmExtension") + " (.sxfm)"));

					fc.setFileFilter(defaultFilter);

					int rc = fc.showDialog(null, mxResources.get("openFile"));

					if (rc == JFileChooser.APPROVE_OPTION) {
						lastDir = fc.getSelectedFile().getParent();

						try {
							if (fc.getSelectedFile().getAbsolutePath()
									.toLowerCase().endsWith(".sxfm")) {
								openSXFM(editor, fc.getSelectedFile());
							}
							// else if (fc.getSelectedFile().getAbsolutePath()
							// .toLowerCase().endsWith(".txt"))
							// {
							// openGD(editor, fc.getSelectedFile(),
							// mxUtils.readFile(fc
							// .getSelectedFile()
							// .getAbsolutePath()));
							// }
							else {
								// Document document = mxXmlUtils
								// .parseXml(mxUtils.readFile(fc
								// .getSelectedFile()
								// .getAbsolutePath()));
								//
								// mxCodec codec = new mxCodec(document);
								// codec.decode(
								// document.getDocumentElement(),
								// graph.getModel());
								// variamosEditor.editModelReset();

								FileTasks.openAction(FileTasks.OPEN,
										fc.getSelectedFile(),
										(VariamosGraphEditor) editor, graph);

								/*
								 * ((VariamosGraphEditor) editor).resetView();
								 * graph =
								 * editor.getGraphComponent().getGraph();
								 * SharedActions.beforeLoadGraph(graph,
								 * variamosEditor);
								 * 
								 * PLGReader.loadPLG(fc.getSelectedFile(),
								 * graph, variamosEditor);
								 * editor.setCurrentFile(fc.getSelectedFile());
								 * SharedActions.afterOpenCloneGraph(graph,
								 * variamosEditor); variamosEditor
								 * .populateIndex(((AbstractGraph) graph)
								 * .getProductLine());
								 * resetEditor(variamosEditor);
								 */
							}
						} catch (IOException | FeatureModelException ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(
									editor.getGraphComponent(), ex.toString(),
									mxResources.get("error"),
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			variamosEditor.refresh();
			((MainFrame) editor.getFrame()).waitingCursor(false);
		}
	}
}

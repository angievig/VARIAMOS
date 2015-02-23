package com.variamos.gui.perspeditor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.actions.ClearConfigurationAction;
import com.variamos.gui.perspeditor.actions.ClearSimulationAction;
import com.variamos.gui.perspeditor.actions.ClearVerificationAction;
import com.variamos.gui.perspeditor.actions.ExitAction;
import com.variamos.gui.perspeditor.actions.HideAdvancedPerspectiveAction;
import com.variamos.gui.perspeditor.actions.HideSimulationDashBoardAction;
import com.variamos.gui.perspeditor.actions.NewAction;
import com.variamos.gui.perspeditor.actions.NextSimulationAction;
import com.variamos.gui.perspeditor.actions.OpenAction;
import com.variamos.gui.perspeditor.actions.ParentElementAction;
import com.variamos.gui.perspeditor.actions.RootElementAction;
import com.variamos.gui.perspeditor.actions.SaveAction;
import com.variamos.gui.perspeditor.actions.ShowAdvancedPerspectiveAction;
import com.variamos.gui.perspeditor.actions.ShowSimulationDashBoardAction;
import com.variamos.gui.perspeditor.actions.StartSimulationAction;
import com.variamos.gui.perspeditor.actions.ToggleAssetVisibilityAction;
import com.variamos.gui.perspeditor.actions.TogglePLVisibilityAction;
import com.variamos.gui.perspeditor.actions.VerificationAction;
import com.variamos.gui.perspeditor.actions.VerifyDeadElementAction;
import com.variamos.gui.perspeditor.actions.VerifyFalseOptElementAction;

@SuppressWarnings("serial")
public class PerspEditorMenuBar extends JMenuBar {

	VariamosGraphEditor editor;

	public PerspEditorMenuBar(BasicGraphEditor basicGraphEditor) {
		init(basicGraphEditor);
	}

	private void init(BasicGraphEditor editor) {
		JMenu menu = new JMenu("File");
		menu.add(editor.bind(mxResources.get("new"), new NewAction()));
		menu.add(editor.bind(mxResources.get("load"), new OpenAction()));
		menu.addSeparator();
		menu.add(editor.bind(mxResources.get("save"), new SaveAction(false)));
		menu.add(editor.bind(mxResources.get("saveAs"), new SaveAction(true)));
		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("exit"), new ExitAction()));

		add(menu);

		menu = (JMenu) menu.add(new JMenu(mxResources.get("layout")));

		menu.add(editor
				.bind("Toggle Assets", new ToggleAssetVisibilityAction()));
		menu.add(editor.bind("Toggle Variability Elements",
				new TogglePLVisibilityAction()));
		menu.addSeparator();

		menu.add(editor.graphLayout("verticalHierarchical", true));
		menu.add(editor.graphLayout("horizontalHierarchical", true));

		menu.addSeparator();

		// menu.add(editor.graphLayout("verticalPartition", false));
		// menu.add(editor.graphLayout("horizontalPartition", false));
		//
		// menu.addSeparator();

		menu.add(editor.graphLayout("verticalStack", true));
		menu.add(editor.graphLayout("horizontalStack", true));

		menu.addSeparator();

		menu.add(editor.graphLayout("verticalTree", true));
		menu.add(editor.graphLayout("horizontalTree", true));

		menu.addSeparator();

		menu.add(editor.graphLayout("placeEdgeLabels", true));
		menu.add(editor.graphLayout("parallelEdges", true));

		menu.addSeparator();

		menu.add(editor.graphLayout("organicLayout", true));
		menu.add(editor.graphLayout("circleLayout", true));
		add(menu);
		final VariamosGraphEditor finalEditor = (VariamosGraphEditor) editor;

		if (editor.getPerspective() == 2) {
			menu = (JMenu) menu
					.add(new JMenu(mxResources.get("verifyDefects")));
			// menu.add(editor.bind(mxResources.get("verifyVoidModel"), new
			// VerifyVoidModelAction()));
			// menu.add(editor.bind(mxResources.get("verifyFalseProductLine"),
			// new
			// VerifyFalseProductLineModelAction()));
			menu.add(editor.bind(mxResources.get("verifyRoot"),
					new RootElementAction()));
			menu.add(editor.bind(mxResources.get("verifyParents"),
					new ParentElementAction()));
			menu.add(editor.bind(mxResources.get("verifyDeadElement"),
					new VerifyDeadElementAction()));
			menu.add(editor.bind(
					mxResources.get("verifyFalseOptionalElements"),
					new VerifyFalseOptElementAction()));
			menu.addSeparator();
			menu.add(editor.bind(mxResources.get("clearElements"),
					new ClearVerificationAction()));
			add(menu);

			menu = (JMenu) menu.add(new JMenu(mxResources
					.get("verifyDefectsOptions")));
			JCheckBoxMenuItem item = new JCheckBoxMenuItem(
					mxResources.get("verifyRoot"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();
					if (selected)
						finalEditor.updateDefects("Root", true);
					else
						finalEditor.updateDefects("Root", false);
				}
			});
			menu.add(item);

			item = new JCheckBoxMenuItem(mxResources.get("verifyParents"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();
					if (arg0.getSource() instanceof Component) {

						if (selected)
							finalEditor.updateDefects("Parent", true);
						else
							finalEditor.updateDefects("Parent", false);
					}
				}
			});
			menu.add(item);

			item = new JCheckBoxMenuItem(mxResources.get("verifyDeadElement"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();

					if (selected)
						finalEditor.updateDefects("Dead", true);
					else
						finalEditor.updateDefects("Dead", false);
				}
			});
			menu.add(item);

			item = new JCheckBoxMenuItem(
					mxResources.get("verifyFalseOptionalElements"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();

					if (selected)
						finalEditor.updateDefects("FalseOpt", true);
					else
						finalEditor.updateDefects("FalseOpt", false);
				}
			});
			menu.add(item);
			menu.addSeparator();
			menu.add(editor.bind(mxResources.get("clearElements"),
					new ClearVerificationAction()));

			menu.add(editor.bind(mxResources.get("verifyElements"),
					new VerificationAction()));
			add(menu);
		}
		if (editor.getPerspective() == 4) {

			menu = (JMenu) menu
					.add(new JMenu(mxResources.get("configuration")));
			Action a = editor.bind(mxResources.get("resetConfiguration"),
					new ClearConfigurationAction());
			menu.add(a);
			menu.addSeparator();
			a = editor.bind(mxResources.get("loadConfiguration"),
					new ClearSimulationAction());
			a.setEnabled(false);
			menu.add(a);
			a = editor.bind(mxResources.get("saveConfiguration"),
					new ClearSimulationAction());
			menu.add(a);
			a.setEnabled(false);
			a = editor.bind(mxResources.get("saveProducts"),
					new ClearSimulationAction());
			menu.add(a);
			a.setEnabled(false);
			add(menu);

			menu = (JMenu) menu.add(new JMenu(mxResources.get("simulation")));
			menu.add(editor.bind(mxResources.get("resetSimulation"),
					new ClearSimulationAction()));
			menu.add(editor.bind(mxResources.get("startSimulation"),
					new StartSimulationAction()));
			menu.add(editor.bind(mxResources.get("nextSimulation"),
					new NextSimulationAction()));
			menu.addSeparator();
			menu.add(editor.bind(mxResources.get("showSimulationDashBoard"),
					new ShowSimulationDashBoardAction()));
			menu.add(editor.bind(mxResources.get("hideSimulationDashBoard"),
					new HideSimulationDashBoardAction()));
			JCheckBoxMenuItem item = new JCheckBoxMenuItem(
					mxResources.get("nameSimulationDashBoard"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();

					if (selected)
						{
						finalEditor.showNames(true);
						finalEditor.updateDashBoard(false, false);
						}
					else
					{
						finalEditor.showNames(false);
						finalEditor.updateDashBoard(false, false);
					}
				}
			});
			menu.add(item);
			add(menu);
		}
		menu = (JMenu) menu.add(new JMenu(mxResources.get("window")));
		menu.add(editor.bind(mxResources.get("showAdvancedPerspectives"),
				new ShowAdvancedPerspectiveAction()));
		menu.add(editor.bind(mxResources.get("hideAdvancedPerspectives"),
				new HideAdvancedPerspectiveAction()));
		menu.addSeparator();

		add(menu);

	}

}
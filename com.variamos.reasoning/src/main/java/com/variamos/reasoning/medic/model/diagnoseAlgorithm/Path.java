package com.variamos.reasoning.medic.model.diagnoseAlgorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.variamos.hlcl.core.HlclProgram;
import com.variamos.reasoning.medic.model.graph.ConstraintGraphHLCL;
import com.variamos.reasoning.medic.model.graph.VertexHLCL;



public class Path {

	private ConstraintGraphHLCL fullGraph;
	private ConstraintGraphHLCL projectionGraph;


	private LinkedList <VertexHLCL> path;
	private  HlclProgram subProblem;
	

	


	public Path(){
		
	}
	public Path(ConstraintGraphHLCL inNet, LinkedList <VertexHLCL> inPath){
		fullGraph= inNet;
		path= inPath;
	}
	
	public Path(ConstraintGraphHLCL inNet, LinkedList <VertexHLCL> inPath, HlclProgram sub){
		fullGraph= inNet;
		path= inPath;
		subProblem= sub;
	}
	
	public HlclProgram getSubProblem() {
		return subProblem;
	}

	public void setSubProblem(HlclProgram subProblem) {
		this.subProblem = subProblem;
	}
	
	public ConstraintGraphHLCL getFullGraph() {
		return fullGraph;
	}

	public void setFullGraph(ConstraintGraphHLCL full) {
		fullGraph = full;
	}

	public LinkedList<VertexHLCL> getPath() {
		return path;
	}

	public void setPath(LinkedList<VertexHLCL> path) {
		this.path = path;
	}
	
	public ConstraintGraphHLCL projectPath() throws Exception{
		//se crea el nuevo grafo
		projectionGraph= new ConstraintGraphHLCL();
		
		VertexHLCL previous=null;
		VertexHLCL clon=null;
		//se recorre el camino uno a uno 
		for (VertexHLCL vertex : path) {
			clon= vertex.clone();
			//se agrega el vertice en el grafo 
			projectionGraph.addVertex(clon);
			
			// el nodo anterior es null cuando vertex es el primero del camino
			// solo en ese caso no se agrega una arista.
			if(previous!=null){
				projectionGraph.addEdge(clon, previous);
			}
			// se obtiene el vertice anterior (debe ser el padre del actual)
			previous= clon;	
		}
		return projectionGraph;
	}
	
	public ConstraintGraphHLCL getProjectionGraph() {
		return projectionGraph;
	}
	public void setProjectionGraph(ConstraintGraphHLCL projectionGraph) {
		this.projectionGraph = projectionGraph;
		
	}
	
//	public List<String> getIdsFromPath(){
//		LinkedList<String> ids = new LinkedList<String>();
//		for (V v : path) {
//			ids.addLast(v);
//			
//		}
//		
//		return ids;
//		
//	}


}

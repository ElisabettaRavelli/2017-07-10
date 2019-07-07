package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private ArtsmiaDAO dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private List<ArcoPeso> arcopeso;
	private Map<Integer, ArtObject> idMpa;
	
	public Model() {
		this.dao = new ArtsmiaDAO();
		this.arcopeso = new ArrayList<>();
		this.idMpa = new HashMap<>();
		this.dao.listObjects(this.idMpa);
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.arcopeso = this.dao.getConnessioni();
		for(ArcoPeso ap: this.arcopeso) {
			Graphs.addEdgeWithVertices(this.grafo, ap.getO1(), ap.getO2(), ap.getPeso());
			System.out.println("Arco aggiunto: "+ ap.getO1()+" -> "+ ap.getO2());
		}
		
	}
	public int getVertici() {
		return this.grafo.vertexSet().size();
	}
	public int getArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public boolean idValido(Integer idInserito) {
		if(this.idMpa.containsKey(idInserito)) {
			return true;
		}
		return false;
	}
	
	public int componenteConnessa(Integer source){
		Integer start = null;
		for(Integer i: this.grafo.vertexSet()) {
			if(i == source) {
				start = i;
			}
		}
		
		List<Integer> result = new ArrayList<Integer>();
		GraphIterator<Integer, DefaultWeightedEdge> it = new BreadthFirstIterator<>(this.grafo, start);
		while(it.hasNext()) {
			result.add(it.next());
		}
		return result.size();
	}

}

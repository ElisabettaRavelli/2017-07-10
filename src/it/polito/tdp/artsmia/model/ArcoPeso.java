package it.polito.tdp.artsmia.model;

public class ArcoPeso {
	
	private Integer o1;
	private Integer o2;
	private Double peso;
	public ArcoPeso(Integer o1, Integer o2, Double peso) {
		super();
		this.o1 = o1;
		this.o2 = o2;
		this.peso = peso;
	}
	public Integer getO1() {
		return o1;
	}
	public Integer getO2() {
		return o2;
	}
	public Double getPeso() {
		return peso;
	}
	

}

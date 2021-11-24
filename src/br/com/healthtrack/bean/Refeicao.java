package br.com.healthtrack.bean;

import java.util.Calendar;

public class Refeicao {

	private int idRefeicao;
	private String refeicao;
	private Calendar data;
	private double calorias;
	private CategoriaRefeicao categoriaRefeicao;
	private Usuario usuario;

	public Refeicao() {}

	public Refeicao(int idRefeicao, String refeicao, double calorias, Calendar data) {
		super();
		this.idRefeicao = idRefeicao;
		this.refeicao = refeicao;
		this.calorias = calorias;
		this.data = data;
	}


	public int getIdRefeicao() {
		return idRefeicao;
	}

	public void setIdRefeicao(int idRefeicao) {
		this.idRefeicao = idRefeicao;
	}

	
	public String getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(String refeicao) {
		this.refeicao = refeicao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	
	public CategoriaRefeicao getCategoriaRefeicao() {
		return categoriaRefeicao;
	}

	public void setIdCategoriaRefeicao(CategoriaRefeicao categoriaRefeicao) {
		this.categoriaRefeicao = categoriaRefeicao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	
	
}
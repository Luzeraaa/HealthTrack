package br.com.healthtrack.bean;

import java.util.Calendar;


public class Pressao {
	
	private int idPressao;
	private Usuario idUsuario;	
	private int diastolica;
	private int sistolica;
	private Calendar data;
	
	public Pressao() {}

	public Pressao(int idPressao, int diastolica, int sistolica, Calendar data) {
		super();
		this.idPressao = idPressao;
		this.diastolica = diastolica;
		this.sistolica = sistolica;
		this.data = data;
	}

	public Usuario getUsuario() {
		return idUsuario;
	}

	public void setUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPressao() {
		return idPressao;
	}

	public void setIdPressao(int idPressao) {
		this.idPressao = idPressao;
	}

	public int getDiastolica() {
		return diastolica;
	}

	public void setDiastolica(int diastolica) {
		this.diastolica = diastolica;
	}

	public int getSistolica() {
		return sistolica;
	}

	public void setSistolica(int sistolica) {
		this.sistolica = sistolica;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

}
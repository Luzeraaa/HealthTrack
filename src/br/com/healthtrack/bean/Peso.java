package br.com.healthtrack.bean;

import java.util.Calendar;

public class Peso {
	
	private int idPeso;
	private Usuario usuario;	
	private double pesoInicial;
	private double pesoAtual;
	private Calendar data;
	
	public Peso() {}
	
	public Peso(int idPeso, double pesoInicial, Calendar data) {
		super();
		this.pesoInicial = pesoInicial;
		this.data = data;
	}
	


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getIdPeso() {
		return idPeso;
	}
	
	public void setIdPeso(int idPeso) {
		this.idPeso = idPeso;
	}
	
	public double getPesoInicial() {
		return pesoInicial;
	}
	
	public void setPesoInicial(double pesoInicial) {
		this.pesoInicial = pesoInicial;
	}
	
	public double getPesoAtual() {
		return pesoAtual;
	}

	public void setPesoAtual(double pesoAtual) {
		this.pesoAtual = pesoAtual;
	}

	public Calendar getData() {
		return data;
	}
	
	public void setData(Calendar data) {
		this.data = data;
	}

}

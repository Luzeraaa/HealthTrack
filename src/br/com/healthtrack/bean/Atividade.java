package br.com.healthtrack.bean;

import java.util.Calendar;
public class Atividade {
	
	private int idAtividade;
	private Usuario usuario;	
	private int duracao;
	private CategoriaAtividade categoria;
	private String categoriaPraticada;
	private double totalCalorias;
	private Calendar data;
	private String nivel;
	private double distancia;
	private double distanciaPercorrida;
	
	
	public Atividade() {}

	public Atividade(int idAtividade , int duracao, double totalCalorias, Calendar data,
			String nivel, double distancia) {
		super();
		this.idAtividade = idAtividade;
		this.duracao = duracao;
		this.totalCalorias = totalCalorias;
		this.data = data;
		this.nivel = nivel;
		this.distancia = distancia;
	}

	
	
	public String getCategoriaPraticada() {
		return categoriaPraticada;
	}

	public void setCategoriaPraticada(String categoriaPraticada) {
		this.categoriaPraticada = categoriaPraticada;
	}

	public double getDistanciaPercorrida() {
		return distanciaPercorrida;
	}

	public void setDistanciaPercorrida(double distanciaPercorrida) {
		this.distanciaPercorrida = distanciaPercorrida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario idUsuario) {
		this.usuario = idUsuario;
	}

	public int getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(int idAtividade) {
		this.idAtividade = idAtividade;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public CategoriaAtividade getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaAtividade categoria) {
		this.categoria = categoria;
	}

	public double getTotalCalorias() {
		return totalCalorias;
	}

	public void setTotalCalorias(double totalCalorias) {
		this.totalCalorias = totalCalorias;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	
}

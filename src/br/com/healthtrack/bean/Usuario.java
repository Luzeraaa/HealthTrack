package br.com.healthtrack.bean; //TODO IMPLEMENTAR CRIPTOGRAFIA DE SENHA!

import java.io.Serializable;
import java.util.Calendar;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int idUsuario;
	private String nome;
	private String sexo;
	private double altura;
	private String email;
	private String senha;
	private Calendar dataNascimento;

	
	public Usuario() {}

	public Usuario(int idUsuario, String nome, String sexo,String email, String senha, Calendar dataNascimento,double altura) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.sexo = sexo;
		this.altura = altura;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nome=" + nome + ", sexo=" + sexo + ", altura=" + altura
				+ ", email=" + email + ", senha=" + senha + ", dataNascimento=" + dataNascimento + "]";
	}

	
	
}
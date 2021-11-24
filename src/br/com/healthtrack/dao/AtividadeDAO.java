package br.com.healthtrack.dao;

import br.com.healthtrack.bean.Atividade;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.exception.DBException;

import java.util.List;

public interface AtividadeDAO{

	void cadastrar(Atividade atividade) throws DBException;
	void atualizar(Atividade atividade) throws DBException;
	void remover(int id) throws DBException;
	Atividade buscar(int id);
	List<Atividade> listar(int usuario);
	double listarCaloriasGastas();
	double getDistanciaPercorrida();
	String getCategoriaPraticada();
}
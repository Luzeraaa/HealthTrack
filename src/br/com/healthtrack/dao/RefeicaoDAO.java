package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.Refeicao;
import br.com.healthtrack.exception.DBException;

public interface RefeicaoDAO {

	void cadastrar(Refeicao refeicao) throws DBException;
	void atualizar(Refeicao refeicao) throws DBException;
	void remover(int id) throws DBException;
	Refeicao buscar(int id);
	List<Refeicao> listar(int idUsuario);
	double listarCaloriasIngeridas ();
	
}

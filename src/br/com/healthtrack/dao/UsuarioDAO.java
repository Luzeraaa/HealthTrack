package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.exception.DBException;

public interface UsuarioDAO {
	
	void cadastrar(Usuario usuario) throws DBException;
	void atualizar(Usuario usuario) throws DBException;
	void remover(int id) throws DBException;
	Usuario buscar(int id);
	List<Usuario> listar();

}

package br.com.healthtrack.factory;
import br.com.healthtrack.dao.impl.*;
import br.com.healthtrack.dao.*;

public class DAOFactory {
	
	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}
	
	public static RefeicaoDAO getRefeicaoDAO() {
		return new OracleRefeicaoDAO();
	}
	
	public static CategoriaRefeicaoDAO getCategoriaRefeicaoDAO() {
		return new OracleCategoriaRefeicaoDAO();
	}
	
	public static AtividadeDAO getAtividadeDAO() {
		return new OracleAtividadeDAO();
	}
	
	public static CategoriaAtividadeDAO getCategoriaDAO() {
		return new OracleCategoriaDAO();
	}
	
	
	public static PressaoDAO getPressaoDAO() {
		return new OraclePressaoDAO();
	}
	
	public static PesoDAO getPesoDAO() {
		return new OraclePesoDAO();
	}
	
	
	
}

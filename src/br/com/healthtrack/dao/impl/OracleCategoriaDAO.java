package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.healthtrack.bean.CategoriaAtividade;
import br.com.healthtrack.dao.CategoriaAtividadeDAO;
import br.com.healthtrack.singleton.ConnectionManager;

public class OracleCategoriaDAO implements CategoriaAtividadeDAO {
	
	private Connection conexao;



	@Override
	public List<CategoriaAtividade> listar() {
		
		CategoriaAtividade categoriaAtividade = null;
		List<CategoriaAtividade> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("SELECT * FROM T_CAT_ATIVIDADE");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idCategoria = rs.getInt("ID_CATEGORIA");
				String categoria = rs.getString("DS_CATEGORIA");
				
				categoriaAtividade = new CategoriaAtividade(idCategoria, categoria);
				
				lista.add(categoriaAtividade);
			}			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

}

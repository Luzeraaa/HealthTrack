package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.healthtrack.bean.CategoriaRefeicao;
import br.com.healthtrack.dao.CategoriaRefeicaoDAO;
import br.com.healthtrack.singleton.ConnectionManager;

public class OracleCategoriaRefeicaoDAO implements CategoriaRefeicaoDAO {
	
	private Connection conexao;



	@Override
	public List<CategoriaRefeicao> listar() {
		
		
		List<CategoriaRefeicao> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("SELECT * FROM T_CAT_REFEICAO");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idCategoria = rs.getInt("ID_CATEGORIA");
				String categoria = rs.getString("DS_CATEGORIA");
				
				CategoriaRefeicao categoriaRefeicao = new CategoriaRefeicao(idCategoria, categoria);
				
				lista.add(categoriaRefeicao);
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
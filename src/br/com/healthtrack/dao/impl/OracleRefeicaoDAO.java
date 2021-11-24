package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.healthtrack.bean.CategoriaRefeicao;
import br.com.healthtrack.bean.Refeicao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.RefeicaoDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.ConnectionManager;

public class OracleRefeicaoDAO implements RefeicaoDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Refeicao refeicao) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_REFEICAO (ID_REFEICAO, DT_REFEICAO, T_CAT_REFEICAO_ID_CATEGORIA, T_USUARIO_ID_USUARIO, NR_CALORIAS, DS_REFEICAO)"
					+ " VALUES (SQ_T_REFEICAO.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);

			java.sql.Date data = new java.sql.Date(refeicao.getData().getTimeInMillis());
			stmt.setDate(1, data);
			stmt.setInt(2, refeicao.getCategoriaRefeicao().getIdCategoria());
			stmt.setInt(3, refeicao.getUsuario().getIdUsuario());
			stmt.setDouble(4, refeicao.getCalorias());
			stmt.setString(5, refeicao.getRefeicao());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Refeicao refeicao) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_REFEICAO SET DT_REFEICAO = ?, T_CAT_REFEICAO_ID_CATEGORIA = ?, T_USUARIO_ID_USUARIO = ?, NR_CALORIAS = ? WHERE ID_REFEICAO = ?";
			stmt = conexao.prepareStatement(sql);

			java.sql.Date data = new java.sql.Date(refeicao.getData().getTimeInMillis());
			stmt.setDate(1, data);
			stmt.setInt(2, refeicao.getCategoriaRefeicao().getIdCategoria());
			stmt.setInt(3, refeicao.getUsuario().getIdUsuario());
			stmt.setDouble(4, refeicao.getCalorias());
			stmt.setInt(5, refeicao.getIdRefeicao());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remover(int codigo) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "DELETE FROM T_REFEICAO WHERE ID_REFEICAO = ?";
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, codigo);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao deletar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Refeicao buscar(int id) {

		CategoriaRefeicao categoriaRefeicao = null;
		Refeicao refeicao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement("SELECT * FROM T_REFEICAO INNER JOIN T_CAT_REFEICAO ON "
					+ "(T_REFEICAO.T_CAT_REFEICAO_ID_CATEGORIA = T_CAT_REFEICAO.ID_CATEGORIA) WHERE ID_REFEICAO = ?");

			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int idRefeicao = rs.getInt("ID_REFEICAO");
				
				java.sql.Date data = rs.getDate("DT_REFEICAO");
				Calendar dataRefeicao = Calendar.getInstance();
				dataRefeicao.setTimeInMillis(data.getTime());
				
				double calorias = rs.getDouble("NR_CALORIAS");
				String refeicaoFeita = rs.getString("DS_REFEICAO");
				
				int idCategoria = rs.getInt("ID_CATEGORIA");
				String categoria = rs.getString("DS_CATEGORIA");		

				int idUsuario = rs.getInt("ID_USUARIO");
				String nome = rs.getString("NM_USUARIO");
				String sexo = rs.getString("SEX_USUARIO");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				java.sql.Date dataNasc = rs.getDate("DT_NASCIMENTO");
				Calendar nascimento = Calendar.getInstance();
				nascimento.setTimeInMillis(dataNasc.getTime());
				float altura = rs.getFloat("NR_ALTURA");

				Usuario usuario = new Usuario(idUsuario, nome, sexo, email, senha, nascimento, altura);

				categoriaRefeicao = new CategoriaRefeicao(idCategoria, categoria);

				refeicao = new Refeicao(idRefeicao, refeicaoFeita, calorias, dataRefeicao);
				refeicao.setIdCategoriaRefeicao(categoriaRefeicao);
				refeicao.setUsuario(usuario);
			}
		} catch (SQLException e) {
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
		return refeicao;
	}

	@Override
	public List<Refeicao> listar(int idUsuario) {

		CategoriaRefeicao categoriaRefeicao = null;
		Refeicao refeicao = null;
		List<Refeicao> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement(
					"SELECT * FROM T_REFEICAO INNER JOIN T_CAT_REFEICAO ON (T_REFEICAO.T_CAT_REFEICAO_ID_CATEGORIA = T_CAT_REFEICAO.ID_CATEGORIA) WHERE ID_REFEICAO = ?");
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idRefeicao = rs.getInt("ID_REFEICAO");
				
				java.sql.Date data = rs.getDate("DT_REFEICAO");
				Calendar dataRefeicao = Calendar.getInstance();
				dataRefeicao.setTimeInMillis(data.getTime());
				
				double calorias = rs.getDouble("NR_CALORIAS");
				String refeicaoFeita = rs.getString("DS_REFEICAO");
				
				int idCategoria = rs.getInt("ID_CATEGORIA");
				String categoriaRef = rs.getString("DS_CATEGORIA");
				
				
				int codigoUsuario = rs.getInt("ID_USUARIO");
				String nome = rs.getString("NM_USUARIO");
				String sexo = rs.getString("SEX_USUARIO");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				java.sql.Date dataNasc = rs.getDate("DT_NASCIMENTO");
				Calendar nascimento = Calendar.getInstance();
				nascimento.setTimeInMillis(dataNasc.getTime());
				float altura = rs.getFloat("NR_ALTURA");
				
				Usuario usuario = new Usuario(codigoUsuario, nome, sexo, email, senha, nascimento, altura);

				categoriaRefeicao = new CategoriaRefeicao(idCategoria, categoriaRef);

				refeicao = new Refeicao(idRefeicao, refeicaoFeita,calorias, dataRefeicao);
				refeicao.setIdCategoriaRefeicao(categoriaRefeicao);
				refeicao.setUsuario(usuario);
				lista.add(refeicao);
			}
		} catch (SQLException e) {
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

	
	
	
	@Override
	public double listarCaloriasIngeridas() {

		Refeicao refeicao = new Refeicao();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		 try {
			 
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement("SELECT SUM(NR_CALORIAS) FROM T_REFEICAO WHERE T_USUARIO_ID_USUARIO = 1");
			rs = stmt.executeQuery();

			while(rs.next()) {
				int totalCalorias = rs.getInt("SUM(NR_CALORIAS)");
				refeicao.setCalorias(totalCalorias);
				
			}

		
		} catch (SQLException e) {
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
		return refeicao.getCalorias();
	}
	

}
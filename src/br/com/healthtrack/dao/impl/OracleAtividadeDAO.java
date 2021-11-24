package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Atividade;
import br.com.healthtrack.bean.CategoriaAtividade;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AtividadeDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.ConnectionManager;

public class OracleAtividadeDAO implements AtividadeDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Atividade atividade) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_ATIVIDADE (ID_ATIVIDADE, TP_DURACAO, NR_CALORIAS_TOTAL, DT_ATIVIDADE, "
					+ "T_CAT_ATIVIDADE_ID_CATEGORIA, T_USUARIO_ID_USUARIO, NR_NIVEL, NR_DISTANCIA) VALUES (SQ_T_ATIVIDADE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, atividade.getDuracao());
			stmt.setDouble(2, atividade.getTotalCalorias());
			java.sql.Date data = new java.sql.Date(atividade.getData().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setInt(4, atividade.getCategoria().getIdCategoria());
			stmt.setInt(5, atividade.getUsuario().getIdUsuario());
			stmt.setString(6, atividade.getNivel());
			stmt.setDouble(7, atividade.getDistancia());
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
	public void atualizar(Atividade atividade) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_ATIVIDADE SET TP_DURACAO = ?, NR_CALORIAS_TOTAL = ?, DT_ATIVIDADE = ?, T_CAT_ATIVIDADE_ID_CATEGORIA = ?,"
					+ " T_USUARIO_ID_USUARIO = ?, NR_NIVEL = ?, NR_DISTANCIA = ? WHERE ID_ATIVIDADE = ?";
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, atividade.getDuracao());
			stmt.setDouble(2, atividade.getTotalCalorias());
			java.sql.Date data = new java.sql.Date(atividade.getData().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setInt(4, atividade.getCategoria().getIdCategoria());
			stmt.setInt(5, atividade.getUsuario().getIdUsuario());
			stmt.setString(6, atividade.getNivel());
			stmt.setDouble(7, atividade.getDistancia());
			stmt.setInt(8, atividade.getIdAtividade());

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

			String sql = "DELETE FROM T_ATIVIDADE WHERE ID_ATIVIDADE = ?";
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
	public Atividade buscar(int id) {

		CategoriaAtividade categoriaAtividade = null;
		Atividade atividade = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement(
					"SELECT * FROM T_ATIVIDADE INNER JOIN T_CAT_ATIVIDADE ON (T_ATIVIDADE.T_CAT_ATIVIDADE_ID_CATEGORIA = T_CAT_ATIVIDADE.ID_CATEGORIA) WHERE ID_ATIVIDADE = ?");

			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int idAtividade = rs.getInt("ID_ATIVIDADE");
				int duracao = rs.getInt("TP_DURACAO");
				double calorias = rs.getDouble("NR_CALORIAS_TOTAL");

				java.sql.Date data = rs.getDate("DT_ATIVIDADE");
				Calendar dataAtividade = Calendar.getInstance();
				dataAtividade.setTimeInMillis(data.getTime());

				int idCategoria = rs.getInt("ID_CATEGORIA");
				String categoria = rs.getString("DS_CATEGORIA");

				String nivel = rs.getString("NR_NIVEL");
				double distancia = rs.getDouble("NR_DISTANCIA");

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

				categoriaAtividade = new CategoriaAtividade(idCategoria, categoria);

				atividade = new Atividade(idAtividade, duracao, calorias, dataAtividade, nivel, distancia);
				atividade.setCategoria(categoriaAtividade);
				atividade.setUsuario(usuario);
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
		return atividade;
	}

	@Override
	public List<Atividade> listar(int idUsuario) {

		CategoriaAtividade categoriaAtividade = null;
		Atividade atividade = null;
		List<Atividade> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement(
					"SELECT * FROM T_ATIVIDADE INNER JOIN T_CAT_ATIVIDADE ON (T_ATIVIDADE.T_CAT_ATIVIDADE_ID_CATEGORIA = T_CAT_ATIVIDADE.ID_CATEGORIA) WHERE T_USUARIO_ID_USUARIO = ?");
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idAtividade = rs.getInt("ID_ATIVIDADE");
				int duracao = rs.getInt("TP_DURACAO");
				double calorias = rs.getDouble("NR_CALORIAS_TOTAL");

				java.sql.Date data = rs.getDate("DT_ATIVIDADE");
				Calendar dataAtividade = Calendar.getInstance();
				dataAtividade.setTimeInMillis(data.getTime());

				int idCategoria = rs.getInt("ID_CATEGORIA");
				String categoria = rs.getString("DS_CATEGORIA");

				String nivel = rs.getString("NR_NIVEL");
				double distancia = rs.getDouble("NR_DISTANCIA");

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

				categoriaAtividade = new CategoriaAtividade(idCategoria, categoria);

				atividade = new Atividade(idAtividade, duracao, calorias, dataAtividade, nivel, distancia);
				atividade.setCategoria(categoriaAtividade);
				atividade.setUsuario(usuario);
				lista.add(atividade);
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
	public double listarCaloriasGastas() {

		Atividade atividade = new Atividade();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao
					.prepareStatement("SELECT SUM(NR_CALORIAS_TOTAL) FROM T_ATIVIDADE WHERE T_USUARIO_ID_USUARIO = 1");
			rs = stmt.executeQuery();

			while (rs.next()) {
				double totalCalorias = rs.getDouble("SUM(NR_CALORIAS_TOTAL)");

				atividade.setTotalCalorias(totalCalorias);
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
		return atividade.getTotalCalorias();
	}

	@Override
	public double getDistanciaPercorrida() {
		Atividade atividade = new Atividade();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement("SELECT SUM(NR_DISTANCIA) FROM T_ATIVIDADE WHERE T_USUARIO_ID_USUARIO = 1");
			rs = stmt.executeQuery();

			while (rs.next()) {

				double distanciaPercorrida = rs.getDouble("SUM(NR_DISTANCIA)");

				atividade.setDistanciaPercorrida(distanciaPercorrida);

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
		return atividade.getDistanciaPercorrida();
	}

	@Override
	public String getCategoriaPraticada() {
		Atividade atividade = new Atividade();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao
					.prepareStatement("SELECT COUNT(T_CAT_ATIVIDADE_ID_CATEGORIA), T_CAT_ATIVIDADE.DS_CATEGORIA\r\n"
							+ "FROM T_ATIVIDADE INNER JOIN T_CAT_ATIVIDADE ON (T_ATIVIDADE.T_CAT_ATIVIDADE_ID_CATEGORIA = T_CAT_ATIVIDADE.ID_CATEGORIA) \r\n"
							+ "WHERE T_ATIVIDADE.T_USUARIO_ID_USUARIO = 1 \r\n"
							+ "GROUP BY T_CAT_ATIVIDADE_ID_CATEGORIA, T_CAT_ATIVIDADE.DS_CATEGORIA\r\n"
							+ "ORDER BY T_ATIVIDADE.T_CAT_ATIVIDADE_ID_CATEGORIA ASC FETCH FIRST 1 ROWS ONLY");
			rs = stmt.executeQuery();

			while (rs.next()) {

				String categoriaPraticada = rs.getString("DS_CATEGORIA");

				atividade.setCategoriaPraticada(categoriaPraticada);

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
		return atividade.getCategoriaPraticada();
	}

}
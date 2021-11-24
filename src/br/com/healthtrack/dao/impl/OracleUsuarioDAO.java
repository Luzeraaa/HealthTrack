package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO {

	Connection conexao;

	@Override
	public void cadastrar(Usuario usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_USUARIO (ID_USUARIO, NM_USUARIO, SEX_USUARIO, DS_EMAIL, DS_SENHA,"
					+ "DT_NASCIMENTO, NR_ALTURA) VALUES (SQ_T_USUARIO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSexo());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getSenha());
			java.sql.Date data = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			stmt.setDate(5, data);
			stmt.setDouble(6, usuario.getAltura());

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
	public void atualizar(Usuario usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_USUARIO SET NM_USUARIO = ?, SEX_USUARIO = ?, DS_EMAIL = ?, DS_SENHA = ?, DT_NASCIMENTO = ?, NR_ALTURA =? WHERE ID_USUARIO = 1"; 
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSexo());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getSenha());
			java.sql.Date data = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			stmt.setDate(5, data);
			stmt.setDouble(6, usuario.getAltura());

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
	public void remover(int id) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_USUARIO WHERE ID_USUARIO = ?";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
					
		}catch(SQLException e ) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conexao.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Usuario buscar(int id) {
		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE ID_USUARIO = 1");
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				int idUsuario = rs.getInt("ID_USUARIO");
				String nome = rs.getString("NM_USUARIO");
				String sexo = rs.getString("SEX_USUARIO");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				java.sql.Date data = rs.getDate("DT_NASCIMENTO");
				Calendar nascimento = Calendar.getInstance();
				nascimento.setTimeInMillis(data.getTime());
				float altura = rs.getFloat("NR_ALTURA");
				
				
				usuario = new Usuario(idUsuario, nome, sexo, email, senha, nascimento, altura);
				
			}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					stmt.close();
					rs.close();
					conexao.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		return usuario;
	}

	@Override
	public List<Usuario> listar() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int idUsuario = rs.getInt("ID_USUARIO");
				String nome = rs.getString("NM_USUARIO");
				String sexo = rs.getString("SEX_USUARIO");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				java.sql.Date data = rs.getDate("DT_NASCIMENTO");
				Calendar nascimento = Calendar.getInstance();
				nascimento.setTimeInMillis(data.getTime());
				float altura = rs.getFloat("NR_ALTURA");
				
				
				Usuario usuario = new Usuario(idUsuario, nome, sexo, email, senha, nascimento, altura);
				lista.add(usuario);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}

}



























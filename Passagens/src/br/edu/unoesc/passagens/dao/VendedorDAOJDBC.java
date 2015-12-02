package br.edu.unoesc.passagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.unoesc.passagens.conexao.Conexao;
import br.edu.unoesc.passagens.model.Vendedor;

public class VendedorDAOJDBC implements VendedorDAO{

	private Connection connection;
	
	public VendedorDAOJDBC() {
		connection = Conexao.conectar();
	}

	@Override
	public void inserir(Vendedor entidade) {	
	}

	@Override
	public void alterar(Vendedor entidade) {
	}

	@Override
	public void excluir(Vendedor entidade) {
	}

	@Override
	public Vendedor buscar(Integer id) {
		return null;
	}

	@Override
	public List<Vendedor> todos() {
		return null;
	}
	
	@Override
	public String buscarNomePorUsuario (String usuario) {
		String sql = "SELECT vendedor_nome FROM vendedor WHERE usuario = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, usuario);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String nome = rs.getString("vendedor_nome");
				return nome;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean validarPorUsuario(String usuario, String senha) {
		String sql = "SELECT * FROM vendedor WHERE usuario = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, usuario);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String username = rs.getString("usuario");
				String password = rs.getString("senha");
				if(username.equals(usuario) && password.equals(senha)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Vendedor buscarPorUsuario(String usuario) {
		Vendedor vendedor = new Vendedor();
		String sql = "SELECT * FROM vendedor WHERE usuario = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, usuario);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vendedor.setId(rs.getInt("vendedor_id"));
				vendedor.setNome(rs.getString("vendedor_nome"));
				vendedor.setUsuario(rs.getString("usuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendedor;
	}
}

package br.edu.unoesc.passagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.unoesc.passagens.conexao.Conexao;
import br.edu.unoesc.passagens.model.Cidade;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class CidadeDAOJDBC implements CidadeDAO{

	private Connection connection;
	
	public CidadeDAOJDBC() {
		connection = Conexao.conectar();
	}

	@Override
	public void inserir(Cidade entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Cidade entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Cidade entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cidade buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Cidade buscarPorNome(String nome) {
		String sql = "SELECT * FROM cidade WHERE cidade_nome = ?";
		Cidade cidade = new Cidade();
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, nome);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				cidade.setId(rs.getInt("cidade_id"));
				cidade.setNome(new SimpleStringProperty(rs.getString("cidade_nome")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cidade;
	}

	
	
	@Override
	public void buscarNomesCidades(Connection connection, ObservableList<Cidade> observableList) {
		String sql = "SELECT * FROM cidade";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				observableList.add(new Cidade(rs.getString("cidade_nome")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cidade> todos() {
		// TODO Auto-generated method stub
		return null;
	}

}

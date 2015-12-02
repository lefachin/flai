package br.edu.unoesc.passagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.unoesc.passagens.conexao.Conexao;
import br.edu.unoesc.passagens.model.Cidade;
import br.edu.unoesc.passagens.model.Linha;

public class LinhaDAOJDBC implements LinhaDAO {

	Connection connection;
	
	public LinhaDAOJDBC() {
		connection = Conexao.conectar();
	}

	@Override
	public void inserir(Linha entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Linha entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Linha entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Linha buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Linha> todos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Linha buscaLinha(Cidade origem, Cidade destino, String dia) {
		String sql = "SELECT * FROM linha WHERE origem = ? AND destino = ? AND dia = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, origem.getId());
			pstmt.setInt(2, destino.getId());
			pstmt.setString(3, dia);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Cidade origem2 = new Cidade();
				origem2.setId(rs.getInt("origem"));
				Cidade destino2 = new Cidade();
				destino2.setId(rs.getInt("destino"));
				Linha linha = new Linha(rs.getString("linha_nome"), rs.getString("dia"),
						origem2, destino2, rs.getDouble("valor"), rs.getTime("horario_saida").toLocalTime(),
						rs.getTime("horario_chegada").toLocalTime(), rs.getString("duracao_viagem"));
				linha.setId(rs.getInt("linha_id"));
				origem2.setNome(origem.getNome());
				destino2.setNome(destino.getNome());
				return linha;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

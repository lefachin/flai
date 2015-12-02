package br.edu.unoesc.passagens.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.passagens.conexao.Conexao;
import br.edu.unoesc.passagens.model.Passagem;

public class PassagemDAOJDBC implements PassagemDAO{

	private Connection connection;
	
	public PassagemDAOJDBC() {
		connection = Conexao.conectar();
	}

	@Override
	public void inserir(Passagem passagem) {
		String sql = "INSERT INTO passagem (cliente, vendedor, linha, data_venda, data_viagem, poltrona) values(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, passagem.getCliente().getId());
			pstmt.setInt(2, passagem.getVendedor().getId());
			pstmt.setInt(3, passagem.getLinha().getId());
			pstmt.setDate(4, Date.valueOf(passagem.getData_venda()));
			pstmt.setDate(5, Date.valueOf(passagem.getData_viagem()));
			pstmt.setInt(6, passagem.getPoltrona());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Passagem entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Passagem entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Passagem buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Passagem> todos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Passagem> retornaTodasPassagemPorDataViagem(LocalDate data_viagem) {
		List<Passagem> passagens = new ArrayList<>();
		String sql = "SELECT poltrona FROM passagem WHERE data_viagem = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(data_viagem));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Passagem passagem = new Passagem();
				passagem.setPoltrona(rs.getInt("poltrona"));
				passagens.add(passagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passagens;
	}
}

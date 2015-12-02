package br.edu.unoesc.passagens.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.passagens.conexao.Conexao;
import br.edu.unoesc.passagens.model.Cliente;

public class ClienteDAOJDBC implements ClienteDAO {

	private Connection connection;
	
	public ClienteDAOJDBC() {
		connection = Conexao.conectar();
	}

	@Override
	public void inserir(Cliente cliente) {
		String sql = "INSERT INTO cliente (cliente_nome, cpf, rg, passaporte, data_nascimento) values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setString(3, cliente.getRg());
			pstmt.setString(4, cliente.getPassaporte());
			pstmt.setDate(5, Date.valueOf(cliente.getData_nascimento()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Cliente cliente) {
		String sql = "UPDATE cliente SET cliente_nome = ?, cpf = ?, rg = ?, passaporte = ?, data_nascimento = ? WHERE cliente_id = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setString(3,  cliente.getRg());
			pstmt.setString(4, cliente.getPassaporte());
			pstmt.setDate(5, Date.valueOf(cliente.getData_nascimento()));
			pstmt.setInt(6, cliente.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Cliente entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> todos() {
		List<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM cliente";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("cliente_id"));
				cliente.setNome(rs.getString("cliente_nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setRg(rs.getString("rg"));
				cliente.setPassaporte(rs.getString("passaporte"));
				cliente.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public Cliente buscarPorCPF(String cpf) {
		Cliente cliente = new Cliente();
		String sql = "SELECT * FROM cliente WHERE cpf = ?";
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, cpf);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				cliente.setId(rs.getInt("cliente_id"));
				cliente.setNome(rs.getString("cliente_nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setRg(rs.getString("rg"));
				cliente.setPassaporte(rs.getString("passaporte"));
				cliente.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

}

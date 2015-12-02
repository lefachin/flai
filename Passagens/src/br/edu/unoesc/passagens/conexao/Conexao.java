package br.edu.unoesc.passagens.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Conexao {
	private static Connection connection;
	
	static {
		String endereco = "jdbc:mysql://localhost:3306/flai";
		String usuario = "root";
		String senha = "root";
		try {
			connection = DriverManager.getConnection(endereco, usuario, senha);
			
		} catch (SQLException e) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setHeaderText("Erro de conexão ao Banco de Dados");
			alerta.setContentText("Ocorreu um erro ao tentar iniciar uma conexão ao Banco de Dados");
			System.exit(0);
		}
	}
	
	public static Connection conectar() {
		return connection;
	}
	
	public static void finalizar() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
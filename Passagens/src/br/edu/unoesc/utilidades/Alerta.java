/**
 * 
 */
package br.edu.unoesc.utilidades;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Fachin
 *
 */
public class Alerta {

	public static void alertaLinhaInexistente() {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setHeaderText("Erro ao selecionar a linha");
		alerta.setContentText("N�o existem servi�o para a data selecionada");
		alerta.showAndWait();
	}
	
	public static void alertaSelecaoComboBox() {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setHeaderText("Erro ao selecionar a linha");
		alerta.setContentText("Por favor, selecione as cidades de origem e destino");
		alerta.showAndWait();
	}
	
	public static void erroAoCarregarFXML() {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setHeaderText("Erro ao carregar FXML");
		alerta.setContentText("Ocorreu um erro no FMXLLoader ao tentar carregar um aquivo");
		alerta.show();
	}
	
	public static void erroLogin() {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setHeaderText("Erro de login");
		alerta.setContentText("Usu�rio e/ou senha inv�lidos");
		alerta.show();
	}
	
	public static void erroCPFInvalido() {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setHeaderText("CPF inv�lido");
		alerta.setContentText("O CPF digitado deve possuir apenas 11 n�meros");
		alerta.show();
	}
	
	public static void erroCamposVazios() {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setHeaderText("Campo vazio");
		alerta.setContentText("Preencha todos os campos para poder cadastrar o cliente");
		alerta.show();
	}
	
	public static void alertaCadastroEfetuadoComSucesso() {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setHeaderText("Cadastro");
		alerta.setContentText("Cliente cadastrado com sucesso");
		alerta.showAndWait();
	}
	
	public static void alertaHorarioDiaInvalido() {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setHeaderText("Erro ao selecionar linha");
		alerta.setContentText("N�o � poss�vel acessar essa linha, pois j� passou do hor�rio permitido");
		alerta.showAndWait();
	}
	
	public static void alertaAleteracaoEfetuadaComSucesso() {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setHeaderText("Altera��o de dados");
		alerta.setContentText("Dados do cliente alterados com sucesso");
		alerta.showAndWait();
	}
	
	public static void erroAlteracaoDosDados() {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setHeaderText("Altera��o de dados");
		alerta.setContentText("Erro ao alterar os dados do cliente");
		alerta.showAndWait();
	}
}

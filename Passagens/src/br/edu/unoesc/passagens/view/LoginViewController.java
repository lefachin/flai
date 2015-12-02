package br.edu.unoesc.passagens.view;

import javafx.event.ActionEvent;

import java.io.IOException;

import br.edu.unoesc.passagens.daofactory.DAOFactory;
import br.edu.unoesc.passagens.model.Vendedor;
import br.edu.unoesc.utilidades.Alerta;
import br.edu.unoesc.utilidades.NodeHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {

	@FXML TextField tfUsuario;
	@FXML PasswordField pfSenha;
	@FXML Button btEntrar;
	@FXML Button btSair;
	private Vendedor vendedor = new Vendedor();
	
	public LoginViewController() {
	}

	/**
	 * M�todo respons�vel por tratar o evento de clique no bot�o Entrar dentro da Scene de Login.
	 * <br>
	 * Primeiramente a classe Node se encarrega de esconder a Scene. Ent�o � criado um novo FMXLLoader que �
	 * respons�vel por localizar o arquivo .FXML refente a nova tela que ser� inicializada. Depois s�o criados
	 * os componentes b�sicos para iniciar um frame no JavaFX, o Parent, que recebe o .FXML, a Scene, que recebe
	 * o Parent e o Stage, que recebe a Scene. Ainda � feito uma pesquisa no banco, a partir do Usu�rio digitado
	 * no campo de texto, que ser� usada para retornar o nome do Vendedor para o pr�ximo frame.
	 * @param event � um ActionEvent passado para a classe Node para que a mesma esconda essa Scene quando o
	 * bot�o Entrar for clicado.
	 * @throws IOException Retorna um erro ao tentar ler um arquivo FXML referente a uma nova Scene.
	 * @author Luiz Eduardo Fachin
	 */
	
	@FXML
	private void handleEntrar(ActionEvent event) throws IOException{
		try {
			if (validarUsuario()) {
				NodeHandler.fecharJanela(event);
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/PrincipalView.fxml"));
				loader.load();
				Parent parent = loader.getRoot(); 
				Stage stage = new Stage();
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.setTitle("FLAI Passagens");
				stage.getIcons().add(new Image("file:img/ticket-icon.png"));
				stage.setResizable(false);
				setNomeVendedor(loader, this.vendedor);
				
				PrincipalViewController vendaView = loader.<PrincipalViewController>getController();
				vendaView.setStage(stage);
				
				stage.show();
			}
		} catch (IOException ioe) {
			Alerta.erroAoCarregarFXML();
		}
	}
	
	/**
	 * M�todo usado para pegar as informa��es do Vendedor no banco a partir do Usu�rio entrado no campo de texto.
	 * @param loader Espera um FXMLLoader referente ao pr�ximo frame que receber� as informa��es do Vendedor.
	 * @param vendedor Espera uma inst�ncia de Vendedor onde seram setados os dados da busca.
	 * @author Luiz Eduardo Fachin
	 */
	private void setNomeVendedor(FXMLLoader loader, Vendedor vendedor) {
		PrincipalViewController vendaViewController = loader.getController();
		vendaViewController.setVendedor(DAOFactory.get().vendedorDAO().buscarPorUsuario(tfUsuario.getText()));
	}
	
	/**
	 * M�todo usado para finalizar a aplica��o ao clicar no bot�o correspondente.
	 * @param event � um ActionEvent passado para a classe Node para que a mesma esconda essa Scene quando o
	 * bot�o Entrar for clicado.
	 * @author Luiz Eduardo Fachin
	 * @throws IOException 
	 */
	@FXML private void handleSair(ActionEvent event){
		NodeHandler.fecharAplicacao(event);
	}
	
	/**
	 * M�todo que faz a verfifica��o de usu�rio e senha.
	 * @return <b>True</b> se a combina��o se usu�rio e senha existir no banco de dados, ou <b>false</b> se n�o existir.
	 * @author Luiz Eduardo Fachin
	 */
	private boolean validarUsuario() {
		String usuario = tfUsuario.getText().toString();
		if (DAOFactory.get().vendedorDAO().validarPorUsuario(
				usuario, pfSenha.getText().toString())
			){
			return true;
		} else {
			Alerta.erroLogin();
			return false;
		}
	}
	
}

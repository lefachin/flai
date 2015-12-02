 package br.edu.unoesc.passagens.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.unoesc.passagens.conexao.Conexao;
import br.edu.unoesc.passagens.daofactory.DAOFactory;
import br.edu.unoesc.passagens.model.Cidade;
import br.edu.unoesc.passagens.model.Cliente;
import br.edu.unoesc.passagens.model.Linha;
import br.edu.unoesc.passagens.model.Vendedor;
import br.edu.unoesc.passagens.relatorio.RelatorioUtil;
import br.edu.unoesc.utilidades.Alerta;
import br.edu.unoesc.utilidades.DiaDaSemana;
import br.edu.unoesc.utilidades.NodeHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PrincipalViewController implements Initializable{
	
	private ObservableList<Linha> linhaData = FXCollections.observableArrayList();
	
	private Vendedor vendedor = new Vendedor();
	@FXML Label lbUsuarioLogado = new Label();
	
	@FXML DatePicker dpIda;
	@FXML DatePicker dpRetorno;
	
	private ObservableList<Cidade> listaCidades;
	@FXML ComboBox<Cidade> cbOrigem;
	@FXML ComboBox<Cidade> cbDestino;
	
	@FXML ToggleGroup rbGroup;
	@FXML RadioButton rbIda;
	@FXML RadioButton rbRetorno;
	
	@FXML Label lbRetorno;
	
	@FXML Button btConfirmar;
	@FXML Button btFinalizar;
	
	private Stage vendaStage;
	
	private Linha linha;
	
	/**
	 * M�todo usado para finalizar a aplica��o ao clicar no bot�o FINALIZAR SISTEMA.
	 * Dentro dele � chamado o m�todo <b>fecharAplicacao</b> da classe NodeHandler,
	 * que simplismente usa o ActionEvent para pegar a Scene e fech�-la.
	 * @param event � um ActionEvent passado para a classe Node para que a mesma esconda essa Scene quando o
	 * bot�o Entrar for clicado.
	 * @author Luiz Eduardo Fachin
	 */
	
	@FXML private void handleFinalizarSistema(ActionEvent event) {
		NodeHandler.fecharAplicacao(event);
	}
	
	/**
	 * M�todo passado como a��o ao bot�o CONFIRMAR para que quando clicado ele abra, caso as condi��es sejam
	 * atendidas, a tela de escolha das poltronas.
	 * @author Luiz Eduardo Fachin
	 */
	
	@FXML private void handleConfirmar() {
		if (comboBoxIsSelected()) {
			LocalDate dataIda = getDataIda();
			String diaIda = dataIda.getDayOfWeek().toString();
			Cidade origem = DAOFactory.get().cidadeDAO().buscarPorNome(cbOrigem.getValue().toString());
			Cidade destino = DAOFactory.get().cidadeDAO().buscarPorNome(cbDestino.getValue().toString());
			linha = DAOFactory.get().linhaDAO().buscaLinha(origem, destino, DiaDaSemana.getDiaSemana(diaIda));
			if (linha != null) {
				LocalTime saida = linha.getHorarioSaida();
				String agoraHoraString = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"));
				LocalTime agoraHora = LocalTime.parse(agoraHoraString);
				LocalDate agoraData = LocalDate.now();
				LocalDate data = getDataIda();
				if (data.isAfter(agoraData)) {
					linhaData.add(linha);
					abrirPoltronaView();
				} else if (data.equals(agoraData) && saida.isAfter(agoraHora)) {
					linhaData.add(linha);
					abrirPoltronaView();	
				}
				else {
					Alerta.alertaHorarioDiaInvalido();
				}
			} else {
				Alerta.alertaLinhaInexistente();
			}
		} else {
			Alerta.alertaSelecaoComboBox();
		}
	}
	
	/**
	 * M�todo chamado pelo MenuItem ALTERAR DADOS CLIENTES. Ele vai abrir uma nova janela com
	 * campos para alterar os dados de um determinado cliente;
	 * 
	 * @author Luiz Eduardo Fachin
	 */
	
	@FXML private void handleAlterarDadosClientes() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/AlterarClienteView.fxml"));
			loader.load();
			Parent parent = loader.getRoot(); 
			Stage stage = new Stage();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("FLAI Passagens");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(vendaStage);
			stage.getIcons().add(new Image("file:img/ticket-icon.png"));
			stage.setResizable(false);
			
			stage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * M�todo chamado pelo MenuItem LISTAR CLIENTES. Ele vai simplesmente mostrar um relat�rio na tela
	 * pelo visualizador do JasperSoft.
	 * 
	 * @author Luiz Eduardo Fachin
	 */
	
	@FXML private void handleListarClientes() {
		
		List<Cliente> clientes = DAOFactory.get().clienteDAO().todos();
		
		for (Cliente c : clientes) {
			Integer anoAtual = LocalDate.now().getYear();
			Integer anoNascimento = c.getData_nascimento().getYear();
			Integer idade = anoAtual - anoNascimento;
			c.setIdade(idade);
		}
		
		new RelatorioUtil().compileViewReport("relatorio/relatorioClientes.jrxml", clientes, null);
	}
	
	/**
	 * M�todo chamado pelo MenuItem MAPA DE VIAGEM. Esse m�todo vai mostrar um rel�torio listando todos
	 * os passageiros embarcados na aeronave.
	 * 
	 * @author Luiz Eduardo Fachin
	 */
	
	@FXML private void handleMapaViagem() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/MapaViagemView.fxml"));
			loader.load();
			Parent parent = loader.getRoot(); 
			Stage stage = new Stage();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("FLAI Passagens");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(vendaStage);
			stage.getIcons().add(new Image("file:img/ticket-icon.png"));
			stage.setResizable(false);
			
			MapaViagemViewController mapaViagemView = loader.<MapaViagemViewController>getController();
			mapaViagemView.setListaCidadesComboBox(listaCidades);
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * M�todo chamado pelo MenuItem SOBRE. Esse m�todo vai usar um componente do JavaFX chamado WebView para
	 * renderizar uma p�gina da web com um manual sobre o sistema.
	 * 
	 * @author Luiz Eduardo Fachin
	 */
	
	@FXML private void handleSobre() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/SobreView.fxml"));
			loader.load();
			WebView webView = new WebView();
			webView.getEngine().load("file:///E:/HTMLWorkspace/Flai/manual.html");
			Stage stage = new Stage();
			Scene scene = new Scene(webView);
			stage.setScene(scene);
			stage.setTitle("FLAI Passagens");
			stage.getIcons().add(new Image("file:img/ticket-icon.png"));
			stage.setResizable(false);
			
			
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * M�todo usado para abrir o frame com as poltronas dispon�veis para a viagem em determinada data.
	 */
	
	public void abrirPoltronaView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/PoltronaView.fxml"));
			loader.load();
			Parent parent = loader.getRoot(); 
			Stage stage = new Stage();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("FLAI Passagens");
			stage.getIcons().add(new Image("file:img/ticket-icon.png"));
			stage.setResizable(false);
			
			//Passagem de par�metros para o controlador do FXML poltronaView.
			PoltronaViewController poltronaView = loader.<PoltronaViewController>getController();
			poltronaView.setVendedor(vendedor);
			poltronaView.setLinha(linha);
			poltronaView.verificaPoltronas(getDataIda());
			poltronaView.setLabelData(getDataIda().toString());
			poltronaView.setLabelOrigem(linha.getOrigem().getNome().getValue());
			poltronaView.setLabelDestino(linha.getDestino().getNome().getValue());
			poltronaView.setLabelHorarioSaida(linha.getHorarioSaida().toString());
			poltronaView.setLabelHorarioChegada(linha.getHorarioChegada().toString());
			poltronaView.setLabelLinha(linha.getNome().toString());
			poltronaView.setLabelValor(linha.getValor());
			poltronaView.setDataViagem(getDataIda());
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * M�todo usado para setar o atual stage da aplica��o nesta classe. Este por sua vez � usado para
	 * servir de dono para as janelas.
	 * @param stage O Stage atual da aplica��o recuperado da classe LoginViewController.
	 * @author Luiz Eduardo Fachin
	 */
	public void setStage(Stage stage) {
		this.vendaStage = stage;
	}
	
	/**
	 * M�todo usado para retornar uma lista com informa��es referentes as linhas.
	 * @return <b>linhaData</b>, ou seja, os dados da linha.
	 */
	
	public ObservableList<Linha> getLinhaData() {
		return linhaData;
	}
	
	/**
	 * M�todo que retorna a data selecionada no DatePicker de origem, ou seja, a data de ida da viagem.
	 * @return <b>dataIda</b>, ou seja, a data de ida selecionada no DatePicker.
	 * @author Luiz Eduardo Fachin
	 */
	public LocalDate getDataIda() {
		LocalDate dataIda = dpIda.getValue();
		return dataIda;
	}
	
	/**
	 * M�todo que retorna a data selecionada no DatePicker de  retorno, ou seja, a data de retorno da viagem.
	 * @return <b>dataRetorno</b>, ou seja, a data de retorno selecionada no DatePicker.
	 * @author Luiz Eduardo Fachin
	 */
	public LocalDate getDataRetorno() {
		LocalDate dataRetorno = dpRetorno.getValue();
		return dataRetorno;
	}

	/**
	 * M�todo usado para indicar qual usu�rio est� atualmente logado no sistema.
	 * @param vendedor O vendedor que est� logado no sistema.
	 * @author Luiz Eduardo Fachin
	 */
	public void setVendedor(Vendedor vendedor) {
		lbUsuarioLogado.setText(lbUsuarioLogado.getText() + " " + vendedor.getNome());
		this.vendedor = vendedor;
	}
	
	/**
	 * M�todo respons�vel por esconder os componentes (Label e DatePicker) referentes � viagem de retorno,
	 * quando o usu�rio selecionar o RadioButton de <b>"Ida"</b>.
	 * @author Luiz Eduardo Fachin
	 */
	
	@FXML private void esconderDatePickerRetorno(){
		lbRetorno.setVisible(false);
		dpRetorno.setVisible(false);
	}

	/**
	 * M�todo respons�vel por mostrar os componentes (Label e DatePicker) referentes � viagem de retorno,
	 * quando o usu�rio selecionar o RadioButton de <b>"Ida e Volta"</b>.
	 * @author Luiz Eduardo Fachin
	 */
	
	@FXML private void mostrarDatePickerRetorno() {
		lbRetorno.setVisible(true);
		dpRetorno.setVisible(true);
	}
	
	/**
	 * M�todo respons�vel por verificar se ambas as ComboBox est�o com valores selecionados.
	 * @return <b>True</b> se ambas as ComboBox tiverem valores selecionados, ou <b>False</b> se n�o
	 * tiverem
	 * @author Luiz Eduardo Fachin
	 */
	
	private boolean comboBoxIsSelected() {
		if(cbOrigem.getValue() == null || cbDestino.getValue() == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * M�todo usado para inicializar alguns itens essenciais para os componentes de tela: a observableList que
	 * fornece o nome das cidades para as ComboBox, o ToggleGroup que impossibilita a sele��o de dois RadioButton
	 * ao mesmo tempo e os DatePicker tem suas datas setadas para o "dia de hoje" e "dia de amanh�".
	 * @author Luiz Eduardo Fachin
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listaCidades = FXCollections.observableArrayList();
		DAOFactory.get().cidadeDAO().buscarNomesCidades(Conexao.conectar(), listaCidades);
		cbOrigem.setItems(listaCidades);
		cbDestino.setItems(listaCidades);
		
		rbGroup = new ToggleGroup();
		
		rbIda.setToggleGroup(rbGroup);
		rbRetorno.setToggleGroup(rbGroup);
		
		esconderDatePickerRetorno();
		
		dpIda.setValue(LocalDate.now());
		dpRetorno.setValue(LocalDate.now().plusDays(1));
	}
}
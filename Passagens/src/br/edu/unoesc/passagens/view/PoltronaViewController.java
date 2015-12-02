/**
 * 
 */
package br.edu.unoesc.passagens.view;


import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.unoesc.passagens.daofactory.DAOFactory;
import br.edu.unoesc.passagens.model.Linha;
import br.edu.unoesc.passagens.model.Passagem;
import br.edu.unoesc.passagens.model.Vendedor;
import br.edu.unoesc.utilidades.NodeHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Fachin
 *
 */
public class PoltronaViewController implements Initializable {
	
	@FXML Button p1; @FXML Button p2; @FXML Button p3; @FXML Button p4; @FXML Button p5; @FXML Button p6; @FXML Button p7;
	@FXML Button p8; @FXML Button p9; @FXML Button p10; @FXML Button p11; @FXML Button p12; @FXML Button p13; @FXML Button p14;
	@FXML Button p15; @FXML Button p16; @FXML Button p17; @FXML Button p18; @FXML Button p19; @FXML Button p20; @FXML Button p21;
	@FXML Button p22; @FXML Button p23; @FXML Button p24; @FXML Button p25; @FXML Button p26; @FXML Button p27; @FXML Button p28;
	@FXML Button p29; @FXML Button p30; @FXML Button p31; @FXML Button p32; @FXML Button p33; @FXML Button p34; @FXML Button p35;
	@FXML Button p36; @FXML Button p37; @FXML Button p38; @FXML Button p39; @FXML Button p40; @FXML Button p41; @FXML Button p42;
	@FXML Button p43; @FXML Button p44; @FXML Button p45; @FXML Button p46; @FXML Button p47; @FXML Button p48; @FXML Button p49;
	@FXML Button p50;

	@FXML Label lbDe = new Label();
	@FXML Label lbPara = new Label();
	@FXML Label lbSaida = new Label();
	@FXML Label lbChegada = new Label();
	@FXML Label lbData = new Label();
	@FXML Label lbLinha = new Label();
	@FXML Label lbValor = new Label();
	
	private LocalDate data_viagem;
	private Linha linha;
	private Vendedor vendedor;
	
	private List<Passagem> passagens = new ArrayList<>();
	
	public void setLinha(Linha linha) {
		this.linha = linha;
	}
	
	@FXML private void handleEscolhaPoltrona(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/CadastroClienteVendaView.fxml"));
			loader.load();
			Parent parent = loader.getRoot(); 
			Stage stage = new Stage();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("FLAI Passagens");
			stage.getIcons().add(new Image("file:img/ticket-icon.png"));
			stage.setResizable(false);
			
			CadastroClienteVendaViewController cadastroView = loader.<CadastroClienteVendaViewController>getController();
			
			String textoBotao = ((Button)event.getSource()).getText();
			Integer poltrona = Integer.valueOf(textoBotao);
			
			cadastroView.setPoltrona(poltrona);
			cadastroView.setDataViagem(data_viagem);
			cadastroView.setLinha(linha);
			cadastroView.setVendedor(vendedor);
			
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		NodeHandler.fecharJanela(event);
	}
	
	@FXML private void handleVoltar(ActionEvent event) {
		NodeHandler.fecharJanela(event);
	}

	public void setLabelData(String data) {
		lbData.setText(lbData.getText() + " " + data);
	}
	
	public void setLabelOrigem(String origem) {
		lbDe.setText(lbDe.getText() + " " + origem);
	}
	
	public void setLabelDestino(String destino) {
		lbPara.setText(lbPara.getText() + " " + destino);
	}
	
	public void setLabelHorarioChegada(String horarioChegada) {
		lbChegada.setText(lbChegada.getText() + " " + horarioChegada);
	}
	
	public void setLabelHorarioSaida(String horarioSaida) {
		lbSaida.setText(lbSaida.getText() + " " + horarioSaida);
	}
	
	public void setLabelLinha(String nomeLinha) {
		lbLinha.setText(lbLinha.getText() + " " + nomeLinha);
	}
	
	public void setDataViagem(LocalDate data_viagem) {
		this.data_viagem = data_viagem;
	}
	
	public void setLabelValor (Double valor) {
		String valorString = String.valueOf(valor);
		lbValor.setText(lbValor.getText() + " " + String.format(valorString, new DecimalFormat("###,##0.00")));
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	public void verificaPoltronas(LocalDate data_viagem) {
		List<Button> listBotoes = new ArrayList<>();
		listBotoes.addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18,
				p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39,
				p40, p41, p42, p43, p44, p45, p46, p47, p48, p49 , p50));
		
		passagens = DAOFactory.get().passagemDAO().retornaTodasPassagemPorDataViagem(data_viagem);
		if (passagens != null) {
			for (Passagem p : passagens) {
				Integer poltrona = p.getPoltrona();
				Integer index = poltrona - 1;
				listBotoes.get(index).setStyle("-fx-background-color: red;");
				listBotoes.get(index).setDisable(true);
			}
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}

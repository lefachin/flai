/**
 * 
 */
package br.edu.unoesc.passagens.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import br.edu.unoesc.passagens.daofactory.DAOFactory;
import br.edu.unoesc.passagens.model.Cliente;
import br.edu.unoesc.utilidades.Alerta;
import br.edu.unoesc.utilidades.NodeHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Fachin
 *
 */
public class AlterarClienteViewController {

	@FXML private TextField tfCPF;
	@FXML private TextField tfNome;
	@FXML private TextField tfRG;
	@FXML private TextField tfPassaporte;
	@FXML private TextField tfDataNascimento;
	
	private Cliente cliente;
	
	@FXML private void handleVerificarCPF() {
		if (verificarCPF() == true) {
			cliente = DAOFactory.get().clienteDAO().buscarPorCPF(tfCPF.getText());
			if (cliente.getCpf() != null) {
				tfNome.setText(cliente.getNome());
				tfRG.setText(cliente.getRg());
				tfPassaporte.setText(cliente.getPassaporte());
				tfDataNascimento.setText(dataParaTela(cliente.getData_nascimento()));
				habilitarCampos();
			} else {
				habilitarCampos();
			}
		}
	}
	
	@FXML private void handleAlterar(ActionEvent event) {
		String cpfCliente = tfCPF.getText();
		if(verificaCampos() == true) {
			cliente.setCpf(cpfCliente);
			cliente.setNome(tfNome.getText());
			cliente.setRg(tfRG.getText());
			cliente.setPassaporte(tfPassaporte.getText());
			cliente.setData_nascimento(dataParaBanco(tfDataNascimento.getText()));
			
			try {
				DAOFactory.get().clienteDAO().alterar(cliente);
				Alerta.alertaAleteracaoEfetuadaComSucesso();
			} catch (Exception e) {
				Alerta.erroAlteracaoDosDados();
			}
		}
	}
	
	@FXML private void handleSair(ActionEvent event) {
		NodeHandler.fecharJanela(event);
	}
	
	private boolean verificarCPF() {
		String cpf = tfCPF.getText();
		if (!cpf.isEmpty()) {
			if(cpf.length() != 11){
				Alerta.erroCPFInvalido();
				return false;
			} else if(cpf.matches("[0-9]+")) {
				return true;
			} else {
				Alerta.erroCPFInvalido();
				return false;
			}
		} else {
			Alerta.erroCPFInvalido();
			return false;
		}
	}
	
	private void habilitarCampos() {
		tfNome.setDisable(false);
		tfRG.setDisable(false);
		tfPassaporte.setDisable(false);
		tfDataNascimento.setDisable(false);
	}
	
	private boolean verificaCampos() {
		String cpf = tfCPF.getText();
		String nome = tfNome.getText();
		String rg = tfRG.getText();
		String passaporte = tfPassaporte.getText();
		String dataNascimento = tfDataNascimento.getText();
		
		if (cpf.isEmpty() || nome.isEmpty() || rg.isEmpty() || passaporte.isEmpty() || dataNascimento.isEmpty()) {
			Alerta.erroCamposVazios();
			return false;
		} else {
			return true;
		}
	}
	
	private LocalDate dataParaBanco(String data) {
		LocalDate dataBanco;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataBanco = LocalDate.parse(data, formatter);
		return dataBanco;
	}
	
	private String dataParaTela(LocalDate data) {
		String dataFormatada;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataFormatada = data.format(formatter);
		return dataFormatada;
	}
}

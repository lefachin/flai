/**
 * 
 */
package br.edu.unoesc.passagens.view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


import br.edu.unoesc.passagens.conexao.Conexao;
import br.edu.unoesc.passagens.daofactory.DAOFactory;
import br.edu.unoesc.passagens.model.Cliente;
import br.edu.unoesc.passagens.model.Linha;
import br.edu.unoesc.passagens.model.Passagem;
import br.edu.unoesc.passagens.model.Vendedor;
import br.edu.unoesc.passagens.relatorio.RelatorioUtil;
import br.edu.unoesc.utilidades.Alerta;
import br.edu.unoesc.utilidades.NodeHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Fachin
 *
 */
public class CadastroClienteVendaViewController{

	@FXML private TextField tfCPF;
	@FXML private TextField tfNome;
	@FXML private TextField tfRG;
	@FXML private TextField tfPassaporte;
	@FXML private TextField tfDataNascimento;
	
	private Integer poltrona;
	private Linha linha;
	private Vendedor vendedor;
	private LocalDate data_viagem;
	private boolean clienteExiste = false;
	private Cliente cliente;
	
	@FXML private void handleVerificarCPF() {
		if (verificarCPF() == true) {
			cliente = DAOFactory.get().clienteDAO().buscarPorCPF(tfCPF.getText());
			if (cliente.getCpf() != null) {
				tfNome.setText(cliente.getNome());
				tfRG.setText(cliente.getRg());
				tfPassaporte.setText(cliente.getPassaporte());
				tfDataNascimento.setText(dataParaTela(cliente.getData_nascimento()));
				clienteExiste = true;
			} else {
				tfNome.setDisable(false);
				tfRG.setDisable(false);
				tfPassaporte.setDisable(false);
				tfDataNascimento.setDisable(false);
				clienteExiste = false;
			}
		}
	}
	
	@FXML private void handleCadastrar(ActionEvent event) {
		String cpfCliente = tfCPF.getText();
		if(verificaCampos() == true) {
			cliente.setCpf(cpfCliente);
			cliente.setNome(tfNome.getText());
			cliente.setRg(tfRG.getText());
			cliente.setPassaporte(tfPassaporte.getText());
			cliente.setData_nascimento(dataParaBanco(tfDataNascimento.getText()));
			
			if (clienteExiste == false) {
				DAOFactory.get().clienteDAO().inserir(cliente);
			}
			Passagem passagem = new Passagem();
			passagem.setCliente(DAOFactory.get().clienteDAO().buscarPorCPF(cpfCliente));
			passagem.setData_venda(LocalDate.now());
			passagem.setData_viagem(data_viagem);
			passagem.setLinha(linha);
			passagem.setOrigem(linha.getOrigem());
			passagem.setDestino(linha.getDestino());
			passagem.setPoltrona(poltrona);
			passagem.setVendedor(vendedor);
			DAOFactory.get().passagemDAO().inserir(passagem);
				
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("data_viagem", Date.valueOf(data_viagem));
			parametros.put("poltrona", poltrona);
			
		    new RelatorioUtil().compileViewReport("relatorio/passagem.jrxml", Conexao.conectar(), parametros);
		               
		    NodeHandler.fecharJanela(event);	         
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
	
	public void setPoltrona(Integer poltrona) {
		this.poltrona = poltrona;
	}
	
	public void setLinha(Linha linha) {
		this.linha = linha;
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	public void setDataViagem(LocalDate data_viagem) {
		this.data_viagem = data_viagem;
	}
}

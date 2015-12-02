/**
 * 
 */
package br.edu.unoesc.passagens.view;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import br.edu.unoesc.passagens.conexao.Conexao;
import br.edu.unoesc.passagens.model.Cidade;
import br.edu.unoesc.passagens.relatorio.RelatorioUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * @author Fachin
 *
 */
public class MapaViagemViewController implements Initializable{

	@FXML private DatePicker dpDataViagem;
	@FXML private ComboBox<Cidade> cbOrigem;
	@FXML private ComboBox<Cidade> cbDestino;
	
	@FXML private void handleConfirmar() {
		Date data = Date.valueOf(dpDataViagem.getValue());
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("data_viagem", data);
		
		new RelatorioUtil().compileViewReport("relatorio/mapaViagem.jrxml", Conexao.conectar(), parametros);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dpDataViagem.setValue(LocalDate.now());
	}
	
	public void setListaCidadesComboBox(ObservableList<Cidade> listaCidades) {
		cbOrigem.setItems(listaCidades);
		cbDestino.setItems(listaCidades);
	}
}

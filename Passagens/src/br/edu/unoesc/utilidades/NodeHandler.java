/**
 * 
 */
package br.edu.unoesc.utilidades;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;

/**
 * @author Fachin
 *
 */
public class NodeHandler {

	public static void fecharJanela(ActionEvent event) {
		(((Node)(event.getSource())).getScene()).getWindow().hide();
	}
	
	public static void fecharAplicacao(ActionEvent event) {
		Platform.exit();
	}

}

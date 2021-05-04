package br.com.crudhibernate.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controlador da View.fxml
 * @author cristovao
 *
 */
public class ControllerView {
	
	@FXML
	private Button btSalvar;
	
	@FXML
	public void onBtSalvar(ActionEvent actionEvent) {
		System.out.println("BtSalvar acionado!");
	}
	
}

package br.com.crudhibernate.view;

import br.com.crudhibernate.dao.ClienteJpaDAOSingleton;
import br.com.crudhibernate.entities.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlador da View.fxml
 * @author cristovao
 *
 */
public class ControllerView {
	
	@FXML
	private TextField txtCPF;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtRG;
	
	@FXML
	private Button btSalvar;
	
	
	/**
	 * Ao clicar no btSalvar os dados de cliente preenchidos nos txts são 
	 * salvos no DB.
	 * @param actionEvent
	 */
	@FXML
	public void onBtSalvar(ActionEvent actionEvent) {
		
		Cliente cliente = new Cliente();
		cliente.setId(Integer.parseInt(txtId.getText()));
		cliente.setCpf(txtCPF.getText());
		cliente.setNome(txtNome.getText());
		cliente.setRg(txtRG.getText());
		
		ClienteJpaDAOSingleton.getInstance().merge(cliente);
		clearFields();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Cadastro realizado c/ sucesso!");
		alert.setHeaderText("Header text");
		alert.setContentText("Cadastro de cliente "+ cliente.getNome()+" realizado com sucesso!");
		alert.show();
	}
	
	
	/**
	 * Limpa campos de formulário.
	 */
	private void clearFields() {
		this.txtCPF.setText("");
		this.txtId.setText("");
		this.txtNome.setText("");
		this.txtRG.setText("");
	}
}

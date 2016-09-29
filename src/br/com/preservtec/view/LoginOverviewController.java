package br.com.preservtec.view;

import java.util.ArrayList;
import java.util.Optional;

import br.com.preservtec.LoginApp;
import br.com.preservtec.MainApp;
import br.com.preservtec.model.Procuracao;
import br.com.preservtec.util.PDFCreator;
import br.com.preservtec.util.Strings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginOverviewController {

	@FXML
	private TextField userTextField;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private ImageView logoImageView;

	private LoginApp loginApp;

	public LoginOverviewController() {		
	}
	
	@FXML
	private void initialize() {
	}
	
	public void setLoginApp(LoginApp loginApp) {
		this.loginApp = loginApp;
	}
	
	@FXML
	private void handleEntrar(){
		
		if (isInputValid()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Procuração Cadastrada");
			alert.setHeaderText("Tudo OK!");
			alert.setContentText(userTextField.getText().toString() + passwordTextField.getText().toString());
			Optional<ButtonType> result = alert.showAndWait();
			loginApp.getPrimaryStage().close();
			//Fazer a lógica de busca do login
		}
	}
	
	private boolean isInputValid() {
		String errorMessage = "";

		if (userTextField.getText() == null || userTextField.getText().length() == 0) {
			errorMessage += "Usuário Inválido \n";
		}

		if (passwordTextField.getText() == null || passwordTextField.getText().length() == 0) {
			errorMessage += "senha Inválida \n";
		} 
		if (errorMessage.length() == 0) {
			return true;
		}else {
			// Mosta a mensagem de erro
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos inválidos");
			alert.setHeaderText("Por favor, corrija os campos inválido");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			

			return false;
		}
	}


	
}

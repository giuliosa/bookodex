package br.com.preservtec.view;

import java.util.ArrayList;
import java.util.Optional;

import br.com.preservtec.LoginApp;
import br.com.preservtec.MainApp;
import br.com.preservtec.JDBC.LoginDAO;
import br.com.preservtec.model.Login;
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
import javafx.stage.Stage;

public class LoginOverviewController {

	@FXML
	private TextField userTextField;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private ImageView logoImageView;

	private LoginApp loginApp;
	private MainApp mainApp;
	private LoginDAO loginDAO;

	public LoginOverviewController() {
		loginDAO = new LoginDAO();
	}

	@FXML
	private void initialize() {
	}

	public void setLoginApp(LoginApp loginApp) {
		this.loginApp = loginApp;
	}

	@FXML
	private void handleEntrar() {

		if (isInputValid()) {
			Login login = loginDAO.verificaLogin(userTextField.getText().toString(),
					passwordTextField.getText().toString());
			// System.out.println(login.toString());

			if (!login.equals(null)) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Login");
				alert.setHeaderText("Tudo OK!");
				alert.setContentText("Login Efetuado com sucesso!!");
				Optional<ButtonType> result = alert.showAndWait();
				loginApp.showProcuracaoDialog(login);
				loginApp.getPrimaryStage().close();
			}

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
		} else {
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

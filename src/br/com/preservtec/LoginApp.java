package br.com.preservtec;

import java.io.IOException;

import br.com.preservtec.model.Login;
import br.com.preservtec.util.Strings;
import br.com.preservtec.view.LoginOverviewController;
import br.com.preservtec.view.ProcuracaoOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginApp extends Application {

	private Stage primaryStage;
	private AnchorPane anchorPane;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = stage;
		this.primaryStage.setResizable(false);
		this.primaryStage.setTitle(Strings.TIULO_LOGIN);
		this.primaryStage.getIcons().add(new Image(Strings.PATH_LOGO));
		
		initLayout();
	}

	
	/**
	 * Inicializa o LoginApp
	 */
	public void initLayout() {
		try {
			//Carrega o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LoginApp.class.getResource(Strings.LOGIN_FXML));
			anchorPane = (AnchorPane) loader.load();
			
			LoginOverviewController controller = loader.getController();
			controller.setLoginApp(this);
			
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void showProcuracaoDialog(Login login){
		try{
			FXMLLoader loaderRoot = new FXMLLoader();
			loaderRoot.setLocation(MainApp.class.getResource(Strings.ROOT_FXML));
			BorderPane rootLayout = (BorderPane) loaderRoot.load();

			Stage stageProcuracao = new Stage();
			stageProcuracao = stageProcuracao;
			stageProcuracao.setTitle(Strings.TIUTLO_MAIN);
			stageProcuracao.getIcons().add(new Image(Strings.PATH_LOGO));
			stageProcuracao.setMaximized(true);
			
			// Mostra a scene (cena) contendo o root layout.
			Scene scene = new Scene(rootLayout);
			stageProcuracao.setScene(scene);
			stageProcuracao.show();
			
			FXMLLoader loaderProcuracao = new FXMLLoader();
			loaderProcuracao.setLocation(MainApp.class.getResource(Strings.PROCURACAO_FXML));
			ScrollPane personOverview = (ScrollPane) loaderProcuracao.load();

			// Define o person overview dentro do root layout.
			rootLayout.setCenter(personOverview);

			ProcuracaoOverviewController controller = loaderProcuracao.getController();
			controller.setStage(stageProcuracao);
						
			controller.setLogin(login);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	public static void main (String [] args){
		launch(args);
	}
}

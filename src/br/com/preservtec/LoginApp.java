package br.com.preservtec;

import java.io.IOException;

import br.com.preservtec.util.Strings;
import br.com.preservtec.view.LoginOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginApp extends Application {

	private Stage primaryStage;
	private AnchorPane anchorPane;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = stage;
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
			loader.setLocation(LoginApp.class.getResource("view/LoginOverview.fxml"));
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
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	public static void main (String [] args){
		launch(args);
	}
}

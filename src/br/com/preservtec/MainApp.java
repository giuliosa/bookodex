package br.com.preservtec;

import java.io.IOException;

import br.com.preservtec.view.ProcuracaoOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author Giulio Sá
 * @version 1.0
 *
 */
public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Bookodex - Procuração");
		this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));
		this.primaryStage.setMaximized(true);
		
		initRootLayout();
		
		showProcuracaoOverview();
	}
	
	/**
     * Inicializa o root layout (layout base).
     */
	public void initRootLayout(){
		try {
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
         // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
		} catch (IOException e) {			
			 e.printStackTrace();
		}
	} 
	
	/**
     * Mostra o person overview dentro do root layout.
     */
    public void showProcuracaoOverview() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProcuracaoOverview.fxml"));
            ScrollPane personOverview = (ScrollPane) loader.load();

            // Define o person overview dentro do root layout.
            rootLayout.setCenter(personOverview);
            
            ProcuracaoOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna o palco principal.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}

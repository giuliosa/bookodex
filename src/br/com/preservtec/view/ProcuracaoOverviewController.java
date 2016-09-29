package br.com.preservtec.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.imageio.ImageIO;

import com.itextpdf.text.DocumentException;

import br.com.preservtec.MainApp;
import br.com.preservtec.model.Procuracao;
import br.com.preservtec.util.PDFCreator;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * O controlador para o procuracaoOverview. Prov� o layout principal para o
 * cadastro da procura��o Seu conteudo consiste em um splitPane para dividir a
 * tela em duas parte com a imagem do lado esquerdo e os campos a serem
 * preenchidos do lado direito
 * 
 * @author Giulio S�
 */

public class ProcuracaoOverviewController {

	@FXML
	private TextField pageTextField;
	@FXML
	private TextField dateTextField;
	@FXML
	private TextField cartorioTextField;
	@FXML
	private TextField outorganteTextField;
	@FXML
	private TextField outorgadoTextField;
	@FXML
	private ImageView imageView;
	@FXML
	private ScrollPane scrollMain;
	@FXML
	private ScrollPane scrollImage;

	private MainApp mainApp;
	private Procuracao procuracao;
	private DoubleProperty zoom;
	private Image image;
	private ArrayList<Procuracao> list;
	private PDFCreator pdf;

	public ProcuracaoOverviewController() {
		InvalidationListener listener = new InvalidationListener() {
			@Override
			public void invalidated(Observable arg0) {
				imageView.setFitWidth(zoom.get() * 4);
				imageView.setFitHeight(zoom.get() * 3);
				scrollImage.requestLayout();
			}
		};
		zoom = new SimpleDoubleProperty(400);
		zoom.addListener(listener);
	}

	@FXML
	private void initialize() {
	}

	/**
	 * � chamado pela aplica��o principal para dar uma refer�ncia de volta a si
	 * mesmo.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Evento para selecionar a imagem que ser� trabalhada
	 * 
	 * Seleciona apenas imagens com extens�o png e jpg
	 */

	@FXML
	private void handleFileChooser() {
		FileChooser fileChooser = new FileChooser();

		// Setar o filtro de extens�o
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterTIFF = new FileChooser.ExtensionFilter("TIF files (*.tif)", "*.TIF");

		fileChooser.getExtensionFilters().addAll(extFilterPNG, extFilterJPG, extFilterTIFF);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			this.image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageView.setImage(image);

			imageView.setFitWidth(zoom.get() * 4);
			imageView.setFitHeight(zoom.get() * 3);
			scrollImage.requestLayout();

			scrollImage.setPannable(true);
			scrollImage.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
				@Override
				public void handle(ScrollEvent event) {
					if (event.getDeltaY() > 0) {
						zoom.set(zoom.get() * 1.1);
					} else if (event.getDeltaY() < 0 && zoom.getValue() > 500) {
						zoom.set(zoom.get() / 1.1);
					}
					event.consume();
				}
			});
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Evento para bot�o de zoom in
	 */
	@FXML
	private void handleZoomPlus() {
		zoom.set(zoom.get() * 1.1);
	}

	/**
	 * Evento para bot�o de zoom out
	 */

	@FXML
	private void handleZoomMinus() {
		if (zoom.getValue() > 500) {
			zoom.set(zoom.get() / 1.1);
		}

	}

	/**
	 * Quando o usu�rio Clica em enviar
	 */
	@FXML
	private void handleEnviar(){
		list = new ArrayList<Procuracao>();
		if (isInputValid()) {

			procuracao = new Procuracao();
			procuracao.setBook("Livro P-64");
			procuracao.setPage(pageTextField.getText().toString());
			procuracao.setDate(dateTextField.getText().toString());
			procuracao.setCartorio(cartorioTextField.getText().toString());
			procuracao.setOutorgante(outorganteTextField.getText().toString());
			procuracao.setOutorgado(outorgadoTextField.getText().toString());

			pdf = new PDFCreator();
			pdf.criaDocumento(procuracao);
			
			//list.add(procuracao);

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Procura��o Cadastrada");
			alert.setHeaderText("Tudo OK!");
			alert.setContentText("Deseja cadastrar mais algum?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				pageTextField.setText("");
				dateTextField.setText("");
				cartorioTextField.setText("");
				outorganteTextField.setText("");
				outorgadoTextField.setText("");

				handleFileChooser();
			} else {
				alert.close();
			}
			/*pdf = new PDFCreator();
			pdf.criaDocumento(list);*/
		}
	}
	
	/**
	 * Valida a entrada do usu�rio nos campos de texto
	 * 
	 * @return true se a entrada � v�lida
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (pageTextField.getText() == null || pageTextField.getText().length() == 0) {
			errorMessage += "P�gina Inv�lida \n";
		}

		if (dateTextField.getText() == null || dateTextField.getText().length() == 0) {
			errorMessage += "Data Inv�lida \n";
		}

		if (cartorioTextField.getText() == null || cartorioTextField.getText().length() == 0) {
			errorMessage += "Cart�rio Inv�lido \n";
		}

		if (outorganteTextField.getText() == null || outorganteTextField.getText().length() == 0) {
			errorMessage += "Outorgante Inv�lido \n";
		}

		if (outorgadoTextField.getText() == null || outorgadoTextField.getText().length() == 0) {
			errorMessage += "Outorgado Inv�lido \n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Mosta a mensagem de erro
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos inv�lidos");
			alert.setHeaderText("Por favor, corrija os campos inv�lido");
			alert.setContentText(errorMessage);
			alert.showAndWait();

			return false;
		}
	}

}

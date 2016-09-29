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
 * O controlador para o procuracaoOverview. Provê o layout principal para o
 * cadastro da procuração Seu conteudo consiste em um splitPane para dividir a
 * tela em duas parte com a imagem do lado esquerdo e os campos a serem
 * preenchidos do lado direito
 * 
 * @author Giulio Sá
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
	 * É chamado pela aplicação principal para dar uma referência de volta a si
	 * mesmo.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Evento para selecionar a imagem que será trabalhada
	 * 
	 * Seleciona apenas imagens com extensão png e jpg
	 */

	@FXML
	private void handleFileChooser() {
		FileChooser fileChooser = new FileChooser();

		// Setar o filtro de extensão
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
	 * Evento para botão de zoom in
	 */
	@FXML
	private void handleZoomPlus() {
		zoom.set(zoom.get() * 1.1);
	}

	/**
	 * Evento para botão de zoom out
	 */

	@FXML
	private void handleZoomMinus() {
		if (zoom.getValue() > 500) {
			zoom.set(zoom.get() / 1.1);
		}

	}

	/**
	 * Quando o usuário Clica em enviar
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
			alert.setTitle("Procuração Cadastrada");
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
	 * Valida a entrada do usuário nos campos de texto
	 * 
	 * @return true se a entrada é válida
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (pageTextField.getText() == null || pageTextField.getText().length() == 0) {
			errorMessage += "Página Inválida \n";
		}

		if (dateTextField.getText() == null || dateTextField.getText().length() == 0) {
			errorMessage += "Data Inválida \n";
		}

		if (cartorioTextField.getText() == null || cartorioTextField.getText().length() == 0) {
			errorMessage += "Cartório Inválido \n";
		}

		if (outorganteTextField.getText() == null || outorganteTextField.getText().length() == 0) {
			errorMessage += "Outorgante Inválido \n";
		}

		if (outorgadoTextField.getText() == null || outorgadoTextField.getText().length() == 0) {
			errorMessage += "Outorgado Inválido \n";
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

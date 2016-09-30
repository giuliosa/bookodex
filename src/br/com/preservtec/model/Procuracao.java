package br.com.preservtec.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe Model para uma procuração
 * 
 * @author Giulio Sá
 */

public class Procuracao {

	private final IntegerProperty id;
	private final StringProperty outorgante;
	private final StringProperty outorgado;
	private final StringProperty page;
	private final StringProperty date;
	private final StringProperty cartorio;
	private final StringProperty book;	
	private final ObjectProperty<Login> login;
	//private final ObjectProperty<LocalDate> dateProcuracao;

	/**
	 * Construtor Padrão
	 */
	public Procuracao() {
		this(0, null, null, null, null, null, null, null,null);

	}

	public Procuracao(int id, String page, String date, String cartorio, String book, String notes, String outorgante,
			String outorgado, Login login) {
		this.id = new SimpleIntegerProperty(id);
		this.outorgante = new SimpleStringProperty(outorgante);
		this.outorgado = new SimpleStringProperty(outorgado);
		this.page = new SimpleStringProperty(page);
		this.date = new SimpleStringProperty(date);
		this.cartorio = new SimpleStringProperty(cartorio);
		this.book = new SimpleStringProperty(book);
		this.login = new SimpleObjectProperty<Login>(login);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id.set(id);
	}

	public String getOutorgante() {
		return outorgante.get();
	}

	public void setOutorgante(String outorgante) {
		this.outorgante.set(outorgante);
	}

	public String getOutorgado() {
		return outorgado.get();
	}

	public void setOutorgado(String outorgado) {
		this.outorgado.set(outorgado);
	}

	public String getPage() {
		return page.get();
	}

	public void setPage(String page) {
		this.page.set(page);
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date.set(date);
	}

	public String getCartorio() {
		return cartorio.get();
	}

	public void setCartorio(String cartorio) {
		this.cartorio.set(cartorio);
	}

	public String getBook() {
		return book.get();
	}

	public void setBook(String book) {
		this.book.set(book);
	}
	
	public Login getLogin(){
		return login.get();
	}
	
	public void setLogin(Login login){
		this.login.set(login);
	}

	@Override
	public String toString() {
		return "Procuração\n" 
				+ "Página: " + getPage() + "\n"  
				+ "Data: " + getDate() +  "\n"
				+ "Cartório: " + getCartorio() + "\n" 
				+ "Outorgante: " + getOutorgante() + "\n"
				+ "Outorgado: " + getOutorgado() + "\n"
				+ "Operador: " + getLogin().getNomeLogin();
		

	}

}

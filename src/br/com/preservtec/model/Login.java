package br.com.preservtec.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Login {

	private final IntegerProperty idLogin;
	private final StringProperty userLogin;
	private final StringProperty passwordLogin;
	private final StringProperty nivelLogin;
	private final StringProperty nomeLogin;

	public Login() {
		this(null, null, null, null);
	}

	public Login(String userLogin, String passwordLogin, String nivelLogin, String nomeLogin) {
		this.idLogin = new SimpleIntegerProperty();
		this.userLogin = new SimpleStringProperty(userLogin);
		this.passwordLogin = new SimpleStringProperty(passwordLogin);
		this.nivelLogin = new SimpleStringProperty(nivelLogin);
		this.nomeLogin = new SimpleStringProperty(nomeLogin);
	}

	public Integer getIdLogin() {
		return idLogin.get();
	}

	public String getUserLogin() {
		return userLogin.get();
	}

	public void setUserLogin(String userLogin) {
		this.userLogin.set(userLogin);
	}

	public String getPasswordLogin() {
		return passwordLogin.get();
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin.set(passwordLogin);
	}
	
	public String getNivelLogin() {
		return nivelLogin.get();
	}

	public void setNivelLogin(String nivelLogin) {
		this.nivelLogin.set(nivelLogin);
	}

	public String getNomeLogin() {
		return nomeLogin.get();
	}

	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin.set(nomeLogin);
	}
}

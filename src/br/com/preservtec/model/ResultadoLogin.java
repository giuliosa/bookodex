package br.com.preservtec.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ResultadoLogin {
	private final IntegerProperty id;
	private final ObjectProperty<Login> login;
	private final ObjectProperty<LocalDate> data;
	private final IntegerProperty resultado;

	public ResultadoLogin() {
		this(null, null, null, null);
	}

	public ResultadoLogin(Integer id, Login login, LocalDate data, Integer resultado) {
		this.id = new SimpleIntegerProperty(id);
		this.login = new SimpleObjectProperty<Login>(login);
		this.data = new SimpleObjectProperty<LocalDate>(data);
		this.resultado = new SimpleIntegerProperty(resultado);
	}

	public int getId() {
		return this.id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public Login getLogin() {
		return this.login.get();
	}

	public void setLogin(Login login) {
		this.login.set(login);
	}

	public LocalDate getData() {
		return this.data.get();
	}

	public void setData(LocalDate data) {
		this.data.set(data);
	}

	public int getResultado() {
		return this.resultado.get();
	}

	public void setResultado(int resultado) {
		this.resultado.set(resultado);
	}
}

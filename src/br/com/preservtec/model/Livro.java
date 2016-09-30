package br.com.preservtec.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Livro {
	
	private final IntegerProperty id;
	private final StringProperty nome;
	private final ObjectProperty<LocalDate> dataInicial, dataFinal;
	
	public Livro(){
		this(null,null,null,null);
	}
	
	public Livro(Integer id, String nome, LocalDate dataInicial, LocalDate dataFinal){
		this.id = new SimpleIntegerProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.dataInicial = new SimpleObjectProperty<LocalDate>(dataInicial);
		this.dataFinal = new SimpleObjectProperty<LocalDate>(dataFinal);
	}

	public int getId() {
		return this.id.get();
	}
	
	public void setId(int id){
		this.id.set(id);
	}

	public String getNome() {
		return this.nome.get();
	}
	
	public void setNome(String nome){
		this.nome.set(nome);
	}

	public LocalDate getDataInicial() {
		return this.dataInicial.get();
	}
	
	public void setDataInicial(LocalDate data){
		this.dataInicial.set(data);
	}

	public LocalDate getDataFinal() {
		return this.dataFinal.get();
	}
	
	public void setDataFinal(LocalDate data){
		this.dataFinal.set(data);
	}
	
	
}

/**
 * 
 */
package br.edu.unoesc.passagens.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Luiz Eduardo Fachin
 *
 */
public class Cidade {
	
	private StringProperty nome;
	private Integer id;

	/**
	 * 
	 */
	public Cidade() {
	}

	public Cidade(String nome) {
		this.nome = new SimpleStringProperty(nome);
	}
	
	public Cidade(String nome, Integer codigo) {
		this.nome = new SimpleStringProperty(nome);
		this.id = codigo;
	}

	public StringProperty getNome() {
		return nome;
	}

	public void setNome(StringProperty nome) {
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer codigo) {
		this.id = codigo;
	}
	
	@Override
	public String toString() {
		return nome.get();
	}
}
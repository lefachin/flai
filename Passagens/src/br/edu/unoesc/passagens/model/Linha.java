/**
 * 
 */
package br.edu.unoesc.passagens.model;

import java.time.LocalTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Luiz Eduardo Fachin
 *
 */
public class Linha {

	private StringProperty nome, dia , tempo;
	private ObjectProperty<Cidade> origem, destino;
	private DoubleProperty valor;
	private ObjectProperty<LocalTime> horarioChegada, horarioSaida;
	private Integer id;
	
	/**
	 * 
	 */
	public Linha() {
	}
	
	public Linha(String nome, String dia, Cidade origem, Cidade destino,
			Double valor, LocalTime horarioSaida,LocalTime horarioChegada, String tempo) {
		this.nome = new SimpleStringProperty(nome);
		this.dia = new SimpleStringProperty(dia);
		this.origem = new SimpleObjectProperty<Cidade>(origem);
		this.destino = new SimpleObjectProperty<Cidade>(destino);
		this.valor = new SimpleDoubleProperty(valor);
		this.horarioSaida = new SimpleObjectProperty<LocalTime>(horarioSaida);
		this.horarioChegada = new SimpleObjectProperty<LocalTime>(horarioChegada);
		this.tempo = new SimpleStringProperty(tempo);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public StringProperty nomeProperty() {
		return nome;
	}
	
	public String getDia() {
		return dia.get();
	}

	public void setDia(String dia) {
		this.dia.set(dia);
	}
	
	public StringProperty diaProperty() {
		return dia;
	}

	public Cidade getOrigem() {
		return origem.get();
	}

	public void setOrigem(Cidade origem) {
		this.origem.set(origem);
	}

	public ObjectProperty<Cidade> origemProperty(){
		return origem;
	}
	
	public Cidade getDestino() {
		return destino.get();
	}

	public void setDestino(Cidade destino) {
		this.destino.set(destino);
	}
	
	public ObjectProperty<Cidade> destinoProperty(){
		return destino;
	}

	public LocalTime getHorarioChegada() {
		return horarioChegada.get();
	}

	public void setHorarioChegada(LocalTime horarioChegada) {
		this.horarioChegada.set(horarioChegada);
	}
	
	public ObjectProperty<LocalTime> horarioChegadaProperty(){
		return horarioChegada;
	}

	public LocalTime getHorarioSaida() {
		return horarioSaida.get();
	}

	public void setHorarioSaida(LocalTime horarioSaida) {
		this.horarioSaida.set(horarioSaida);
	}

	public ObjectProperty<LocalTime> horarioSaidaProperty(){
		return horarioSaida;
	}
	
	public Double getValor() {
		return valor.get();
	}
	
	public void setValor(Double valor) {
		this.valor.set(valor);
	}
	
	public DoubleProperty valorProperty(){
		return valor;
	}

	public String getTempo() {
		return tempo.get();
	}

	public void setTempo(String tempo) {
		this.tempo.set(tempo);
	}
	
	public StringProperty tempoProperty(){
		return tempo;
	}
}

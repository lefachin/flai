package br.edu.unoesc.passagens.dao;

import java.sql.Connection;

import br.edu.unoesc.passagens.model.Cidade;
import javafx.collections.ObservableList;

public interface CidadeDAO extends GenericDAO<Cidade>{
	
	public Cidade buscarPorNome(String nome);
	public void buscarNomesCidades(Connection connection, ObservableList<Cidade> observableList);
}

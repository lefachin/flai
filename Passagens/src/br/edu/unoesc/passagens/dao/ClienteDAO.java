package br.edu.unoesc.passagens.dao;

import br.edu.unoesc.passagens.model.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente>{
	
	public Cliente buscarPorCPF(String cpf);

}

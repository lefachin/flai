package br.edu.unoesc.passagens.dao;

import br.edu.unoesc.passagens.model.Vendedor;

public interface VendedorDAO extends GenericDAO<Vendedor> {
	
	public boolean validarPorUsuario (String usuario, String senha);
	public String buscarNomePorUsuario (String usuario);
	public Vendedor buscarPorUsuario (String usuario);
}
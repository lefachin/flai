package br.edu.unoesc.passagens.daofactory;

import br.edu.unoesc.passagens.dao.CidadeDAO;
import br.edu.unoesc.passagens.dao.ClienteDAO;
import br.edu.unoesc.passagens.dao.LinhaDAO;
import br.edu.unoesc.passagens.dao.PassagemDAO;
import br.edu.unoesc.passagens.dao.VendedorDAO;

public interface AbstractDAOFactory {
	
	VendedorDAO vendedorDAO();
	CidadeDAO cidadeDAO();
	LinhaDAO linhaDAO();
	ClienteDAO clienteDAO();
	PassagemDAO passagemDAO();
}

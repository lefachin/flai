package br.edu.unoesc.passagens.daofactory;

import br.edu.unoesc.passagens.dao.CidadeDAO;
import br.edu.unoesc.passagens.dao.CidadeDAOJDBC;
import br.edu.unoesc.passagens.dao.ClienteDAO;
import br.edu.unoesc.passagens.dao.ClienteDAOJDBC;
import br.edu.unoesc.passagens.dao.LinhaDAO;
import br.edu.unoesc.passagens.dao.LinhaDAOJDBC;
import br.edu.unoesc.passagens.dao.PassagemDAO;
import br.edu.unoesc.passagens.dao.PassagemDAOJDBC;
import br.edu.unoesc.passagens.dao.VendedorDAO;
import br.edu.unoesc.passagens.dao.VendedorDAOJDBC;

public class DAOFactoryJDBC implements AbstractDAOFactory{

	@Override
	public VendedorDAO vendedorDAO() {
		return new VendedorDAOJDBC();
	}
	@Override
	public CidadeDAO cidadeDAO() {
		return new CidadeDAOJDBC();
	}
	@Override
	public LinhaDAO linhaDAO() {
		return new LinhaDAOJDBC();
	}
	@Override
	public ClienteDAO clienteDAO() {
		return new ClienteDAOJDBC();
	}
	@Override
	public PassagemDAO passagemDAO() {
		return new PassagemDAOJDBC();
	}	
}

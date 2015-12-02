package br.edu.unoesc.passagens.daofactory;

import br.edu.unoesc.passagens.dao.CidadeDAO;
import br.edu.unoesc.passagens.dao.ClienteDAO;
import br.edu.unoesc.passagens.dao.LinhaDAO;
import br.edu.unoesc.passagens.dao.PassagemDAO;
import br.edu.unoesc.passagens.dao.VendedorDAO;

public class DAOFactory {

	private static DAOFactory factory;
	
	private static final String tipoSistema = "BANCO";
	
	private static AbstractDAOFactory daoFactory;
	
	public static DAOFactory get(){
		if(factory == null){
			factory = new DAOFactory();
		}
		if(tipoSistema.equals("BANCO")){
			daoFactory = new DAOFactoryJDBC();
		}
		return factory;
	}
	public VendedorDAO vendedorDAO(){
		return daoFactory.vendedorDAO();
	}
	public CidadeDAO cidadeDAO(){
		return daoFactory.cidadeDAO();
	}
	public LinhaDAO linhaDAO(){
		return daoFactory.linhaDAO();
	}
	public PassagemDAO passagemDAO(){
		return daoFactory.passagemDAO();
	}
	public ClienteDAO clienteDAO() {
		return daoFactory.clienteDAO();
	}
}

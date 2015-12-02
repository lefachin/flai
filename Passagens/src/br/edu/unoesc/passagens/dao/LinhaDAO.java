/**
 * 
 */
package br.edu.unoesc.passagens.dao;

import br.edu.unoesc.passagens.model.Cidade;
import br.edu.unoesc.passagens.model.Linha;

/**
 * @author Luiz Fachin
 *
 */
public interface LinhaDAO extends GenericDAO<Linha>{

	/**
	 * 
	 */
	public Linha buscaLinha(Cidade origem, Cidade destino, String dia);

}

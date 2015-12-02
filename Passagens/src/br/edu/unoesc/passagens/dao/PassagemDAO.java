package br.edu.unoesc.passagens.dao;

import java.time.LocalDate;
import java.util.List;

import br.edu.unoesc.passagens.model.Passagem;

public interface PassagemDAO extends GenericDAO<Passagem>{
	
	public List<Passagem> retornaTodasPassagemPorDataViagem(LocalDate data_viagem);

}

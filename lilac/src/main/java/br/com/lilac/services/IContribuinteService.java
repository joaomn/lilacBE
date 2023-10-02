package br.com.lilac.services;

import java.util.List;
import java.util.Optional;



import br.com.lilac.entitys.Contribuinte;
import br.com.lilac.entitys.DTOS.ContribuinteDTO;
import br.com.lilac.services.exceptions.ContribuinteCadastradoException;
import br.com.lilac.services.exceptions.NotFoundLilacException;

public interface IContribuinteService {
	public void store(Contribuinte contribuinte) throws ContribuinteCadastradoException ;
	List<Contribuinte> getAll();
	Optional<Contribuinte> getByID(Long id);
	public void update(Long id, ContribuinteDTO contribuinte) throws NotFoundLilacException;
	public void delete(Long id) throws NotFoundLilacException;

}

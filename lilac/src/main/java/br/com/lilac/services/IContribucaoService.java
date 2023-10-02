package br.com.lilac.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.lilac.entitys.Contribuicao;
import br.com.lilac.entitys.DTOS.ContribuicaoDTO;
import br.com.lilac.services.exceptions.ContribuinteCadastradoException;
import br.com.lilac.services.exceptions.NotFoundLilacException;

public interface IContribucaoService {
	public void store(Contribuicao contribuicao) throws ContribuinteCadastradoException ;
	List<Contribuicao> getAll();
	Optional<Contribuicao> getByID(Long id);
	public void update(Long id, ContribuicaoDTO contribuicao) throws NotFoundLilacException;
	public void delete(Long id) throws NotFoundLilacException;
	List<Contribuicao> getByData(LocalDate data) throws NotFoundLilacException;

}

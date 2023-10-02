package br.com.lilac.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lilac.entitys.Contribuinte;
import br.com.lilac.entitys.DTOS.ContribuinteDTO;
import br.com.lilac.repositorys.ContribuinteRepository;
import br.com.lilac.services.exceptions.ContribuinteCadastradoException;
import br.com.lilac.services.exceptions.NotFoundLilacException;

@Service
public class ContribuinteServiceImpl implements IContribuinteService {

	@Autowired
	ContribuinteRepository repository;

	@Override
	public void store(Contribuinte contribuinte) throws ContribuinteCadastradoException {
		Optional<Contribuinte> objcontribuinte = repository.findByNome(contribuinte.getNome());

		if (objcontribuinte.isPresent()) {
			throw new ContribuinteCadastradoException("Participantes Já cadastrados");
		}

		this.repository.save(contribuinte);

	}

	@Override
	public List<Contribuinte> getAll() {
		try {
			return this.repository.findAll();
		} catch (Exception e) {
			e.getMessage();
			return null;
		}

	}

	@Override
	public Optional<Contribuinte> getByID(Long id) {
		try {
			return this.repository.findById(id);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@Override
	public void update(Long id, ContribuinteDTO contribuinte) throws NotFoundLilacException {
		try {
			Optional<Contribuinte> objContribuinte = this.repository.findById(id);
			if(objContribuinte.isPresent()) {
				Contribuinte obj = objContribuinte.get();
				if(contribuinte.getNome() != null) {
					obj.setNome(contribuinte.getNome());
				}
				
					obj.setStatus(contribuinte.isStatus());	
					
					
				
				
				 this.repository.save(obj);
			}
		} catch (Exception e) {
			throw new NotFoundLilacException("Participante não encontrado ou campos mal preenchidos");
		}
	}

	@Override
	public void delete(Long id) throws NotFoundLilacException {
		try {
		Optional<Contribuinte> objContribuinte = this.repository.findById(id);
		
		if(objContribuinte.isEmpty()) {
			throw new NotFoundLilacException("Participante não cadastrado");
		}
		
		this.repository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundLilacException("Erro interno ao deletar participante");
		}

	}

}

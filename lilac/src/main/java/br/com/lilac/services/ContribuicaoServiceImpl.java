package br.com.lilac.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lilac.entitys.Contribuicao;
import br.com.lilac.entitys.DTOS.ContribuicaoDTO;
import br.com.lilac.repositorys.ContribuicaoRepository;
import br.com.lilac.services.exceptions.ContribuinteCadastradoException;
import br.com.lilac.services.exceptions.NotFoundLilacException;

@Service
public class ContribuicaoServiceImpl implements IContribucaoService {

	@Autowired
	ContribuicaoRepository repository;

	@Override
	public void store(Contribuicao contribuicao) throws ContribuinteCadastradoException {
		try {
			this.repository.save(contribuicao);
		} catch (Exception e) {
			throw new ContribuinteCadastradoException("Ocorreu um erro ao inserir a contribuição");
		}

	}

	@Override
	public List<Contribuicao> getAll() {
		try {
			return this.repository.findAll();
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@Override
	public Optional<Contribuicao> getByID(Long id) {
		try {
			return this.repository.findById(id);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@Override
	public void update(Long id, ContribuicaoDTO contribuicao) throws NotFoundLilacException {
		try {
			Optional<Contribuicao> objContribuicao = this.repository.findById(id);
			if (objContribuicao.isPresent()) {
				Contribuicao obj = objContribuicao.get();
				
				if(contribuicao.getValor() != 0) {
					obj.setValor(contribuicao.getValor());
				}

				this.repository.save(obj);
			}
		} catch (Exception e) {
			throw new NotFoundLilacException("Contribuicao não encontrada ou campos mal preenchidos");
		}
	}

	@Override
	public void delete(Long id) throws NotFoundLilacException {
		try {
			Optional<Contribuicao> objContribuicao = this.repository.findById(id);

			if (objContribuicao.isEmpty()) {
				throw new NotFoundLilacException("Participante não cadastrado");
			}

			this.repository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundLilacException("Erro interno ao deletar participante");
		}

	}

	@Override
	public List<Contribuicao> getByData(LocalDate data) throws NotFoundLilacException {
		try {
			return this.repository.findAllByData(data);
		} catch (Exception e) {
			throw new NotFoundLilacException("Erro ao obter contribuições por data");
		}

	}
	}




package br.com.lilac.repositorys;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lilac.entitys.Contribuicao;
import br.com.lilac.entitys.Contribuinte;

@Repository
public interface ContribuicaoRepository extends JpaRepository<Contribuicao, Long>{
	
	 Optional<Contribuicao> findById(Long id);
	 Optional<Contribuicao> findByValor(double valor);
	 Optional<Contribuicao> findByData(LocalDate data);
	 Optional<Contribuicao> findByContribuinte (Contribuinte contribuinte);
	 List<Contribuicao> findAllByData(LocalDate data);
	

}

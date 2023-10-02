package br.com.lilac.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lilac.entitys.Contribuinte;

@Repository
public interface ContribuinteRepository extends JpaRepository<Contribuinte, Long>{
	
	Optional<Contribuinte>  findById(Long id);
	Optional<Contribuinte> findByNome(String nome);

}

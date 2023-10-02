package br.com.lilac.repositorys;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lilac.entitys.Caixa;


@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {
	
	Optional<Caixa> findByData (YearMonth data);
	List<Caixa> findAllByData(YearMonth data);

}

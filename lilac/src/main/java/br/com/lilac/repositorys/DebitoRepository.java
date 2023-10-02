package br.com.lilac.repositorys;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lilac.entitys.Debito;

@Repository
public interface DebitoRepository extends JpaRepository<Debito, Long> {
	
	Optional<Debito> findByData(YearMonth data);
	List<Debito> findAllByData(YearMonth data);

}

package br.com.lilac.entitys;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.lilac.entitys.DTOS.ContribuicaoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Contribuicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	@NotNull(message = "o campo data deve ser preenchido")	
	private LocalDate data;
	
	@NotNull(message = "O campo valor deve ser preenchido")
	private double valor;
	
	@ManyToOne
	private Contribuinte contribuinte;
	
	public ContribuicaoDTO toDTO() {
		return new ContribuicaoDTO(this);
	}

	public Contribuicao(ContribuicaoDTO contribuicao) {
		this.id = contribuicao.getId();
		this.data = contribuicao.getData();
		this.valor = contribuicao.getValor();
		this.contribuinte = contribuicao.getContribuinte();
	}
}

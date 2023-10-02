package br.com.lilac.entitys;

import java.time.YearMonth;

import br.com.lilac.entitys.DTOS.DebitoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Debito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@PastOrPresent(message = "a data não pode ser futura")
	@NotNull(message = "o campo data deve ser preenchido")
	private YearMonth data;
	
	@NotBlank(message = "O campo valor deve ser preenchido")
	private double valor;

	public DebitoDTO toDto() {
		return new DebitoDTO(this);
	}
	
	public Debito(DebitoDTO debito) {
		this.id = debito.getId();
		this.data = debito.getData();
		this.valor = debito.getValor();
	}
}

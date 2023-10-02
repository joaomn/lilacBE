package br.com.lilac.entitys.DTOS;

import java.time.YearMonth;

import br.com.lilac.entitys.Debito;
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
public class DebitoDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@PastOrPresent(message = "a data não pode ser futura")
	@NotNull(message = "o campo data deve ser preenchido")
	private YearMonth data;
	
	@NotBlank(message = "O campo valor deve ser preenchido")
	private double valor;
	
	private String message;

	public DebitoDTO(Debito debito) {
		this.id = debito.getId();
		this.data = debito.getData();
		this.valor = debito.getValor();
	}
}

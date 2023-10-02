package br.com.lilac.entitys.DTOS;

import java.time.YearMonth;

import br.com.lilac.entitys.Caixa;
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
public class CaixaDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@PastOrPresent(message = "a data não pode ser futura")
	@NotNull(message = "o campo data deve ser preenchido")
	private YearMonth data;
	
	@NotBlank(message = "O campo valor deve ser preenchido")
	private double valor;
	
	private String message;
	
	public CaixaDTO(Caixa caixa) {
		this.id = caixa.getId();
		this.data = caixa.getData();
		this.valor = caixa.getValor();
	}
}

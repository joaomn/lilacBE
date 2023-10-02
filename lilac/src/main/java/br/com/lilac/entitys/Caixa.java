package br.com.lilac.entitys;

import java.time.YearMonth;

import br.com.lilac.entitys.DTOS.CaixaDTO;
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
public class Caixa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@PastOrPresent(message = "a data não pode ser futura")
	@NotNull(message = "o campo data deve ser preenchido")
	private YearMonth data;

	@NotBlank(message = "O campo valor deve ser preenchido")
	private double valor;

	public CaixaDTO toDto() {
		return new CaixaDTO(this);

	}

	public Caixa(CaixaDTO caixa) {
		this.id = caixa.getId();
		this.data = caixa.getData();
		this.valor = caixa.getValor();

	}
}

package br.com.lilac.entitys.DTOS;

import br.com.lilac.entitys.Contribuicao;
import br.com.lilac.entitys.Contribuinte;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContribuinteDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "o campo nome deve ser preenchido e ter no maximo 100 caracteres")
	@Column(length = 100)
	private String nome;
	@NotNull(message = "o campo status é obrigatório")
	private boolean status;
	
	
	
	private String message;

	
	public ContribuinteDTO(Contribuinte contribuinte) {
		this.id = contribuinte.getId();
		this.nome = contribuinte.getNome();
		this.status = contribuinte.isStatus();
		
		
	}
}

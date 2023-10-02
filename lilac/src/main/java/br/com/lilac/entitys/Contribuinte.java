package br.com.lilac.entitys;

import java.util.List;

import br.com.lilac.entitys.DTOS.ContribuinteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
@NoArgsConstructor
public class Contribuinte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "o campo nome deve ser preenchido e ter no maximo 100 caracteres")
	@Column(length = 100)
	private String nome;
	@NotNull(message = "o campo status é obrigatório")
	private boolean status;
	

	
	public ContribuinteDTO toDTO() {
		return new ContribuinteDTO(this);
	}
	
	public Contribuinte(ContribuinteDTO dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.status = dto.isStatus();
		
	}

}

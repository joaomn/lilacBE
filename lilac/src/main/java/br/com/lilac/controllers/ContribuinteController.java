package br.com.lilac.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lilac.entitys.Contribuinte;
import br.com.lilac.entitys.DTOS.ContribuinteDTO;
import br.com.lilac.services.ContribuinteServiceImpl;
import br.com.lilac.services.exceptions.NotFoundLilacException;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/participante")
public class ContribuinteController {

	@Autowired
	ContribuinteServiceImpl service;

	@ApiOperation(value = "persisitir no banco")
	@PostMapping
	public ResponseEntity<ContribuinteDTO> store(@Valid @RequestBody ContribuinteDTO contribuinteDTO) {

		Contribuinte contirbuinte = new Contribuinte(contribuinteDTO);

		try {
			this.service.store(contirbuinte);
			return ResponseEntity.status(HttpStatus.CREATED).body(contribuinteDTO);
		} catch (Exception e) {
			contribuinteDTO.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contribuinteDTO);

		}

	}

	@GetMapping
	public ResponseEntity<List<ContribuinteDTO>> index() {

		List<ContribuinteDTO> contribuintesList = new ArrayList<>();

		List<Contribuinte> contribuinte = this.service.getAll();

		if (!contribuinte.isEmpty()) {
			contribuintesList = contribuinte.stream().map(cliente -> cliente.toDTO()).collect(Collectors.toList());
		}
		return ResponseEntity.status(HttpStatus.OK).body(contribuintesList);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContribuinteDTO> show(@PathVariable Long id){
		Optional<Contribuinte> objContribuinte = this.service.getByID(id);
		
		if(objContribuinte.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(objContribuinte.get().toDTO());
			
		}
		
		ContribuinteDTO dto = new ContribuinteDTO();
		dto.setMessage("Participante não encontrado");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ContribuinteDTO> delete(@PathVariable Long id){
		
		ContribuinteDTO dto = new ContribuinteDTO();
		dto.setId(id);
		
		try {

			this.service.delete(dto.getId());
			dto.setMessage("Excluido com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		} catch (Exception e) {
			dto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ContribuinteDTO> update(@Valid @PathVariable Long id, @RequestBody ContribuinteDTO dto) throws NotFoundLilacException {

		Optional<Contribuinte> cliente = this.service.getByID(id);

		if (cliente.isPresent()) {
			service.update(id, dto);

			return ResponseEntity.status(HttpStatus.OK).body(cliente.get().toDTO());
		}

		ContribuinteDTO clienteDTO = new ContribuinteDTO();
		clienteDTO.setMessage("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);

	}

}

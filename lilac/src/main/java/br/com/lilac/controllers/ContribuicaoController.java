package br.com.lilac.controllers;

import java.time.LocalDate;
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

import br.com.lilac.entitys.Contribuicao;
import br.com.lilac.entitys.DTOS.ContribuicaoDTO;
import br.com.lilac.services.ContribuicaoServiceImpl;
import br.com.lilac.services.exceptions.NotFoundLilacException;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/contribuicao")
public class ContribuicaoController {
	
	@Autowired
	ContribuicaoServiceImpl service;
	
	@ApiOperation(value = "persisitir no banco")
	@PostMapping
	public ResponseEntity<ContribuicaoDTO> store(@Valid @RequestBody ContribuicaoDTO contribuinteDTO) {

		Contribuicao contirbuinte = new Contribuicao(contribuinteDTO);

		try {
			
			this.service.store(contirbuinte);
			return ResponseEntity.status(HttpStatus.CREATED).body(contribuinteDTO);
		} catch (Exception e) {
			contribuinteDTO.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contribuinteDTO);

		}

	}
	
	@GetMapping
	public ResponseEntity<List<ContribuicaoDTO>> index() {

		List<ContribuicaoDTO> contribuintesList = new ArrayList<>();

		List<Contribuicao> contribuinte = this.service.getAll();

		if (!contribuinte.isEmpty()) {
			contribuintesList = contribuinte.stream().map(cliente -> cliente.toDTO()).collect(Collectors.toList());
		}
		return ResponseEntity.status(HttpStatus.OK).body(contribuintesList);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContribuicaoDTO> show(@PathVariable Long id){
		Optional<Contribuicao> objContribuinte = this.service.getByID(id);
		
		if(objContribuinte.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(objContribuinte.get().toDTO());
			
		}
		
		ContribuicaoDTO dto = new ContribuicaoDTO();
		dto.setMessage("Participante não encontrado");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ContribuicaoDTO> delete(@PathVariable Long id){
		
		ContribuicaoDTO dto = new ContribuicaoDTO();
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
	public ResponseEntity<ContribuicaoDTO> update(@Valid @PathVariable Long id, @RequestBody ContribuicaoDTO dto) throws NotFoundLilacException {

		Optional<Contribuicao> cliente = this.service.getByID(id);

		if (cliente.isPresent()) {
			service.update(id, dto);

			return ResponseEntity.status(HttpStatus.OK).body(cliente.get().toDTO());
		}

		ContribuicaoDTO clienteDTO = new ContribuicaoDTO();
		clienteDTO.setMessage("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);

	}
	
	@GetMapping("/buscar/{date}")
	public ResponseEntity<List<ContribuicaoDTO>> showByData(@PathVariable LocalDate date){
		
		
		List<ContribuicaoDTO> contribuintesList = new ArrayList<>();

		List<Contribuicao> contribuinte;
		try {
			contribuinte = this.service.getByData(date);
			if (!contribuinte.isEmpty()) {
			contribuintesList = contribuinte.stream().map(cliente -> cliente.toDTO()).collect(Collectors.toList());
		}
		return ResponseEntity.status(HttpStatus.OK).body(contribuintesList);
			
		} catch (NotFoundLilacException e) {
		return null;
		}

		
	}
	


}

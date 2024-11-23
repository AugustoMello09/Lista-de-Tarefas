package io.gitHub.AugustoMello09.ListTasks.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.MoveTarefaRecord;
import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaDTO;
import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaRecord;
import io.gitHub.AugustoMello09.ListTasks.servicies.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Tarefas endpoint")
@RestController
@RequestMapping(value = "/v1/tarefas")
@RequiredArgsConstructor
public class TarefaController {

	private final TarefaService service;
	
	@Operation(summary = "Busca uma Tarefa no banco de dados por ID. ")
	@GetMapping(value = "/{id}")
	public ResponseEntity<TarefaDTO> findById(@PathVariable Long id) {
		var response = service.findById(id);
		return ResponseEntity.ok().body(response);
	}
	
	@Operation(summary = "Busca todas as tarefas no banco de dados. ")
	@GetMapping
	public ResponseEntity<List<TarefaDTO>> listAll(){
		var response = service.listAll();
		return ResponseEntity.ok().body(response);
	}
	
	@Operation(summary = "Cria uma Tarefa no banco de dados. ")
	@PostMapping
	public ResponseEntity<TarefaDTO> create(@Valid @RequestBody TarefaRecord tarefa){
		var newObj = service.create(tarefa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newObj.getId())
				.toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@Operation(summary = "Atualiza uma Tarefa no banco de dados. ")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody TarefaRecord tarefa,@PathVariable Long id){
		service.update(tarefa, id);
		return ResponseEntity.ok().build();
	}
	
	@Operation(summary = "Deleta uma Tarefa no banco de dados. ")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Habilita mover a tarefa dentro da lista trocando sua posição. ")
	@PostMapping("/move")
	public ResponseEntity<Void> moveTarefa(@RequestBody MoveTarefaRecord moveDTO) {
	    service.moveTarefa(moveDTO.sourceIndex(), moveDTO.destinationIndex());
	    return ResponseEntity.ok().build();
	}

}

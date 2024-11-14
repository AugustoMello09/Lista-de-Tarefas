package io.gitHub.AugustoMello09.ListTasks.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaDTO;
import io.gitHub.AugustoMello09.ListTasks.servicies.TarefaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/v1/tarefas")
@RequiredArgsConstructor
public class TarefaController {

	private final TarefaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TarefaDTO> findById(@PathVariable Long id) {
		var response = service.findById(id);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<TarefaDTO>> listAll(){
		var response = service.listAll();
		return ResponseEntity.ok().body(response);
	}

}

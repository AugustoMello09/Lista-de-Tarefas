package io.gitHub.AugustoMello09.ListTasks.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaDTO;
import io.gitHub.AugustoMello09.ListTasks.provider.TarefaDTOProvider;
import io.gitHub.AugustoMello09.ListTasks.servicies.TarefaService;

@ExtendWith(MockitoExtension.class)
public class TarefaControllerTest {

	private static final long ID = 1L;

	@InjectMocks
	private TarefaController controller;

	@Mock
	private TarefaService service;

	// private TarefaProvider tarefaProvider;
	private TarefaDTOProvider tarefaDTOProvider;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		// tarefaProvider = new TarefaProvider();
		tarefaDTOProvider = new TarefaDTOProvider();
		controller = new TarefaController(service);
	}

	@DisplayName("Deve retornar uma tarefa. ")
	@Test
	public void shouldControllerReturnFindByIdWithSuccess() {
		TarefaDTO tarefaDTO = tarefaDTOProvider.criar();
		when(service.findById(ID)).thenReturn(tarefaDTO);
		var response = controller.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(TarefaDTO.class, response.getBody().getClass());
	}

	@DisplayName("Deve retornar uma Lista de tarefas. ")
	@Test
	public void shouldReturnListTarefaDTO() {
		List<TarefaDTO> tarefas = new ArrayList<>();
		when(service.listAll()).thenReturn(tarefas);
		ResponseEntity<List<TarefaDTO>> response = controller.listAll();
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service).listAll();
	}

}

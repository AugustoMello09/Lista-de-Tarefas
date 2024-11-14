package io.gitHub.AugustoMello09.ListTasks.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import io.gitHub.AugustoMello09.ListTasks.domain.entities.Tarefa;
import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaDTO;
import io.gitHub.AugustoMello09.ListTasks.provider.TarefaProvider;
import io.gitHub.AugustoMello09.ListTasks.repositories.TarefaRepository;
import io.gitHub.AugustoMello09.ListTasks.servicies.exceptions.ObjectNotFoundException;
import io.gitHub.AugustoMello09.ListTasks.servicies.serviciesImpl.TarefaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

	private static final long ID = 1L;

	@InjectMocks
	private TarefaServiceImpl service;

	@Mock
	private TarefaRepository repository;

	private TarefaProvider tarefaProvider;
	// private TarefaDTOProvider tarefaDTOProvider;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		tarefaProvider = new TarefaProvider();
		// tarefaDTOProvider = new TarefaDTOProvider();
		service = new TarefaServiceImpl(repository);
	}

	@DisplayName("Deve retornar uma tarefa com sucesso.")
	@Test
	public void shouldReturnATarefaWithSuccess() {
		Tarefa tarefa = tarefaProvider.criar();

		when(repository.findById(ID)).thenReturn(Optional.of(tarefa));

		var response = service.findById(ID);
		assertNotNull(response);
		assertEquals(TarefaDTO.class, response.getClass());
		assertEquals(ID, response.getId());
	}

	@DisplayName("Deve retornar uma tarefa não encontrado.")
	@Test
	public void shouldReturnTarefaNotFound() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

}

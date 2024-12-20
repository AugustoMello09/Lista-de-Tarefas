package io.gitHub.AugustoMello09.ListTasks.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaRecord;
import io.gitHub.AugustoMello09.ListTasks.provider.TarefaProvider;
import io.gitHub.AugustoMello09.ListTasks.repositories.TarefaRepository;
import io.gitHub.AugustoMello09.ListTasks.servicies.exceptions.DataIntegratyViolationException;
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
	//private TarefaDTOProvider tarefaDTOProvider;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		tarefaProvider = new TarefaProvider();
		//tarefaDTOProvider = new TarefaDTOProvider();
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
	
	@DisplayName("Deve retornar uma lista de tarefas.")
	@Test
	public void whenFindAllThenReturnTarefaDTO() {;
		List<Tarefa> tarefas = Arrays.asList(new Tarefa(ID, "Estudar", BigDecimal.ZERO, LocalDate.now().plusDays(2), 0));
		when(repository.findAllByOrderByPosition()).thenReturn(tarefas);
		List<TarefaDTO> result = service.listAll();
		assertNotNull(result);
	}
	
	@DisplayName("Deve criar uma tarefa com sucesso.")
	@Test
	public void whenCreateThenReturnTarefaDTO() {
	    TarefaRecord tarefaRecord = new TarefaRecord("Nome da Tarefa", new BigDecimal("100.00"), "2023-12-31");

	    Tarefa tarefa = new Tarefa();
	    tarefa.setId(1L);
	    tarefa.setName(tarefaRecord.name());
	    tarefa.setCost(tarefaRecord.cost());
	    tarefa.setDueDate(LocalDate.parse(tarefaRecord.dueDate())); 
	    tarefa.setPosition(1); 

	    when(repository.save(any(Tarefa.class))).thenReturn(tarefa);
	    when(repository.findMaxPosition()).thenReturn(0); 

	    TarefaDTO resultado = service.create(tarefaRecord);

	    assertNotNull(resultado);
	    assertEquals(tarefa.getName(), resultado.getName());
	    assertEquals(tarefa.getCost(), resultado.getCost());
	    assertEquals(tarefa.getDueDate(), resultado.getDueDate());
	    assertEquals(tarefa.getPosition(), resultado.getPosition());
	}
	
	@DisplayName("Atualização Deve retornar sucesso.")
	@Test
	public void shouldUpdateReturnSuccess() {
		
		TarefaRecord tarefaRecord = new TarefaRecord("Nome da Tarefa", new BigDecimal("100.00"), "2023-12-31");
		Tarefa tarefa = tarefaProvider.criar();
		
		when(repository.findById(ID)).thenReturn(Optional.of(tarefa));
		when(repository.save(any(Tarefa.class))).thenReturn(tarefa);
		
		service.update(tarefaRecord, ID);
		
		verify(repository, times(1)).findById(ID);
		verify(repository, times(1)).save(any(Tarefa.class));
	}
	
	@DisplayName("Atualização Deve retornar tarefa não encontrada.")
	@Test
	public void shouldUpdateReturnTarefaNotFound() {
		TarefaRecord tarefaRecord = new TarefaRecord("Nome da Tarefa", new BigDecimal("100.00"), "2023-12-31");
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.update(tarefaRecord, ID));
	}
	
	@DisplayName("Deve deletar uma tarefa com sucesso.")
	@Test
	public void shouldDeleteTarefaWithSuccess() {
		Tarefa tarefa = tarefaProvider.criar();
		when(repository.findById(ID)).thenReturn(Optional.of(tarefa));
		service.delete(ID);
	}

	@DisplayName("Deve não encontrar uma tarefa ao deletar.")
	@Test
	public void shouldReturnTarefaNotFoundWhenDelete() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.delete(ID));
	}
	
	@DisplayName("Deve retornar nome da tarefa já existe.")
	@Test
	public void shouldReturnDataIntegratyViolationExceptionWhenNameTarefaExist() {
	    TarefaRecord tarefaRecord = new TarefaRecord("Nome da Tarefa", new BigDecimal("100.00"), "2023-12-31");
	    
	    Tarefa tarefa = new Tarefa();
	    tarefa.setId(1L);
	    tarefa.setName(tarefaRecord.name());
	    tarefa.setCost(tarefaRecord.cost());
	    tarefa.setDueDate(LocalDate.parse(tarefaRecord.dueDate())); 
	    tarefa.setPosition(1);
	    
	    when(repository.findByName(tarefaRecord.name()))
	      .thenReturn(Optional.of(tarefa));

	    DataIntegratyViolationException exception = assertThrows(DataIntegratyViolationException.class, () -> {
	        service.nameAlreadyExists(tarefaRecord.name());
	    });

	    assertEquals("Não é permitido criar uma tarefa com um nome já existente", exception.getMessage());
	}
	
	@DisplayName("Não deve lançar exceção quando o nome da tarefa não existe")
	@Test
	public void shouldNotThrowExceptionWhenNameNotExists() {
	    TarefaRecord tarefaRecord = new TarefaRecord("Nome da Tarefa", new BigDecimal("100.00"), "2023-12-31");
	    
	    when(repository.findByName(tarefaRecord.name())).thenReturn(Optional.empty());
	    
	    assertDoesNotThrow(() -> service.nameAlreadyExists(tarefaRecord.name()));
	}
	
	@DisplayName("Deve mover a tarefa na lista com sucesso")
	@Test
	public void shouldMoveTeskWithSuccess() {
		List<Tarefa> tarefas = new ArrayList<>();
		
		Tarefa tarefa1 = new Tarefa();
		tarefa1.setId(1L);
		tarefa1.setName("Tarefa Teste");
		tarefa1.setPosition(0);
		
		Tarefa tarefa2 = new Tarefa();
		tarefa2.setId(2L);
		tarefa2.setName("Tarefa Teste");
		tarefa2.setPosition(1);
		
		tarefas.add(tarefa1);
		tarefas.add(tarefa2);
		
		when(repository.findAllByOrderByPosition()).thenReturn(tarefas);
		
		int sourceIndex = 0; 
	    int destinationIndex = 1; 
	    
	    service.moveTarefa(sourceIndex, destinationIndex);
	    
	    verify(repository).findAllByOrderByPosition();
	    
	    verify(repository).updateBelongingPosition(2L, 0); 
        verify(repository).updateBelongingPosition(1L, 1);
	}
	
	@DisplayName("Deve aplicar com sucesso o patch de atualização em uma tarefa existente.")
	@Test
	public void shouldApplyPatchToUpdateTarefa() {
		Tarefa tarefa = tarefaProvider.criar();
		Map<String, Object> fields = new HashMap<>();
		fields.put("name", "teste 1");
		when(repository.findById(ID)).thenReturn(Optional.of(tarefa));
		when(repository.save(any(Tarefa.class))).thenReturn(tarefa);
		
		service.patchUpdate(fields, ID);
		
		verify(repository, times(1)).findById(ID);
		verify(repository, times(1)).save(any(Tarefa.class));
	}

	@DisplayName("Deve retornar tarefa não encontrado ao tentar aplicar patch de atualização.")
	@Test
	public void shouldReturnTarefaNotFoundWhenApplyingPatchToUpdate() {
		Map<String, Object> fields = new HashMap<>();
		fields.put("name", "teste 1");
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.patchUpdate(fields, ID));
	}

}

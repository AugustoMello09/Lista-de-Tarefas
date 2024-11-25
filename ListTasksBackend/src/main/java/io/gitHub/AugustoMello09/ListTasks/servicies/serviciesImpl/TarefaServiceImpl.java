package io.gitHub.AugustoMello09.ListTasks.servicies.serviciesImpl;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import io.gitHub.AugustoMello09.ListTasks.domain.entities.Tarefa;
import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaDTO;
import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaRecord;
import io.gitHub.AugustoMello09.ListTasks.repositories.TarefaRepository;
import io.gitHub.AugustoMello09.ListTasks.servicies.TarefaService;
import io.gitHub.AugustoMello09.ListTasks.servicies.exceptions.DataIntegratyViolationException;
import io.gitHub.AugustoMello09.ListTasks.servicies.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService {

	private final TarefaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public TarefaDTO findById(Long id) {
		Optional<Tarefa> obj = repository.findById(id);
		Tarefa entity = obj.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada"));
		return new TarefaDTO(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TarefaDTO> listAll() {
		List<Tarefa> tarefas = repository.findAllByOrderByPosition();
		return tarefas.stream().map(tarefa -> new TarefaDTO(tarefa)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public TarefaDTO create(TarefaRecord tarefaRecord) {
		nameAlreadyExists(tarefaRecord.name());
		Tarefa tarefa = new Tarefa();
		tarefa.setName(tarefaRecord.name());
		tarefa.setCost(tarefaRecord.cost());
		LocalDate dueDate = LocalDate.parse(tarefaRecord.dueDate());
		tarefa.setDueDate(dueDate);

		Integer maxPosition = repository.findMaxPosition();
		tarefa.setPosition(maxPosition + 1);

		repository.save(tarefa);

		return new TarefaDTO(tarefa);
	}

	@Override
	@Transactional
	public void update(TarefaRecord tarefaRecord, Long id) {
		Tarefa tarefa = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("tarefa não encontrada"));
		tarefa.setCost(tarefaRecord.cost());
		LocalDate dueDate = LocalDate.parse(tarefaRecord.dueDate());
		tarefa.setDueDate(dueDate);
		nameAlreadyExists(tarefaRecord.name());
		tarefa.setName(tarefaRecord.name());
		repository.save(tarefa);
	}

	@Override
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}

	public void nameAlreadyExists(String name) {
		Optional<Tarefa> entity = repository.findByName(name);
		if (entity.isPresent()) {
			throw new DataIntegratyViolationException("Não é permitido criar uma tarefa com um nome já existente");
		}
	}

	@Override
	@Transactional
	public void moveTarefa(int sourceIndex, int destinationIndex) {
		List<Tarefa> tarefas = repository.findAllByOrderByPosition();

		Tarefa obj = tarefas.remove(sourceIndex);

		tarefas.add(destinationIndex, obj);

		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;

		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

		for (int i = min; i <= max; i++) {
			Long tarefaId = tarefas.get(i).getId();
			repository.updateBelongingPosition(tarefaId, i);
		}

	}
	
	@Override
	public void patchUpdate(Map<String, Object> fields, Long id) {
		Tarefa tarefa = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada"));
		if (fields.containsKey("name")) {
	        String newName = (String) fields.get("name"); 
	        nameAlreadyExists(newName); 
	    }
		merge(fields, tarefa);
		repository.save(tarefa);
	}
	
	private void merge(Map<String, Object> fields, Tarefa tarefa) {
	    fields.forEach((propertyName, propertyValue) -> {
	        Field field = ReflectionUtils.findField(Tarefa.class, propertyName);
	        if (field != null) {
	            field.setAccessible(true);
	            Object newValue = propertyValue;
	            ReflectionUtils.setField(field, tarefa, newValue);
	        }
	    });
	}

}

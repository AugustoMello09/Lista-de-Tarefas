package io.gitHub.AugustoMello09.ListTasks.servicies.serviciesImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.gitHub.AugustoMello09.ListTasks.domain.entities.Tarefa;
import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaDTO;
import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaRecord;
import io.gitHub.AugustoMello09.ListTasks.repositories.TarefaRepository;
import io.gitHub.AugustoMello09.ListTasks.servicies.TarefaService;
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
		List<Tarefa> tarefas = repository.findAll();
		return tarefas.stream().map(tarefa -> new TarefaDTO(tarefa)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public TarefaDTO create(TarefaRecord tarefaRecord) {
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

}

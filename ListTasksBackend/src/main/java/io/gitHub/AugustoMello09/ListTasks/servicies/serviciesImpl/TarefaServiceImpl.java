package io.gitHub.AugustoMello09.ListTasks.servicies.serviciesImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.gitHub.AugustoMello09.ListTasks.domain.entities.Tarefa;
import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaDTO;
import io.gitHub.AugustoMello09.ListTasks.repositories.TarefaRepository;
import io.gitHub.AugustoMello09.ListTasks.servicies.TarefaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService {
	
	private final TarefaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public TarefaDTO findById(Long id) {
		Optional<Tarefa> obj = repository.findById(id);
		Tarefa entity = obj.orElse(null);
		return new TarefaDTO(entity);
	}
	


}

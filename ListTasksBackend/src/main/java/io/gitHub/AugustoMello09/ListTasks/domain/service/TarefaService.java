package io.gitHub.AugustoMello09.ListTasks.domain.service;

import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaDTO;

public interface TarefaService {
	
	TarefaDTO findById(Long id);

}

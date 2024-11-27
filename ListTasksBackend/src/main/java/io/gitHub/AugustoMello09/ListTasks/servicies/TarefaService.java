package io.gitHub.AugustoMello09.ListTasks.servicies;

import java.util.List;
import java.util.Map;

import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaDTO;
import io.gitHub.AugustoMello09.ListTasks.domain.entities.dtos.TarefaRecord;

public interface TarefaService {
	
	TarefaDTO findById(Long id);
	
	List<TarefaDTO> listAll();
	
	TarefaDTO create(TarefaRecord tarefaRecord);
	
	void update(TarefaRecord tarefaRecord, Long id);
	
	void delete(Long id);
	
	void moveTarefa(int sourceIndex, int destinationIndex);
	
	TarefaDTO patchUpdate(Map<String, Object> fields, Long id);

}

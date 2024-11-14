package io.gitHub.AugustoMello09.ListTasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.gitHub.AugustoMello09.ListTasks.domain.entities.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query("SELECT COALESCE(MAX(t.position), 0) FROM Tarefa t")
	Integer findMaxPosition();

}

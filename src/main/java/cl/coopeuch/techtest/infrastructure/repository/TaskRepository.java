package cl.coopeuch.techtest.infrastructure.repository;

import cl.coopeuch.techtest.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}


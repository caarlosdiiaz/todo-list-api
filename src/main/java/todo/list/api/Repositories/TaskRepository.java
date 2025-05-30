package todo.list.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.list.api.Models.Task;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}

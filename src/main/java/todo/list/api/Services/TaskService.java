package todo.list.api.Services;

import org.springframework.stereotype.Service;
import todo.list.api.Repositories.TaskRepository;

@Service
public class TaskService {

  private final TaskRepository repository;

  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }
}

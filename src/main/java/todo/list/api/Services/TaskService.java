package todo.list.api.Services;

import org.springframework.stereotype.Service;
import todo.list.api.Models.Tag;
import todo.list.api.Models.Task;
import todo.list.api.Repositories.TaskRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

  private final TaskRepository repository;

  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }

  public Optional<Task> findById(UUID id) {
    return repository.findById(id);
  }

  public Task save(Task task) {
    return repository.save(task);
  }

  public boolean deleteById(UUID id) {
    Optional<Task> taskOptional = repository.findById(id);
    if (taskOptional.isPresent()) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }

  public boolean completeTask(UUID id) {
    Optional<Task> taskOptional = repository.findById(id);
    if (taskOptional.isPresent()) {
      Task task = taskOptional.get();
      task.setCompleted(true);
      repository.save(task);
      return true;
    }
    return false;
  }

  public boolean addTagToTask(UUID taskId, Tag tag) {
    Optional<Task> taskOptional = repository.findById(taskId);
    if (taskOptional.isPresent()) {
      Task task = taskOptional.get();
      task.addTag(tag);
      repository.save(task);
      return true;
    }
    return false;
  }

  public boolean removeTagFromTask(UUID taskId, Tag tag) {
    Optional<Task> taskOptional = repository.findById(taskId);
    if (taskOptional.isPresent()) {
      Task task = taskOptional.get();
      task.deleteTag(tag);
      repository.save(task);
      return true;
    }
    return false;
  }
}

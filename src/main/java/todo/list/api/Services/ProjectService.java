package todo.list.api.Services;

import org.springframework.stereotype.Service;
import todo.list.api.Models.Project;
import todo.list.api.Models.Task;
import todo.list.api.Models.User;
import todo.list.api.Repositories.ProjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

  private final ProjectRepository repository;

  public ProjectService(ProjectRepository repository) {
    this.repository = repository;
  }

  public Optional<Project> findById(UUID id) {
    return repository.findById(id);
  }

  public Project save(Project project) {
    return repository.save(project);
  }

  public boolean addUserToProject(UUID projectId, User user) {
    Optional<Project> optionalProject = repository.findById(projectId);
    if (optionalProject.isPresent()) {
      Project project = optionalProject.get();
      project.addUser(user);
      repository.save(project);
      return true;
    }
    return false;
  }

  public boolean removeUserFromProject(UUID projectId, User user) {
    Optional<Project> optionalProject = repository.findById(projectId);
    if (optionalProject.isPresent()) {
      Project project = optionalProject.get();
      project.removeUser(user);
      repository.save(project);
      return true;
    }
    return false;
  }

  public Boolean addTaskToProject(UUID projectId, Task task) {
    Optional<Project> optionalProject = repository.findById(projectId);
    if (optionalProject.isPresent()) {
      Project project = optionalProject.get();
      project.addTask(task);
      repository.save(project);
      return true;
    }
    return false;
  }

  public boolean removeTaskFromProject(UUID projectId, Task task) {
    Optional<Project> optionalProject = repository.findById(projectId);
    if (optionalProject.isPresent()) {
      Project project = optionalProject.get();
      project.removeTask(task);
      repository.save(project);
      return true;
    }
    return false;
  }

  public List<User> getUsersByProjectId(UUID projectId) {
    Optional<Project> optionalProject = repository.findById(projectId);
    return optionalProject.map(Project::getUsers).orElse(List.of());
  }

  public void deleteById(UUID id) {
    repository.deleteById(id);
  }

  public List<Project> findAll() {
    return repository.findAll();
  }
}

package todo.list.api.Services;

import org.springframework.stereotype.Service;
import todo.list.api.Repositories.ProjectRepository;

@Service
public class ProjectService {

  private final ProjectRepository repository;

  public ProjectService(ProjectRepository repository) {
    this.repository = repository;
  }
}

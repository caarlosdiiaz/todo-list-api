package todo.list.api.Services;

import org.springframework.stereotype.Service;
import todo.list.api.Repositories.TagRepository;

@Service
public class TagService {

  private final TagRepository repository;

  public TagService(TagRepository repository) {
    this.repository = repository;
  }
}

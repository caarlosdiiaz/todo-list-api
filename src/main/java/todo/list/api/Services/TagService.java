package todo.list.api.Services;

import org.springframework.stereotype.Service;
import todo.list.api.Models.Tag;
import todo.list.api.Repositories.TagRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {

  private final TagRepository repository;

  public TagService(TagRepository repository) {
    this.repository = repository;
  }

  public List<Tag> findAll() {
    return repository.findAll();
  }

  public Optional<Tag> findById(UUID id) {
    return repository.findById(id);
  }

  public Tag save(Tag tag) {
    return repository.save(tag);
  }

  public boolean modify(Tag tag) {
    if (repository.existsById(tag.getId())) {
      repository.save(tag);
      return true;
    }
    return false;
  }

  public void deleteById(UUID id) {
    repository.deleteById(id);
  }
}

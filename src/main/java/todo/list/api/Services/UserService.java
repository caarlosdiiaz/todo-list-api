package todo.list.api.Services;

import org.springframework.stereotype.Service;
import todo.list.api.Repositories.UserRepository;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }
}
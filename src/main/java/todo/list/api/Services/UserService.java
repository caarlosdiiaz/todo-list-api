package todo.list.api.Services;

import org.springframework.stereotype.Service;
import todo.list.api.Configs.CryptConfig;
import todo.list.api.Dtos.CreateUserDto;
import todo.list.api.Dtos.LoginDto;
import todo.list.api.Dtos.UserDto;
import todo.list.api.Models.Project;
import todo.list.api.Models.User;
import todo.list.api.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public Optional<User> getUserById(UUID id) {
    return repository.findById(id);
  }

  public User createUser(CreateUserDto user) {
    User newUser = new User();
    newUser.setUsername(user.getUsername());
    newUser.setPassword(CryptConfig.hashPassword(user.getPassword()));
    newUser.setEmail(user.getEmail());
    newUser.setAdmin(false);
    return repository.save(newUser);
  }

  public Optional<UserDto> logIn(LoginDto dto) {
    Optional<User> userOpt = repository.findByUsername(dto.getUsername());
    if (userOpt.isPresent()) {
      User user = userOpt.get();
      if (CryptConfig.checkPassword(dto.getPassword(), user.getPassword())) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAdmin(user.getAdmin());
        userDto.setProjects(user.getProjects());
        return Optional.of(userDto);
      }
    }
    return Optional.empty();
  }

  public boolean deleteUser(UUID id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }

  public List<Project> getUserProjects(UUID userId) {
    return repository.findById(userId)
        .map(User::getProjects)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  public List<UserDto> getAllUsers() {
    return repository.findAll().stream()
        .map(user ->
            new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getAdmin(), user.getProjects())
        ).toList();
  }

  public Boolean updateUser(UUID id, CreateUserDto user) {
    Optional<User> OptionalUser = repository.findById(id);
    if (OptionalUser.isPresent()) {
      User existingUser = OptionalUser.get();
      existingUser.setUsername(user.getUsername());
      existingUser.setEmail(user.getEmail());
      if (user.getPassword() != null && !user.getPassword().isEmpty()) {
        existingUser.setPassword(CryptConfig.hashPassword(user.getPassword()));
      }
      repository.save(existingUser);
      return true;
    }

    return false;
  }
}
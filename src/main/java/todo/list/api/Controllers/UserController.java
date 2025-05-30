package todo.list.api.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.list.api.Dtos.CreateUserDto;
import todo.list.api.Dtos.LoginDto;
import todo.list.api.Dtos.UserDto;
import todo.list.api.Models.Project;
import todo.list.api.Models.User;
import todo.list.api.Services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  // GET: Obtener usuario por ID
  @GetMapping("id/{id}")
  public ResponseEntity<User> getUserById(@PathVariable UUID id) {
    return service.getUserById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // GET: Obtener todos los usuarios
  @GetMapping("/all")
  public List<UserDto> getAllUsers() {
    return service.getAllUsers();
  }

  // GET: Obtener proyectos de un usuario
  @GetMapping("/projects/{id}")
  public ResponseEntity<List<Project>> getUserProjects(@PathVariable UUID id) {
    try {
      return ResponseEntity.ok(service.getUserProjects(id));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // POST: Crear usuario
  @PostMapping("/create")
  public ResponseEntity<User> createUser(@RequestBody CreateUserDto dto) {
    User created = service.createUser(dto);
    return ResponseEntity.ok(created);
  }

  // POST: Login de usuario
  @PostMapping("/login")
  public ResponseEntity<UserDto> logIn(@RequestBody LoginDto dto) {
    return service.logIn(dto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.status(401).build());
  }

  // PUT: Actualizar usuario
  @PutMapping("update/{id}")
  public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody CreateUserDto dto) {
    Boolean updated = service.updateUser(id, dto);
    if (updated) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  // DELETE: Eliminar usuario
  @DeleteMapping("delete/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
    boolean deleted = service.deleteUser(id);
    if (deleted) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
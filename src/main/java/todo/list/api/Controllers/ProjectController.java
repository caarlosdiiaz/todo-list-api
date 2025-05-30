package todo.list.api.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.list.api.Models.Project;
import todo.list.api.Models.Task;
import todo.list.api.Models.User;
import todo.list.api.Services.ProjectService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

  private final ProjectService service;

  public ProjectController(ProjectService service) {
    this.service = service;
  }

  // GET: Obtener proyecto por ID
  @GetMapping("/id/{id}")
  public ResponseEntity<Project> getProjectById(@PathVariable UUID id) {
    Optional<Project> project = service.findById(id);
    return project.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  // POST: Crear nuevo proyecto
  @PostMapping("/create")
  public ResponseEntity<Project> createProject(@RequestBody Project project) {
    Project saved = service.save(project);
    return ResponseEntity.ok(saved);
  }

  // PUT: Actualizar proyecto existente
  @PutMapping("/update/{id}")
  public ResponseEntity<Project> updateProject(@PathVariable UUID id, @RequestBody Project project) {
    Optional<Project> existing = service.findById(id);
    if (existing.isPresent()) {
      project.setId(id);
      Project updated = service.save(project);
      return ResponseEntity.ok(updated);
    }
    return ResponseEntity.notFound().build();
  }

  // DELETE: Eliminar proyecto por ID
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
    service.deleteById(id);
    return ResponseEntity.ok().build();
  }

  // POST: Agregar usuario a proyecto
  @PostMapping("/add-project-users/{projectId}")
  public ResponseEntity<Void> addUserToProject(@PathVariable UUID projectId, @RequestBody User user) {
    boolean added = service.addUserToProject(projectId, user);
    if (added) return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }

  // DELETE: Remover usuario de proyecto
  @DeleteMapping("/delete-project-users/{projectId}")
  public ResponseEntity<Void> removeUserFromProject(@PathVariable UUID projectId, @RequestBody User user) {
    boolean removed = service.removeUserFromProject(projectId, user);
    if (removed) return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }

  // GET: Listar usuarios de un proyecto
  @GetMapping("/find-project-users/{projectId}")
  public ResponseEntity<List<User>> getUsersByProject(@PathVariable UUID projectId) {
    List<User> users = service.getUsersByProjectId(projectId);
    return ResponseEntity.ok(users);
  }

  // POST: Agregar tarea a proyecto
  @PostMapping("/add-project-tasks/{projectId}")
  public ResponseEntity<Void> addTaskToProject(@PathVariable UUID projectId, @RequestBody Task task) {
    Boolean added = service.addTaskToProject(projectId, task);
    if (added) return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }

  // DELETE: Remover tarea de proyecto
  @DeleteMapping("/delete-project-tasks/{projectId}")
  public ResponseEntity<Void> removeTaskFromProject(@PathVariable UUID projectId, @RequestBody Task task) {
    boolean removed = service.removeTaskFromProject(projectId, task);
    if (removed) return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }

  // GET: Listar tareas de un proyecto
  @GetMapping("/find-project-tasks/{projectId}")
  public ResponseEntity<List<Task>> getTasksByProject(@PathVariable UUID projectId) {
    Optional<Project> project = service.findById(projectId);
    return project.map(value -> ResponseEntity.ok(value.getTasks()))
        .orElse(ResponseEntity.notFound().build());
  }

  // GET: Listar todos los proyectos (extra)
  @GetMapping("/all")
  public ResponseEntity<List<Project>> getAllProjects() {
    // Este método requiere que agregues un método findAll() en ProjectService y ProjectRepository
    return ResponseEntity.ok(service.findAll());
  }
}
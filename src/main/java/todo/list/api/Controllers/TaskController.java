package todo.list.api.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.list.api.Models.Tag;
import todo.list.api.Models.Task;
import todo.list.api.Services.TaskService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService service;

  public TaskController(TaskService service) {
    this.service = service;
  }

  // GET: Obtener tarea por ID
  @GetMapping("/id/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable UUID id) {
    Optional<Task> task = service.findById(id);
    return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // POST: Crear o guardar una tarea
  @PostMapping("/create")
  public ResponseEntity<Task> createTask(@RequestBody Task task) {
    Task saved = service.save(task);
    return ResponseEntity.ok(saved);
  }

  // POST: Agregar tag a una tarea
  @PostMapping("/add-tag/{taskId}")
  public ResponseEntity<Void> addTagToTask(@PathVariable UUID taskId, @RequestBody Tag tag) {
    boolean added = service.addTagToTask(taskId, tag);
    if (added) return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }

  // PUT: Marcar tarea como completada
  @PutMapping("/complete/{id}")
  public ResponseEntity<Void> completeTask(@PathVariable UUID id) {
    boolean completed = service.completeTask(id);
    if (completed) return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }

  // DELETE: Eliminar tarea por ID
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
    boolean deleted = service.deleteById(id);
    if (deleted) return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }

  // DELETE: Remover tag de una tarea
  @DeleteMapping("/remove-tag/{taskId}")
  public ResponseEntity<Void> removeTagFromTask(@PathVariable UUID taskId, @RequestBody Tag tag) {
    boolean removed = service.removeTagFromTask(taskId, tag);
    if (removed) return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }
}
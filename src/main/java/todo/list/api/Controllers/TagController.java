package todo.list.api.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.list.api.Models.Tag;
import todo.list.api.Services.TagService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagController {

  private final TagService service;

  public TagController(TagService service) {
    this.service = service;
  }

  // GET: Obtener todos los tags
  @GetMapping
  public ResponseEntity<List<Tag>> getAllTags() {
    return ResponseEntity.ok(service.findAll());
  }

  // GET: Obtener tag por ID
  @GetMapping("/{id}")
  public ResponseEntity<Tag> getTagById(@PathVariable UUID id) {
    Optional<Tag> tag = service.findById(id);
    return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // POST: Crear un nuevo tag
  @PostMapping
  public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
    Tag saved = service.save(tag);
    return ResponseEntity.ok(saved);
  }

  // PUT: Modificar un tag existente
  @PutMapping("/{id}")
  public ResponseEntity<Tag> updateTag(@PathVariable UUID id, @RequestBody Tag tag) {
    tag.setId(id);
    boolean updated = service.modify(tag);
    if (updated) {
      return ResponseEntity.ok(tag);
    }
    return ResponseEntity.notFound().build();
  }

  // DELETE: Eliminar un tag por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
    service.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
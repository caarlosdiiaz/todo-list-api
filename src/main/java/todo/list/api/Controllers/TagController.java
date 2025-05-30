package todo.list.api.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.list.api.Services.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

  private final TagService service;

  public TagController(TagService service) {
    this.service = service;
  }
}

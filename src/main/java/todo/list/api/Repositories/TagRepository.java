package todo.list.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.list.api.Models.Tag;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
}

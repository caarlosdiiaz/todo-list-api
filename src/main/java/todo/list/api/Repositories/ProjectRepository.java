package todo.list.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.list.api.Models.Project;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}

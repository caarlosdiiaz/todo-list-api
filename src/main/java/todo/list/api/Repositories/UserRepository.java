package todo.list.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.list.api.Models.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}

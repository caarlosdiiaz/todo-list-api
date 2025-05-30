package todo.list.api.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "project")
public class Project {

  @Id
  @UuidGenerator
  private UUID id;

  @Column(nullable = false)
  private String name;
  private String description;

  @Column(name = "manager_id", nullable = false)
  private UUID projectManagerId;

  @ManyToMany(mappedBy = "projects")
  private List<User> users;

  @OneToMany(mappedBy = "project")
  private List<Task> tasks;

  public Project() {
  }

  public Project(UUID id, String name, String description, UUID projectManagerId) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.projectManagerId = projectManagerId;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public UUID getProjectManagerId() {
    return projectManagerId;
  }

  public void setProjectManagerId(UUID projectManagerId) {
    this.projectManagerId = projectManagerId;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }
}
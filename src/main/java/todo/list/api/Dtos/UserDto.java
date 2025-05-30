package todo.list.api.Dtos;

import todo.list.api.Models.Project;

import java.util.List;
import java.util.UUID;

public class UserDto {
  private UUID id;
  private String username;
  private String email;
  private Boolean admin;
  private List<Project> projects;

  public UserDto() {
  }

  public UserDto(UUID id, String username, String email, Boolean admin, List<Project> projects) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.admin = admin;
    this.projects = projects;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getAdmin() {
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }
}

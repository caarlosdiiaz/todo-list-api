package todo.list.api.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tag")
public class Tag {
  @Id
  @UuidGenerator
  private UUID id;

  private String name;
  @Column(columnDefinition = "default '#000000'")
  private String color;
  @Column(columnDefinition = "boolean default false")
  private Boolean favorite;

  @ManyToMany(mappedBy = "tags")
  private List<Task> tasks;

  public Tag() {
  }

  public Tag(UUID id, String name, String color, Boolean favorite) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.favorite = favorite;
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

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Boolean getFavorite() {
    return favorite;
  }

  public void setFavorite(Boolean favorite) {
    this.favorite = favorite;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }
}

package com.andrey4623.springdemo.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  private Long id;
  private String username;
  private String password;
  private String name;
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(Long id, String username, String password, String name) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "username", length = 45)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "password", length = 45)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "name", length = 255)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @ManyToMany
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(
          name = "user_id"),
      inverseJoinColumns = @JoinColumn(
          name = "role_id"))
  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}

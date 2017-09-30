package com.andrey4623.springdemo.service;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class ExtendedUser extends User {

  private long id;
  private String name;

  public ExtendedUser(long id, String username, String name, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    setId(id);
    setName(name);
  }

  public ExtendedUser(long id, String username, String name, String password, boolean enabled,
      boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
        authorities);
    setId(id);
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}

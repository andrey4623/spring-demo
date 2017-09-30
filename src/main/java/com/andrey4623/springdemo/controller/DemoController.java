package com.andrey4623.springdemo.controller;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

  @GetMapping(value = "/")
  public String welcome() {
    String[] requiredRoles = {"ROLE_USER", "ROLE_ADMIN"};

    if (isAuthorized(requiredRoles)) {
      return "user/welcome";
    } else {
      return "welcome";
    }
  }

  //TODO refactor
  private boolean isAuthorized(String[] roles) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

    boolean authorized = false;

    for (String role : roles) {
      if (authorities.contains(new SimpleGrantedAuthority(role))) {
        authorized = true;
        break;
      }
    }

    return authorized;
  }

  @GetMapping(value = "/home")
  public String userHome() {
    return "user/welcome";
  }

  @GetMapping(value = "/admin")
  public String adminHome() {
    return "admin/welcome";
  }
}

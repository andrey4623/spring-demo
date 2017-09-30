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
    if (isAuthorized("ROLE_READ_PROFILE")) {
      return "user/welcome";
    } else {
      return "welcome";
    }
  }

  private boolean isAuthorized(String role) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

    return authorities.contains(new SimpleGrantedAuthority(role));
  }

  @GetMapping(value = "/admin")
  public String adminHome() {
    return "admin/welcome";
  }
}

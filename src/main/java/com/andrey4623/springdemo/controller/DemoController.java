package com.andrey4623.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

  @GetMapping(value = "/")
  public String welcome() {
    return "welcome";
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

package com.andrey4623.springdemo.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsController {

  @GetMapping(value = "/401")
  public String handle401() {
    return "errors/401";
  }

  @RequestMapping(value = "/403", method = RequestMethod.GET)
  public String accesssDenied(ModelMap model) {

    // check if user is login
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (!(auth instanceof AnonymousAuthenticationToken)) {
      UserDetails userDetail = (UserDetails) auth.getPrincipal();

      model.addAttribute("username", userDetail.getUsername());
    }

    return "errors/403";
  }

  @GetMapping(value = "/404")
  public String handle404() {
    return "errors/404";
  }

  @GetMapping(value = "/500")
  public String handle500() {
    return "errors/500";
  }
}

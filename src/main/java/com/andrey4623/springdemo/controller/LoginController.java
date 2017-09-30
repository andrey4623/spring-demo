package com.andrey4623.springdemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  private static final String INVALID_USERNAME_AND_PASSWORD = "Invalid username and (or) password";
  private static final String LOGGED_OUT = "Logged out";

  @GetMapping(value = "/login")
  public String login(@RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,
      ModelMap model) {

    if (error != null) {
      model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
    }

    if (logout != null) {
      model.addAttribute("msg", LOGGED_OUT);
    }

    return "login";
  }

  // customize the error message
  private String getErrorMessage(HttpServletRequest request, String key) {

    Exception exception = (Exception) request.getSession().getAttribute(key);

    String error = "";
    if (exception instanceof BadCredentialsException) {
      error = INVALID_USERNAME_AND_PASSWORD;
    } else if (exception instanceof LockedException) {
      error = exception.getMessage();
    } else {
      error = INVALID_USERNAME_AND_PASSWORD;
    }

    return error;
  }

  @GetMapping(value = "/logout")
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";
  }
}

package com.andrey4623.springdemo.service;

import com.andrey4623.springdemo.dao.UsersRepository;
import com.andrey4623.springdemo.model.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jboss.logging.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

  private static final String ROLE_USER = "ROLE_USER";
  private final UsersRepository usersRepository;
  private static final Logger logger = Logger.getLogger(MyUserDetailsService.class);

  public MyUserDetailsService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = usersRepository.findByUsername(username);
    List<GrantedAuthority> authorities = buildUserAuthority();
    return buildUserForAuthentication(user, authorities);
  }

  private ExtendedUser buildUserForAuthentication(User user,
      List<GrantedAuthority> authorities) {
    return new ExtendedUser(user.getId(), user.getUsername(), user.getName(), user.getPassword(),
        true, true, true, true, authorities);
  }

  private List<GrantedAuthority> buildUserAuthority() {
    Set<GrantedAuthority> setAuths = new HashSet<>();
    setAuths.add(new SimpleGrantedAuthority(ROLE_USER));
    List<GrantedAuthority> Result = new ArrayList<>(setAuths);
    return Result;
  }
}

package com.andrey4623.springdemo.service;

import com.andrey4623.springdemo.dao.UsersRepository;
import com.andrey4623.springdemo.model.Privilege;
import com.andrey4623.springdemo.model.Role;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

  private final UsersRepository usersRepository;
  private static final Logger logger = Logger.getLogger(MyUserDetailsService.class);

  public MyUserDetailsService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = usersRepository.findByUsername(username);

    List<? extends GrantedAuthority> authorities = getAuthorities(user.getRoles());

    return new ExtendedUser(user.getId(), user.getUsername(), user.getName(), user.getPassword(),
        true, true, true, true, authorities);
  }

  private List<? extends GrantedAuthority> getAuthorities(
      Set<Role> roles) {

    return getGrantedAuthorities(getPrivileges(roles));
  }

  private Set<String> getPrivileges(Set<Role> roles) {
    Set<String> privileges = new HashSet<>();
    Set<Privilege> collection = new HashSet<>();
    for (Role role : roles) {
      collection.addAll(role.getPrivileges());
    }
    for (Privilege item : collection) {
      privileges.add(item.getName());
    }
    return privileges;
  }

  private List<GrantedAuthority> getGrantedAuthorities(Set<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }
}

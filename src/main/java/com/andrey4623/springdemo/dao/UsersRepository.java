package com.andrey4623.springdemo.dao;

import com.andrey4623.springdemo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository
    extends CrudRepository<User, Long> {

  User findByUsername(String username);

}

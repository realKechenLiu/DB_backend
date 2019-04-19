package com.kechen.repository;

import com.kechen.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    public User findByUserNameAndPassword(String userName, String password);

    public User findByEmailAndPassword(String email, String password);

    public User findByUserName(String userName);

    public User findByEmail(String email);

    public User findById(int Id);
}

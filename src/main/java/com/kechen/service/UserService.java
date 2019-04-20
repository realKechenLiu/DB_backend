package com.kechen.service;

import com.kechen.domain.User;
import com.kechen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /*
    Login by username and password

    @Params userName
    @Params password

    @Return 202 if login successful.
    @Return 400 if password is incorrect
    @Return 404 if both username and password both incorrect
    */
    public int loginByUserName(String userName, String password){
        if(userRepository.findByUserNameAndPassword(userName,password) != null)
            return 200;
        else if(userRepository.findByUserName(userName) != null)
            return 400;
        return 404;
    }

    /*
    Login by email and password

    @Params email
    @Params password

    @Return 202 if login successful.
    @Return 400 if password is incorrect
    @Return 404 if both username and password both incorrect
    */
    public int loginByEmail(String email, String password){
        if(userRepository.findByEmailAndPassword(email,password) != null)
            return 200;
        else if(userRepository.findByUserName(email) != null)
            return 400;
        return 404;
    }


    /*
    Register method

    @Param userName
    @Param email
    @Param password
    @Param isAdmin

    @Return true if register successfully
    @Return false if register unsuccessfully

     */
    public boolean register(String userName, String email, String password, int isAdmin){
        return userRepository.save(new User(userName,password,email,isAdmin))!=null;
    }

    public User findById(int id){
        return userRepository.findByUserId(id);
    }
}

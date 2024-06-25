package com.spring_security.springSecurity.service;

import com.spring_security.springSecurity.Entity.User;
import com.spring_security.springSecurity.model.UserModel;

public interface UserService {
     User registerUser(UserModel userModel);

     void saveVerificationTokenForUser(String token, User user);
}

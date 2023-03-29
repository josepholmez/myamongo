package com.olmez.myamango.services;

import java.util.List;

import com.olmez.myamango.model.PasswordWrapper;
import com.olmez.myamango.model.User;

public interface UserService {

    List<User> getUsers();

    boolean addUser(User user);

    User getUserById(String id);

    boolean deleteUser(String id);

    User updateUser(String existingUserId, User givenUser);

    User getUserByUsername(String username);

    User updateUser(User givenUser);

    User getCurrentUser();

    boolean updateUserPassword(PasswordWrapper passWrapper);
}

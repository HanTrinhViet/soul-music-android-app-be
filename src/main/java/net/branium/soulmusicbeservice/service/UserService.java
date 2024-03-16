package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.model.User;

public interface UserService {
    User createUser(User user);
    User getUserById(String uuid);
    void deleteUserById(String uuid);
    void updateUser(User requestUser);
}

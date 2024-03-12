package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.exception.UserNotFoundException;
import net.branium.soulmusicbeservice.model.User;
import net.branium.soulmusicbeservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUserById(String uuid) {
        User user = userRepo.findById(uuid).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Can't find user with ID = " + uuid);
        }
        return user;
    }

    @Override
    public void deleteUserById(String uuid) {
        User user = userRepo.findById(uuid).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Can't find user with ID = " + uuid);
        }
        userRepo.deleteById(uuid);
    }
}

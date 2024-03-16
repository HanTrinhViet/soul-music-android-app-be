package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.exception.UserNotFoundException;
import net.branium.soulmusicbeservice.mapper.UserMapper;
import net.branium.soulmusicbeservice.model.User;
import net.branium.soulmusicbeservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
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

    @Override
    public void updateUser(User requestUser) {
        User updatedUser = userRepo.findById(requestUser.getId()).orElse(null);
        if (updatedUser != null) {
            updatedUser = userMapper.toUpdatedUser(requestUser);
            userRepo.save(updatedUser);
        } else {
            throw new UserNotFoundException("Can't find user with ID = " + requestUser.getId());
        }
    }
}

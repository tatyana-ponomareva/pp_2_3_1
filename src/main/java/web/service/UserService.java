package web.service;

import web.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    User getUserById(int id);
    void updateUser(User user);
    void deleteUser(int id);
}

package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    void deleteById(Long id);

    void update(User user);

    List<User> getAllUsers();

    User getUserById(Long id);
}

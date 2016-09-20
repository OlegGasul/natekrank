package pl.natekrank.service;

import pl.natekrank.model.User;

public interface UserService {
    void save(User user);

    User findByEmail(String email);
}

package portal.repository;

import java.util.List;

import portal.business.User;

public interface UserRepository {
    public User findByUsername(String username);
    public List<User> findAll();
}

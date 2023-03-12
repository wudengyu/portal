package portal.repository;

import org.springframework.security.core.userdetails.User;

public interface UserRepository {
    public User findByUsername(String username);
}

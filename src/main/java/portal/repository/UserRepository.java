package portal.repository;

import portal.business.User;

public interface UserRepository {
    public User findByUsername(String username);
}

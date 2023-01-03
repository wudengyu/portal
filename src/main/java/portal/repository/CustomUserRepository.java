package portal.repository;

import portal.business.CustomUser;

public interface CustomUserRepository {
    public CustomUser findCustomUserByUsername(String username);
}

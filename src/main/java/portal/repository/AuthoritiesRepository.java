package portal.repository;

import java.util.Set;

public interface AuthoritiesRepository {
    Set<String> readByUsername(String username);
}

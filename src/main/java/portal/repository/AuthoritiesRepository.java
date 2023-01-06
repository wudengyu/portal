package portal.repository;

import java.util.List;

public interface AuthoritiesRepository {
    List<String> findByUsername(String username);
}

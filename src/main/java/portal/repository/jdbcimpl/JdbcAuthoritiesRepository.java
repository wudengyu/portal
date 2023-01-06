package portal.repository.jdbcimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import portal.repository.AuthoritiesRepository;

@Repository
public class JdbcAuthoritiesRepository implements AuthoritiesRepository {
    
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<String> findByUsername(String username) {
        return jdbcOperations.queryForList("select authority from authorities where username=?",String.class,username);
    }
    
}

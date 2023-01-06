package portal.repository.jdbcimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import portal.business.User;
import portal.repository.UserRepository;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public User findByUsername(String username) {
        return 
            jdbcOperations.query("select username,password from users where username=?",
            (rs)->{
                if(rs.next())
                    return new User(rs.getString("username"),rs.getString("password"));
                else
                    return null;},
            username);
    }

}

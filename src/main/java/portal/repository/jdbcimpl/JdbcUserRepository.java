package portal.repository.jdbcimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
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

    @Override
    public List<User> findAll() {
        return jdbcOperations.query("select username,password from users",
                new RowMapper<User>(){
                    public User mapRow(ResultSet rs, int index) throws SQLException {
                        User user = new User(rs.getString("username"),rs.getString("password"));
                        return user;
                    }}
        );
    }
}

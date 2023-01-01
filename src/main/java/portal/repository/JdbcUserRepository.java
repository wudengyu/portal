package portal.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import portal.business.User;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_USERNAME="select password from user where username=?";

    @Override
    public User loadUserByUsername(String username) {
        jdbcTemplate.query(SQL_SELECT_BY_USERNAME,(rs)->{
            User user=new User();
            user.setUsername(username);
            user.setPassword(rs.getString("password"));
            return user;},username);
        return null;
    }
    
}

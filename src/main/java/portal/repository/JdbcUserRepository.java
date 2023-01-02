package portal.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import portal.business.User;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    private static final String SQL_SELECT_BY_USERNAME="select password from user where username=?";

    @Override
    public User loadUserByUsername(String username) {
        User loginuser=jdbcOperations.query(SQL_SELECT_BY_USERNAME,(rs)->{
            User user=new User();
            user.setUsername(username);
            user.setPassword(rs.getString("password"));
            return user;},username);
        return loginuser;
    }
    
}

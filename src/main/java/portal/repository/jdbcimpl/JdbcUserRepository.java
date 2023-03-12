package portal.repository.jdbcimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import portal.repository.UserRepository;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public User findByUsername(String username) {
        String password=jdbcOperations.queryForObject("select password from users where username=?",String.class,username);
        if(password!=null){
            List<GrantedAuthority> authorities=jdbcOperations.query(
                "select authority from authorities where username=?",
                new RowMapper<GrantedAuthority>(){
                    @Override
                    public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
                        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(rs.getString("authority"));
                        return grantedAuthority;
                    }
                },username);
            return new User(username, password, authorities);
        }else
            return null;
    }
}

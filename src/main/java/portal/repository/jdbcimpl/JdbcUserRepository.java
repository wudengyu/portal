package portal.repository.jdbcimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Repository;

import portal.business.User;
import portal.repository.UserRepository;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public User loadUserByUsername(String username) {
        String password=jdbcOperations.queryForObject("select password from users where username=?",String.class,username);
        if(password!=null){
            String authorities=jdbcOperations.queryForObject("select group_concat(authority) from authorities where username=?",String.class,username);
            return new User(username,password,AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
        }
        return null;
    }

}

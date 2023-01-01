package portal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import portal.business.User;
import portal.repository.UserRepository;

public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.loadUserByUsername(username);
        if(user!=null)
            return user;
        else
            throw new UsernameNotFoundException("User '"+username+"' not found.");
    }
    
}

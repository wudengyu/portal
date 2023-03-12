package portal.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import portal.repository.AuthoritiesRepository;
import portal.repository.UserRepository;

@Service
public class CustomUserService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("admin")){
            PasswordEncoder passwordEncoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
            return User.withUsername(username).password(passwordEncoder.encode("admin")).authorities("admin").build();
        }
        User user=userRepository.findByUsername(username);
        if(user!=null){
            List<String> authorities=authoritiesRepository.findByUsername(username);
            return new CustomUserDetails(user,authorities);
        }
        else
            throw new UsernameNotFoundException("User '"+username+"' not found.");
    }
    private class CustomUserDetails implements UserDetails{
        private String username;
        private String password;
        private ArrayList<SimpleGrantedAuthority> grantedAuthorities;

        protected CustomUserDetails(User user,List<String> authorities){
            this.username=user.getUsername();
            this.password=user.getPassword();
            for(String authority:authorities){
                this.grantedAuthorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.grantedAuthorities;
        }

        @Override
        public String getPassword() {
            return this.password;
        }

        @Override
        public String getUsername() {
            return this.username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
        
    }
    
}

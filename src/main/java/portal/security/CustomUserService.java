package portal.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import portal.business.User;
import portal.repository.AuthoritiesRepository;
import portal.repository.UserRepository;

public class CustomUserService implements UserDetailsService{

    private UserRepository userRepository;
    private AuthoritiesRepository authoritiesRepository;

    public CustomUserService(UserRepository userRepository,AuthoritiesRepository authoritiesRepository){
        this.userRepository=userRepository;
        this.authoritiesRepository=authoritiesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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

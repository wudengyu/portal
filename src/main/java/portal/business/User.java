package portal.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User  implements UserDetails{
    private String username;
    private String password;
    ArrayList<GrantedAuthority> authorities;

    public void setUsername(String username){
        this.username=username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setAuthorities(Enumeration<String> authorities){
        this.authorities.clear();
        while(authorities.hasMoreElements()){
            this.authorities.add(new SimpleGrantedAuthority(authorities.nextElement()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}

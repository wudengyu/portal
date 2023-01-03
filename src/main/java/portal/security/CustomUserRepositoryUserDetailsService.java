package portal.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import portal.business.CustomUser;
import portal.repository.AuthoritiesRepository;
import portal.repository.CustomUserRepository;

@Service
public class CustomUserRepositoryUserDetailsService implements UserDetailsService{

	private final CustomUserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;

	public CustomUserRepositoryUserDetailsService(CustomUserRepository userRepository,AuthoritiesRepository authoritiesRepository) {
		this.userRepository = userRepository;
        this.authoritiesRepository=authoritiesRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUser customUser = this.userRepository.findCustomUserByUsername(username);
		if (customUser == null) {
			throw new UsernameNotFoundException("username " + username + " is not found");
		}
		return new CustomUserDetails(customUser);
	}

	final class CustomUserDetails extends CustomUser implements UserDetails {

		private ArrayList<GrantedAuthority> role_user=new ArrayList<GrantedAuthority>();

		CustomUserDetails(CustomUser customUser) {
			super(customUser.getUsername(), customUser.getPassword());
            Set<String> authorities=authoritiesRepository.readByUsername(customUser.getUsername());
            for(String authority:authorities){
                role_user.add(new SimpleGrantedAuthority(authority));
            }
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return role_user;
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

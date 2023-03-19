package tda.darkarmy.parkme.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tda.darkarmy.parkme.model.User;
import tda.darkarmy.parkme.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);
        return new CustomUser(user);
    }
    
}

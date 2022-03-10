package se.iths.springloppis.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.iths.springloppis.entity.UserEntity;
import se.iths.springloppis.repository.UserRepository;

@Service
public class LoppisUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public LoppisUserDetailsService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null)
            throw new UsernameNotFoundException("Can't find user with username: " + username);
        return new LoppisUserPrincipal(userEntity);
    }
}

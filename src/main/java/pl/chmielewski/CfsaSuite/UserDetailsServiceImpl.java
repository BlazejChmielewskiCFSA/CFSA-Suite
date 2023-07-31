package pl.chmielewski.CfsaSuite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.chmielewski.CfsaSuite.repository.CfsaUserRepo;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private CfsaUserRepo cfsaUserRepo;

    @Autowired
    public UserDetailsServiceImpl(CfsaUserRepo cfsaUserRepo) {
        this.cfsaUserRepo = cfsaUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return cfsaUserRepo.findAllByUsername(username);
    }
}

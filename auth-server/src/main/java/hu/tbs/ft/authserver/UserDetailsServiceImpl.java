package hu.tbs.ft.authserver;

import hu.tbs.ft.user.model.dto.DbUser;
import hu.tbs.ft.user.model.dto.UserServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceIF userServiceIF;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<DbUser> user = Optional.ofNullable(userServiceIF.findUserByUsername(username).getBody());
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(user.get());
    }

}
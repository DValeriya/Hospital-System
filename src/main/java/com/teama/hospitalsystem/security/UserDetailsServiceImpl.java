package com.teama.hospitalsystem.security;

import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.services.UsersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersService usersService;

    public UserDetailsServiceImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        BigInteger loginBigInteger = new BigInteger(login);
        User user = usersService.getUserByLogin(loginBigInteger);
        return new UserDetailsAdapter(user);
    }
}

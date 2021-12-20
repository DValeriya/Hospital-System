package com.teama.hospitalsystem.security;

import com.teama.hospitalsystem.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsAdapter extends org.springframework.security.core.userdetails.User {
    public UserDetailsAdapter(User user) {
        this(user.getLogin().toString(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }

    public UserDetailsAdapter(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}

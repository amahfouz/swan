package com.mahfouz.swan;

import java.util.Set;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service
public class SwanUserDetailsService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (! username.equals("ayman"))
            throw new UsernameNotFoundException("User not found.");

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();    
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User("ayman", "ayman", authorities);
    }
}
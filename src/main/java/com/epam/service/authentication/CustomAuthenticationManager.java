package com.epam.service.authentication;

import com.epam.entity.Role;
import com.epam.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vadym_Vlasenko on 9/7/2015.
 */
@Service
public class CustomAuthenticationManager implements AuthenticationManager {

    public void authenticateUser(User user) throws AuthenticationException{
        List<GrantedAuthority> grantedAuthorities = buildUserAuthority(user.getRoles());
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), user.getUserName(), grantedAuthorities);
        setAuthentication(authentication);
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        if (auth.getName().equals("Vadm@mail.com")) {
            return new UsernamePasswordAuthenticationToken(auth.getName(),
                    auth.getCredentials(), auth.getAuthorities());
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    private Authentication getAuthenticationFromUser(String userName, String password, List<GrantedAuthority> grantedAuthorities) {
        return new UsernamePasswordAuthenticationToken(userName, password , grantedAuthorities);
    }

    private void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role userRole : userRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<>(grantedAuthorities);
    }

}

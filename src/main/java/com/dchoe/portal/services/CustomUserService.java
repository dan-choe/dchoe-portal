package com.dchoe.portal.services;

import com.dchoe.portal.model.Role;
import com.dchoe.portal.model.Users;
import com.dchoe.portal.repositories.RoleRepository;
import com.dchoe.portal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
//@Component
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public Users findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        users.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(users);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    /**
     * Spring Security Users Detail
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepository.findByUsername(username);
        if(users != null) {
            List<GrantedAuthority> authorities = getUserAuthority(users.getRoles());
            return buildUserForAuthentication(users, authorities);
        }
        throw new UsernameNotFoundException("Not found Users");
    }
}

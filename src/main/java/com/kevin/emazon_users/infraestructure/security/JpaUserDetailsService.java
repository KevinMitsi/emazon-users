package com.kevin.emazon_users.infraestructure.security;

import com.kevin.emazon_users.infraestructure.exception.NotFoundUserException;
import com.kevin.emazon_users.infraestructure.repository.IUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private static final String NOT_FOUND_USER_MESSAGE = "Usuario no encontrado";
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundUserException(NOT_FOUND_USER_MESSAGE));
    }
}

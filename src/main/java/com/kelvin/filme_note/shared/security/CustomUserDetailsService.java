package com.kelvin.filme_note.shared.security;

import com.kelvin.filme_note.user.repository.UserRepository;
import com.kelvin.filme_note.user.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(user);
    }
}

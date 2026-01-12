package com.SpringSecurityApp.SpringSecurityApp.services;

import com.SpringSecurityApp.SpringSecurityApp.dto.LoginDTO;
import com.SpringSecurityApp.SpringSecurityApp.dto.SignUpDTO;
import com.SpringSecurityApp.SpringSecurityApp.dto.UserDTO;
import com.SpringSecurityApp.SpringSecurityApp.entities.User;
import com.SpringSecurityApp.SpringSecurityApp.exception.ResourceNotFoundException;
import com.SpringSecurityApp.SpringSecurityApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                ()->new BadCredentialsException("User Not Found with email"+username+" this")
        );
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User Not Found with id "+id+" this"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);

    }public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<User> user=userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with email already exits "+signUpDTO.getEmail());
        }
        User toBeCreate=modelMapper.map(signUpDTO,User.class);
        toBeCreate.setPassword(passwordEncoder.encode(toBeCreate.getPassword()));
        User savedUser=userRepository.save(toBeCreate);
        return modelMapper.map(savedUser,UserDTO.class);
    }


    public User save(User newUser) {
        return userRepository.save(newUser);
    }
}

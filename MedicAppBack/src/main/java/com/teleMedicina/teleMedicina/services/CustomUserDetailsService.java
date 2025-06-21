package com.teleMedicina.teleMedicina.services;

// import com.teleMedicina.teleMedicina.models.UserModel;
// import com.teleMedicina.teleMedicina.repositories.IUserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// @Service

// // public class CustomUserDetailsService implements UserDetailsService {
// //     private final IUserRepository userRepository;

// //     public CustomUserDetailsService(IUserRepository userRepository) {
// //         this.userRepository = userRepository;
// //     }

// //     // @Override
// //     // public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {

// //     //     UserModel user = userRepository.findByCorreoElectronico(correoElectronico);

// //     //     if (user == null) {
// //     //         throw new UsernameNotFoundException("Usuario no registra con el correo: " + correoElectronico);
// //     //     }

// //     //     return org.springframework.security.core.userdetails.User
// //     //             .withUsername(user.getCorreoElectronico()) 
// //     //             .password(user.getContraseña()) 
// //     //             .roles(user.getRol().toString()) 
// //     //             .build();

// //     // }
// // }
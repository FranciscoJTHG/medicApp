package com.teleMedicina.teleMedicina.controllers;

import com.teleMedicina.teleMedicina.models.usuarios.DatosRegistroUsuario;
import com.teleMedicina.teleMedicina.models.usuarios.DatosRespuestaUsuario;
// import com.teleMedicina.teleMedicina.models.UserModel;
import com.teleMedicina.teleMedicina.models.usuarios.Usuario;
// import com.teleMedicina.teleMedicina.repositories.DatosAutenticacionUsuario;
// import com.teleMedicina.teleMedicina.security.DatosJWTToken;
// import com.teleMedicina.teleMedicina.services.CustomUserDetailsService;
import com.teleMedicina.teleMedicina.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
// import com.teleMedicina.teleMedicina.security.TokenService;


import jakarta.validation.Valid;
import java.util.List;


@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Usuario", description = "API de gestión de usuarios")
public class UserController {
    
    @Autowired
    private UserService userService;

    // Todos los usuarios
    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        System.out.println("all");
        List<Usuario> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //Un usuario
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        System.out.println("id");
        Usuario user = userService.getUserById(id);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Un usuario
    @GetMapping("/usuario/email/{email}")
    public ResponseEntity<Usuario> getUserByEmail(@PathVariable String email) {
        System.out.println("Email");
        Usuario user = userService.getUserByEmail(email);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // //Agregar usuario
    // @PostMapping("/registro")
    // public ResponseEntity<Usuario> createUser(@Valid @RequestBody Usuario user) {
    //     Usuario savedUser = userService.registerUser(user);
    //     return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    // }

    //Agregar usuario
    @PostMapping("/registro")
    public ResponseEntity<DatosRespuestaUsuario> createUser(@Valid @RequestBody DatosRegistroUsuario datosAutenticacionUsuario) {
        var savedUser = userService.registerUser(datosAutenticacionUsuario);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    //Editar usuario
    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Integer id, @Valid @RequestBody Usuario user) {
        Usuario updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //Borrar usuario
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

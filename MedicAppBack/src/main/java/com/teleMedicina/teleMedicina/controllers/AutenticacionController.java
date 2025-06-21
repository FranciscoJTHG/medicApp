package com.teleMedicina.teleMedicina.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.teleMedicina.teleMedicina.errores.ErrorResponse;
import com.teleMedicina.teleMedicina.models.usuarios.DatosAutenticacionUsuario;
import com.teleMedicina.teleMedicina.models.usuarios.Usuario;
import com.teleMedicina.teleMedicina.security.DatosJWTToken;
import com.teleMedicina.teleMedicina.security.TokenService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
// @RequestMapping("/login")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Autenticación")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @SuppressWarnings("null")
    @PostMapping("/login")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {

        ResponseEntity responseEntity = null;
        // String JWTtoken = null;

        try {

            Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(), datosAutenticacionUsuario.clave());
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
            responseEntity = ResponseEntity.ok(new DatosJWTToken(JWTtoken));

        } catch (BadCredentialsException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Datos inválidos", 400));
        } catch (AuthenticationException  e) {

            if (RequestContextHolder.getRequestAttributes() != null &&
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest().getAttribute("USER_NOT_FOUND") != null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Usuario no encontrado", 404));
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Credenciales inválidas", 401));
        }

        return responseEntity;
    }
}

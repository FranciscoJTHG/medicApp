package com.teleMedicina.teleMedicina.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleMedicina.teleMedicina.models.usuarios.DatosAutenticacionUsuario;
import com.teleMedicina.teleMedicina.models.usuarios.DatosRespuestaUsuario;
import com.teleMedicina.teleMedicina.models.usuarios.Rol;
import com.teleMedicina.teleMedicina.models.usuarios.Usuario;
import com.teleMedicina.teleMedicina.models.usuarios.UsuarioRepository;
import com.teleMedicina.teleMedicina.security.AutenticacionService;
import com.teleMedicina.teleMedicina.security.SecurityConfigurations;
import com.teleMedicina.teleMedicina.security.SecurityFilter;
import com.teleMedicina.teleMedicina.security.TokenService;
import com.teleMedicina.teleMedicina.services.UserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@WebMvcTest(AutenticacionController.class)
@AutoConfigureMockMvc(addFilters = false) 
@Import({SecurityConfigurations.class})
public class AutenticacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private AutenticacionService autenticacionService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UserService userService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private SecurityFilter securityFilter;


    @Test
    @DisplayName("Deberia devolver http 400 cuando la respuesta no tenga datos")
    // @WithMockUser(username = "franthielengaravito@gmail.com", password = "123456", authorities = {"USER"})
    void testAutenticarUsuario() throws Exception {

        // Given
        DatosAutenticacionUsuario datosInvalidos = new DatosAutenticacionUsuario(
            null,  // email nulo
            null,  // password nulo
            null,  // nombre nulo
            null,   // apellido nulo
            null
        );

        when(authenticationManager.authenticate(any()))
            .thenThrow(new BadCredentialsException("Credenciales inválidas"));

        // When & Then
        var response = mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(datosInvalidos)))
            .andDo(print())
            .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia devolver http 200 cuando las credenciales son correctas")
    void testAutenticarUsuarioExitoso() throws Exception {
        // Given
        DatosAutenticacionUsuario datosValidos = new DatosAutenticacionUsuario(
            "franthielengaravito@gmail.com",
            "123456",
            "Francisco",
            "Garavito",
            Rol.Paciente
        );

        Usuario usuarioMock = new Usuario(datosValidos);
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioMock, null, Collections.emptyList());

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        when(tokenService.generarToken(any(Usuario.class))).thenReturn("token-test");

        // When
        var result =  mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(datosValidos)))
            .andDo(print())
            .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getResponse().getContentAsString()).contains("token");

    }

    @Test
    @DisplayName("Deberia devolver http 404 cuando no se encuentra el usuario")
    void testAutenticarUsuarioFallido() throws Exception {

        // Given
        DatosAutenticacionUsuario datosValidos = new DatosAutenticacionUsuario(
            "franthielengaravito11@gmail.com",
            "123456",
            "Francisco",
            "Garavito",
            Rol.Paciente
        );

        when(authenticationManager.authenticate(any(Authentication.class))).thenAnswer(invocation -> {
            RequestContextHolder.currentRequestAttributes()
                .setAttribute("USER_NOT_FOUND", true, RequestAttributes.SCOPE_REQUEST);
            throw new AuthenticationException("Usuario no encontrado") {};
        });


        // When
        var result = mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(datosValidos)))
            .andDo(print())
            .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        // assertThat(result.getResponse().getContentAsString()).contains("token");
    }

     // Método auxiliar para convertir objetos a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.teleMedicina.teleMedicina.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleMedicina.teleMedicina.errores.ErrorResponse;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false) 
@Import({SecurityConfigurations.class})
public class UserControllerTest {

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
    @DisplayName("Deberia devolver http 201 cuando se crea con exito un Usuario")
    void testCreacionUsuario() throws Exception {
        // Given
        DatosAutenticacionUsuario datosRegistro  = new DatosAutenticacionUsuario(
            "franthielengaravito@gmail.com",
            "123456",
            "Francisco",
            "Garavito",
            Rol.Paciente
        );

        var respuestaEsperada = new DatosRespuestaUsuario(
            datosRegistro.email(),
            datosRegistro.nombre(),
            datosRegistro.apellido(),
            datosRegistro.rol()
        );

        when(userService.registerUser(any(DatosAutenticacionUsuario.class))).thenReturn(respuestaEsperada);

        // When
        var response = mockMvc.perform(post("/registro")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(datosRegistro)))
            .andDo(print())
            .andReturn();

        // Then
        assertThat(response.getResponse().getStatus())
            .isEqualTo(HttpStatus.CREATED.value());

        var responseBody = new ObjectMapper().readValue(
            response.getResponse().getContentAsString(),
            DatosRespuestaUsuario.class
        );

        assertThat(responseBody.email()).isEqualTo(datosRegistro.email());
        // assertThat(responseBody.nombre()).isEqualTo("Francisco");
    }

    @Test
    @DisplayName("Deberia devolver http 409 cuando el email ya existe")
    void testCreacionUsuarioEmailDuplicado() throws Exception {

        // Given
        var datosRegistro = new DatosAutenticacionUsuario(
            "franthielengaravito@gmail.com",
            "123456",
            "Francisco",
            "Garavito",
            Rol.Paciente
        );

        when(userService.registerUser(any(DatosAutenticacionUsuario.class)))
            .thenThrow(new DataIntegrityViolationException("UK_usuario_email"));

        // When
        var response = mockMvc.perform(post("/registro")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(datosRegistro)))
            .andDo(print())
            .andReturn();

        // Then
        assertThat(response.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT.value());

        ErrorResponse errorResponse = new ObjectMapper()
            .readValue(response.getResponse().getContentAsString(), ErrorResponse.class);

        assertThat(errorResponse.error())
            .isEqualTo("Ya existe un registro con esos datos");
        assertThat(errorResponse.status())
            .isEqualTo(HttpStatus.CONFLICT.value());
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

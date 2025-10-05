package com.teleMedicina.teleMedicina.models.usuarios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Debe encontrar un usuario por email")
    void testFindByEmail() {

        // Give
        this.registrarUsuario("franthielengaravito@gmail.com", "123456", "Francisco", "Garavito", Rol.Paciente);

        // Wen
        UserDetails usuarioEncontrado = usuarioRepository.findByEmail("franthielengaravito@gmail.com");

        // Then
        assertThat(usuarioEncontrado).isNotNull();

        Usuario usuario = (Usuario) usuarioEncontrado;

        assertThat(usuario)
            .satisfies(u -> {
                assertThat(u.getEmail()).isEqualTo("franthielengaravito@gmail.com");
                assertThat(u.getPassword()).isEqualTo("123456");
            });

    }

    @Test
    @DisplayName("Debe retornar null cuando el email no existe")
    void testFindByEmailNoExiste() {

        // When & Then
        assertThat(usuarioRepository.findByEmail("noexiste@email.com")).isNull();
        assertThat(usuarioRepository.findByEmail("")).isNull();
        assertThat(usuarioRepository.findByEmail(null)).isNull();
    }

    private Usuario registrarUsuario(String email, String clave, String nombre, String apellido, Rol rol) {

        Usuario usuario = new Usuario(new DatosAutenticacionUsuario(email, clave, nombre, apellido, rol));
        entityManager.persist(usuario);
        entityManager.flush();
        return usuario;
    }
}

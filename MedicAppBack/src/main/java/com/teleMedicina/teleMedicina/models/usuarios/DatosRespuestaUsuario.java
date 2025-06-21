package com.teleMedicina.teleMedicina.models.usuarios;

import com.teleMedicina.teleMedicina.models.Rol;

public record DatosRespuestaUsuario(
    String email,
    String nombre,
    String apellido,
    Rol rol
) {

    public DatosRespuestaUsuario(Usuario usuario) {
        this(
            usuario.getEmail(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getRol()
        );
    }
}

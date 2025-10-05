package com.teleMedicina.teleMedicina.models.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// import com.teleMedicina.teleMedicina.models.Rol;

public record DatosRegistroUsuario(

    @NotBlank
    @Email
    String email, 

    @NotBlank
    String clave,

    @NotBlank
    String nombre,

    @NotBlank
    String apellido,

    Rol rol
) {}

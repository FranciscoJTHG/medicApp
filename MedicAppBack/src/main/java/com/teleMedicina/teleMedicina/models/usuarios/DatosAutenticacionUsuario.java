package com.teleMedicina.teleMedicina.models.usuarios;

import com.teleMedicina.teleMedicina.models.Rol;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// import com.teleMedicina.teleMedicina.models.Rol;

public record DatosAutenticacionUsuario(

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
) {

}

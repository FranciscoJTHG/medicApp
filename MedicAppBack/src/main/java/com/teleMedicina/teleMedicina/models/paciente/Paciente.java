package com.teleMedicina.teleMedicina.models.paciente;

import com.teleMedicina.teleMedicina.models.citas.Cita;
import com.teleMedicina.teleMedicina.models.historialMedico.HistorialMedico;
import com.teleMedicina.teleMedicina.models.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPaciente;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @Column(name = "fecha_Nacimiento")
    private LocalDate fechaNacimiento;

    private String genero;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;

    @OneToMany(mappedBy = "paciente")
    private List<HistorialMedico> historialMedico;
}
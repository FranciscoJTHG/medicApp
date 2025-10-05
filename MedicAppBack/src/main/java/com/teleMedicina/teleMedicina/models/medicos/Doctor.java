package com.teleMedicina.teleMedicina.models.medicos;

import com.teleMedicina.teleMedicina.models.citas.Cita;
import com.teleMedicina.teleMedicina.models.especialidades.Especialidad;
import com.teleMedicina.teleMedicina.models.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private Integer idDoctor;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_especialidad", nullable = false)
    private Especialidad especialidad;

    @Column(name = "fecha_Nacimiento")
    private LocalDate fechaNacimiento;

    private String genero;
    
    private String biografia;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Cita> citas;
}
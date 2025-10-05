package com.teleMedicina.teleMedicina.models.citas;

import com.teleMedicina.teleMedicina.models.medicos.Doctor;
import com.teleMedicina.teleMedicina.models.paciente.Paciente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "cita")
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_doctor", nullable = false)
    private Doctor doctor;

    @Column(name = "fecha_Cita", nullable = false)
    private LocalDateTime fechaCita;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    private String notas;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private Consulta consulta;
}
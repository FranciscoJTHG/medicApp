package com.teleMedicina.teleMedicina.models.citas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
@Getter
@Setter
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;

    private String sintomas;
    
    private String diagnostico;
    
    private String prescripcion;

    @Column(name = "fecha_consulta", nullable = false)
    private LocalDateTime fechaConsulta;
}
package com.teleMedicina.teleMedicina.models.historialMedico;

import com.teleMedicina.teleMedicina.models.paciente.Paciente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_Medico")
@Getter
@Setter
public class HistorialMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private String descripcion;
    
    private String tipo;

    @Column(name = "fecha_Creacion", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaCreacion;
}
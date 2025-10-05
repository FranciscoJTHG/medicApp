package com.teleMedicina.teleMedicina.models.horarioDoctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "horario_doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioDoctor 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Long idHorario;

    @Column(name = "id_doctor", nullable = false)
    private Long idDoctor;

    @Column(name = "dia_semana", nullable = false)
    private String diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private String horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private String horaFin;
}

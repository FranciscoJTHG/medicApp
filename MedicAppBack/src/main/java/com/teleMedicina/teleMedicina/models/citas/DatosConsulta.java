package com.teleMedicina.teleMedicina.models.citas;

public class DatosConsulta {

    private Long citaId;
    private String diagnostico;

    // Getters and setters
    public Long getCitaId() {
        return citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}

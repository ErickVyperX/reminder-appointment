package gob.irencentro.reminderappointment.datalayer;

import java.time.LocalDateTime;

public interface PatientProjection {
    Long getPatientKey();
    String getNombreDelPaciente();
    String getCel();
    LocalDateTime getFechaCita();
    LocalDateTime getFechaRegistro();
    String getAgenda();
    Boolean getReminder();
}

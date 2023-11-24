package gob.irencentro.reminderappointment.datalayer;

import lombok.*;
import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDTO {
    private double patientKey;
    private String fullName;
    private String cellphone;
    private Timestamp appointmentDate;
    private Timestamp registerDate;
    private String attentionArea;
    private boolean reminderSent;
}

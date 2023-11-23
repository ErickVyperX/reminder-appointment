package gob.irencentro.reminderappointment.datalayer;

import java.sql.Timestamp;
import java.util.StringJoiner;

public class PatientDTO {
    private double patientKey;
    private String fullName;
    private String cellphone;
    private Timestamp appointmentDate;
    private Timestamp registerDate;
    private String attentionArea;
    private boolean reminderSent;

    public PatientDTO(){
    }

    public PatientDTO(double patientKey, String fullName, String cellphone, Timestamp appointmentDate, Timestamp registerDate, String attentionArea, boolean reminderSent) {
        this.patientKey = patientKey;
        this.fullName = fullName;
        this.cellphone = cellphone;
        this.appointmentDate = appointmentDate;
        this.registerDate = registerDate;
        this.attentionArea = attentionArea;
        this.reminderSent = reminderSent;
    }

    public double getPatientKey() {
        return patientKey;
    }

    public void setPatientKey(double patientKey) {
        this.patientKey = patientKey;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isReminderSent() {
        return reminderSent;
    }

    public void setReminderSent(boolean reminderSent) {
        this.reminderSent = reminderSent;
    }

    public String getAttentionArea() {
        return attentionArea;
    }

    public void setAttentionArea(String attentionArea) {
        this.attentionArea = attentionArea;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PatientDTO.class.getSimpleName() + "[", "]")
                .add("ID=" + patientKey)
                .add("FullName='" + fullName + "'")
                .add("Cel='" + cellphone + "'")
                .add("Register=" + registerDate)
                .add("Appointment=" + appointmentDate)
                .add("Area=" + attentionArea)
                .add("Reminder=" + reminderSent)
                .toString();
    }
}

package gob.irencentro.reminderappointment.datalayer;

import java.sql.Timestamp;
import java.util.StringJoiner;

public class PatientDTO {
    private double key;
    private String nombres;
    private String cel;
    private Timestamp fecha_cita;
    private Timestamp fecha_registro;
    private String area;
    private boolean reminder_sent;

    public PatientDTO(){
    }

    public PatientDTO(double key, String nombres, String cel, Timestamp fecha_cita, Timestamp fecha_registro, String area, boolean reminder_sent) {
        this.key = key;
        this.nombres = nombres;
        this.cel = cel;
        this.fecha_cita = fecha_cita;
        this.fecha_registro = fecha_registro;
        this.area = area;
        this.reminder_sent = reminder_sent;
    }

    public double getKey() {
        return key;
    }

    public void setKey(double key) {
        this.key = key;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public Timestamp getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Timestamp fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public Timestamp getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public boolean isReminder_sent() {
        return reminder_sent;
    }

    public void setReminder_sent(boolean reminder_sent) {
        this.reminder_sent = reminder_sent;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PatientDTO.class.getSimpleName() + "[", "]")
                .add("ID=" + key)
                .add("Nombres='" + nombres + "'")
                .add("Cel='" + cel + "'")
                .add("Registro=" + fecha_registro)
                .add("Cita=" + fecha_cita)
                .add("Area=" + area)
                .add("Reminder Sent=" + reminder_sent)
                .toString();
    }
}

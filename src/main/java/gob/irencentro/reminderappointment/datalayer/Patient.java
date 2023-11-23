package gob.irencentro.reminderappointment.datalayer;

import java.sql.Date;
import java.util.StringJoiner;

/*@Entity
@Table(schema = "prueba", name = "paciente")
@Repository*/
public class Patient {
    /*@Id
    @GeneratedValue*/
    private Long id;
    private String nombres;
    private Date cita;
    private int celular;

    public Patient() {
    }

    public Patient(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getCita() {
        return cita;
    }

    public void setCita(Date cita) {
        this.cita = cita;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Patient.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nombres='" + nombres + "'")
                .add("cita='" + cita + "'")
                .add("celular='" + celular + "'")
                .toString();
    }
}

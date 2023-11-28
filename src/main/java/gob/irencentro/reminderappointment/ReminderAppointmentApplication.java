package gob.irencentro.reminderappointment;

import gob.irencentro.reminderappointment.servicelayer.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReminderAppointmentApplication implements CommandLineRunner {
    PatientService patientService;

    @Autowired
    public ReminderAppointmentApplication(PatientService patientService) {
        this.patientService = patientService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReminderAppointmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientService.getPatientsWithAppointments();
    }
}

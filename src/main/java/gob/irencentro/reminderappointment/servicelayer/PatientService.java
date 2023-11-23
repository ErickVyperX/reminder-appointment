package gob.irencentro.reminderappointment.servicelayer;

import gob.irencentro.reminderappointment.datalayer.Patient;
import gob.irencentro.reminderappointment.datalayer.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> patientsWithAppointments() {
        return patientRepository.findAll();
    }

    public Patient patientErick() {
        return patientRepository.selectPatient();
    }
}

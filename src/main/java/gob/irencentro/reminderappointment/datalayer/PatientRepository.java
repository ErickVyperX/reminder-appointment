package gob.irencentro.reminderappointment.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM prueba.paciente WHERE nombres = 'Erick Villacorta'")
    Patient selectPatient();

}

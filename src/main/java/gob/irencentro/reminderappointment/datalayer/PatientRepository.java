package gob.irencentro.reminderappointment.datalayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PatientRepository {
    private final JdbcTemplate jdbcTemplate;
    private String patientsQuery;
    private final String reminderQuery;

    @Autowired
    public PatientRepository(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) throws IOException {
        this.jdbcTemplate = jdbcTemplate;
        this.patientsQuery = StreamUtils.copyToString(
                resourceLoader.getResource("classpath:query-patients-with-appointments.sql").getInputStream(),
                StandardCharsets.UTF_8);
        updateAppointmentDay();
        this.reminderQuery = StreamUtils.copyToString(
                resourceLoader.getResource("classpath:query_update_status_reminder.sql").getInputStream(),
                StandardCharsets.UTF_8);
    }

    public List<PatientDTO> findAll() {
        return jdbcTemplate.query(patientsQuery, new PatientRowMapper());
    }

    public void updateStatusReminder(PatientDTO patientDTO) {
        jdbcTemplate.update(reminderQuery, true, patientDTO.getPatientKey(), patientDTO.getAppointmentDate(), patientDTO.getRegisterDate());
    }

    public void updateAppointmentDay() {
        switch (LocalDate.now().getDayOfWeek()) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY ->
                this.patientsQuery = this.patientsQuery.replace("TO_RANGE_VALUE", "2");
            case FRIDAY ->
                this.patientsQuery = this.patientsQuery.replace("TO_RANGE_VALUE", "3");
        }
    }
}

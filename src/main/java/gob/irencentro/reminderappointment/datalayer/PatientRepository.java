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
    private String appointmentsQuery;

    @Autowired
    public PatientRepository(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) throws IOException {
        this.jdbcTemplate = jdbcTemplate;
        this.appointmentsQuery = StreamUtils.copyToString(
                resourceLoader.getResource("classpath:query-patients-with-appointments.sql").getInputStream(),
                StandardCharsets.UTF_8);
        updateAppointmentDay();
    }
    public List<PatientDTO> findAll() {
        return jdbcTemplate.query(appointmentsQuery, new PatientRowMapper());
    }

    public void updateAppointmentDay() {
        switch (LocalDate.now().getDayOfWeek()) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY ->
                this.appointmentsQuery = this.appointmentsQuery.replace("TO_RANGE_VALUE", "2");
            case FRIDAY ->
                this.appointmentsQuery = this.appointmentsQuery.replace("TO_RANGE_VALUE", "3");
        }
    }
}

package gob.irencentro.reminderappointment.datalayer;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<PatientDTO> {
    @Override
    public PatientDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientKey(rs.getInt("ID"));
        patientDTO.setFullName(rs.getString("FullName"));
        patientDTO.setCellphone(rs.getString("Cellphone"));
        patientDTO.setAppointmentDate(rs.getTimestamp("AppointmentDate"));
        patientDTO.setRegisterDate(rs.getTimestamp("RegisterDate"));
        patientDTO.setAttentionArea(rs.getString("AttentionArea"));
        patientDTO.setReminderSent(rs.getBoolean("ReminderSent"));
        return patientDTO;
    }
}

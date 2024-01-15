package gob.irencentro.reminderappointment.servicelayer;

import gob.irencentro.reminderappointment.datalayer.PatientDTO;
import gob.irencentro.reminderappointment.datalayer.PatientRepository;
import lombok.Getter;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Getter
public class PatientService {
    @Value("${api.token}")
    private String apiToken;
    PatientRepository patientRepository;
    @Value("${api.messages.url}")
    private String apiMessagesUrl;
    private final Logger logger;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public void getPatientsWithAppointments() throws IOException, InterruptedException {
        OkHttpClient client = new OkHttpClient();
        for (PatientDTO patientDTO : patientRepository.findAll()) {
            Response response = sendReminderAPI(client, patientDTO);
            updateStatusReminder(patientDTO);
            if (response.body() != null) {
                logger.info(">> Reminder sent to: {}", patientDTO);
            } else {
                logger.info(">> ERROR SENDING REMINDER TO: {}", patientDTO);
            }
            logger.info("Status Message API: {}", response.body().string());
            response.close();
            Thread.sleep(1000);
        }
    }

    private Response sendReminderAPI(OkHttpClient client, PatientDTO patientDTO) throws IOException {
        String formattedDate = formatDate(patientDTO.getAppointmentDate());
        String messageBody = buildMessageBody(patientDTO, formattedDate);
        RequestBody body = new FormBody.Builder()
                .add("token", getApiToken())
                .add("to", "51" + patientDTO.getCellphone() + "@c.us")
                .add("body", messageBody)
                .build();
        Request request = new Request.Builder()
                .url(getApiMessagesUrl())
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        return client.newCall(request).execute();
    }

    private void updateStatusReminder(PatientDTO patientDTO) {
        patientRepository.updateStatusReminder(patientDTO);
        patientDTO.setReminderSent(true);
    }

    private String buildMessageBody(PatientDTO patientDTO, String formattedDate) {
        return """
        \uD83E\uDD16 Hola %s
        \uD83C\uDFE5 IREN CENTRO te informa que tienes una cita pendiente!
        FECHA: %s.
        CONSULTORIO: %s.
        ¡RECUERDA ACERCARTE MEDIA HORA ANTES DE TU CITA!
        ==CONFIRMANOS TU ASISTENCIA==

        NOTA: Si crees que recibiste este mensaje por equivocación, por favor omítelo.
        """.formatted(patientDTO.getFullName(), formattedDate, patientDTO.getAttentionArea());
    }

    private String formatDate(Timestamp appointmentDate) {
        LocalDateTime localDateTime = appointmentDate.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy 'a las ' h:mm a");
        String[] daysOfWeek = new DateFormatSymbols().getWeekdays();
        String dayName = daysOfWeek[localDateTime.getDayOfWeek().getValue() + 1];
        return dayName.substring(0, 1).toUpperCase() + dayName.substring(1) + ", " + localDateTime.format(formatter);
    }
}

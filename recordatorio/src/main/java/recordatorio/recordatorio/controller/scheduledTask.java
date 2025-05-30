package recordatorio.recordatorio.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import recordatorio.recordatorio.DTO.binnacleDTO;

import recordatorio.recordatorio.model.reminder;
import recordatorio.recordatorio.service.binnacleService;
import recordatorio.recordatorio.service.emailService;
import recordatorio.recordatorio.service.reminderService;

@Component
public class scheduledTask {
@Autowired
private emailService email;
@Autowired
private reminderService reminderService;
@Autowired
private binnacleService binnacleService;

/*
 * cron = "* * * * * ?"
*/
@Scheduled(fixedRate = 300000)
public void taskCron(){
for (reminder r : reminderService.getListReminders()) {
    try {
    int id = r.getId_reminder();
    String emailTo = r.getPatient().getEmail();
    String name =r.getPatient().get_name();
    String doses = r.getMedicine().get_dose();
    LocalDateTime date = r.getDate_reminder();
    String idMedicine = r.getMedicine().get_name();    
    email.basicEmail(name, date, idMedicine, emailTo, doses, id);
    binnacleDTO binnacle = new binnacleDTO(
        0,
        id,
        LocalDateTime.now(),
        "Se envio correctamente"
    );
    binnacleService.save(binnacle);
    System.out.println("Recordatorio enviado a: " + emailTo + " para la medicina: " + idMedicine);
    } catch (Exception e) {
        System.out.println("Error al enviar el recordatorio: " + e.getMessage());
    }

}

}


}

package recordatorio.recordatorio.DTO;
import java.time.LocalDateTime;
public class reminderDTO {
private int id_reminder;
private int patient;
private int medicine;
private LocalDateTime date_reminder;
private int time_reminder;
private boolean status;
public reminderDTO() {

}
public reminderDTO(int id_reminder, int patient, int medicine, LocalDateTime date_reminder, int time_reminder, boolean status) {
    this.id_reminder = id_reminder;
    this.patient = patient;
    this.medicine = medicine;
    this.date_reminder = date_reminder;
    this.time_reminder = time_reminder;
    this.status = status;
    }
public int getId_reminder() {
    return id_reminder;
}
public void setId_reminder(int id_reminder) {
    this.id_reminder = id_reminder;
}
public int getPatient() {
    return patient;
}
public void setPatient(int patient) {
    this.patient = patient;
}
public int getMedicine() {
    return medicine;
}
public void setMedicine(int medicine) {
    this.medicine = medicine;
}
public LocalDateTime getDate_reminder() {
    return date_reminder;
}
public void setDate_reminder(LocalDateTime date_reminder) {
    this.date_reminder = date_reminder;
}
public int getTime_reminder() {
    return time_reminder;
}
public void setTime_reminder(int time_reminder) {
    this.time_reminder = time_reminder;
}
public boolean getStatus() {
    return status;
}
public void setStatus(boolean status) {
    this.status = status;
}
}

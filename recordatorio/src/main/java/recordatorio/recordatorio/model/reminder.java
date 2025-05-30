package recordatorio.recordatorio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

import jakarta.persistence.Column;

@Entity(name = "reminder")
public class reminder {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_reminder", nullable = false)
private int id_reminder;
@ManyToOne
@JoinColumn(name = "id_patient", nullable = false)
private patient patient;
@ManyToOne
@JoinColumn(name = "id_medicine", nullable = false)
private medicine medicine;
@Column(name = "date_reminder", nullable = false)
private LocalDateTime date_reminder;
@Column(name = "time_reminder", nullable = false)
private int time_reminder;
@Column(name = "status", nullable = false)
private boolean status;
public reminder() {

}
public reminder(int id_reminder, patient patient, medicine medicine, LocalDateTime date_reminder, int time_reminder, boolean status) {
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
public patient getPatient() {
    return patient;
}
public void setPatient(patient patient) {
    this.patient = patient;
}
public medicine getMedicine() {
    return medicine;
}
public void setMedicine(medicine medicine) {
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

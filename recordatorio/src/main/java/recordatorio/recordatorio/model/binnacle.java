package recordatorio.recordatorio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;


@Entity(name = "binnacle")
public class binnacle {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_binnacle", nullable = false)
private int id_binnacle;

@ManyToOne 
@JoinColumn(name ="id_reminder", nullable = false)
private reminder id_reminder;

@Column(name = "shipping_date", nullable = false)
private LocalDateTime shipping_date;
@Column(name = "shipping_confirmation", nullable = false)
private String shipping_confirmation;
public binnacle() {
}
public binnacle(int id_binnacle, reminder id_reminder, LocalDateTime shipping_date, String shipping_confirmation) {
    this.id_binnacle = id_binnacle;
    this.id_reminder = id_reminder;
    this.shipping_date = shipping_date;
    this.shipping_confirmation = shipping_confirmation;
    }

public int getId_binnacle() {
    return this.id_binnacle;
}

public void setId_binnacle(int id_binnacle) {
    this.id_binnacle = id_binnacle;
}

public reminder getId_reminder() {
    return this.id_reminder;
}

public void setId_reminder(reminder id_reminder) {
    this.id_reminder = id_reminder;
}

public LocalDateTime getShipping_date() {
    return this.shipping_date;
}

public void setShipping_date(LocalDateTime shipping_date) {
    this.shipping_date = shipping_date;
}

public String getShipping_confirmation() {
    return this.shipping_confirmation;
}

public void setShipping_confirmation(String shipping_confirmation) {
    this.shipping_confirmation = shipping_confirmation;
}   
}

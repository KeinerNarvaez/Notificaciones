package recordatorio.recordatorio.DTO;
import java.time.LocalDateTime;

public class binnacleDTO {
private int id_binnacle;
private int id_reminder;
private LocalDateTime shipping_date;
private String shipping_confirmation;
public binnacleDTO(int id_binnacle, int id_reminder, LocalDateTime shipping_date, String shipping_confirmation) {
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

public int getId_reminder() {
    return this.id_reminder;
}

public void setId_reminder(int id_reminder) {
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

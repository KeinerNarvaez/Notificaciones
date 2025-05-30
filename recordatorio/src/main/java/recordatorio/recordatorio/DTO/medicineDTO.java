package recordatorio.recordatorio.DTO;

public class medicineDTO {

 private int id_medicine;


 private String name;

 private String dose;

 public medicineDTO(int id_medicine, String name, String dose) {
     this.id_medicine = id_medicine;
     this.name = name;
     this.dose = dose;
 }
 public int getId_medicine() {
     return id_medicine;
 }

 public void setId_medicine(int id_medicine) {
     this.id_medicine = id_medicine;
 }

 public String get_name() {
     return name;
 }

 public void set_name(String name) {
     this.name = name;
 }

 public String get_dose() {
     return dose;
 }

 public void set_dose(String dose) {
     this.dose = dose;
 }
}

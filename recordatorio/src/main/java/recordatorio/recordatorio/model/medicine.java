package recordatorio.recordatorio.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity(name = "medicine")
public class medicine {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id_medicine")
 private int id_medicine;

 @Column(name = "name", length = 100, nullable = false)
 private String name;

 @Column(name = "dose", length = 100, nullable = false)
 private String dose;

 public medicine() {
 }

 public medicine(int id_medicine, String name, String dose) {
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

package recordatorio.recordatorio.DTO;

public class patientDTO {
   private int id_patient;
   private String name;
   private String email;
   private boolean status;

   public patientDTO(int id_patient, String name, String email, boolean status) {
      this.id_patient = id_patient;
      this.name = name;
      this.email = email;
      this.status = status;
   }

   // get del ID
   public int getId_user() {
      return id_patient;
   }

   // set del ID
   public void setId_user(int id_patient) {
      this.id_patient = id_patient;
   }

   // get del firstName
   public String get_name() {
      return name;
   }

   // set del firstName
   public void set_name(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public boolean getStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }
}


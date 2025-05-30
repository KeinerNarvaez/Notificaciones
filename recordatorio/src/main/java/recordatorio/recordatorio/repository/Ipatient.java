package recordatorio.recordatorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import recordatorio.recordatorio.model.patient;

public interface Ipatient extends JpaRepository
<patient, Integer> 
{
    @Query("SELECT u FROM patient u WHERE u.status != false")
    List<patient> getListUserActive();
    @Query("SELECT u FROM patient u WHERE u.email=?1")
    List<patient> getListPatientByEmail(String email);
}

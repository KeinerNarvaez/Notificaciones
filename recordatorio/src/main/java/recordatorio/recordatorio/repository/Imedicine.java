package recordatorio.recordatorio.repository;
import org.springframework.data.jpa.repository.JpaRepository;


import recordatorio.recordatorio.model.medicine;

public interface Imedicine extends JpaRepository
<medicine, Integer> 
{
}

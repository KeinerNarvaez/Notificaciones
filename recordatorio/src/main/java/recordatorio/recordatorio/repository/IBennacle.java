package recordatorio.recordatorio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


import recordatorio.recordatorio.model.binnacle;

public interface IBennacle extends JpaRepository
<binnacle, Integer> 
{
    @Query("SELECT b FROM binnacle b WHERE b.shipping_confirmation =?1")
    List<binnacle> getList(String shipping_confirmation);
    
}

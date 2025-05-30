package recordatorio.recordatorio.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import recordatorio.recordatorio.model.reminder;
public interface IReminder extends JpaRepository<reminder, Integer> 
{
    @Query("SELECT u FROM reminder u WHERE u.status != false")
    List<reminder> getListReminders();
}

package recordatorio.recordatorio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import recordatorio.recordatorio.DTO.reminderDTO;
import recordatorio.recordatorio.DTO.responseDTO;
import recordatorio.recordatorio.model.medicine;
import recordatorio.recordatorio.model.patient;
import recordatorio.recordatorio.model.reminder;
import recordatorio.recordatorio.repository.IReminder;

@Service
public class reminderService {
   @Autowired
    private IReminder reminderRepository;

    @Autowired
    private patientService patientService;
    @Autowired
    private medicineService medicineService;

    public List<reminder> findAll() {
        return reminderRepository.findAll();
    }
    public List<reminder> getListReminders() {
        return reminderRepository.getListReminders();
    }
    
    public Optional<reminder> findById(int id) {
        return reminderRepository.findById(id);
    }

    public responseDTO delete(int id) {
        Optional<reminder> reminder = findById(id);
        if (!reminder.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "La factura de préstamo no existe.");
        }
        reminderRepository.deleteById(id);
        return new responseDTO(HttpStatus.OK, "Factura de préstamo eliminada correctamente.");
    }

    public responseDTO update(int id, reminderDTO reminderDTO) {
        Optional<reminder> existingLoanOpt = findById(id);

        if (!existingLoanOpt.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "La factura de préstamo no existe.");
        }

        Optional<patient> patientOptional = patientService.findById(reminderDTO.getPatient());
        if (!patientOptional.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "El usuario no existe.");
        }
        Optional<medicine> medicineOptional = medicineService.findById(reminderDTO.getMedicine());
        if (!patientOptional.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "La medicina no existe.");
        }

        try {
            reminder existingLoan = existingLoanOpt.get();
            existingLoan.setPatient(patientOptional.get());
            existingLoan.setMedicine(medicineOptional.get());
            existingLoan.setDate_reminder(reminderDTO.getDate_reminder());
            existingLoan.setTime_reminder(reminderDTO.getTime_reminder());
            existingLoan.setStatus(reminderDTO.getStatus());

            reminderRepository.save(existingLoan);

            return new responseDTO(HttpStatus.OK, "Factura de préstamo actualizada correctamente.");
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDTO save(reminderDTO reminderDTO) {
        Optional<patient> patientOptional = patientService.findById(reminderDTO.getPatient());
        if (!patientOptional.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "The user with ID " + reminderDTO.getPatient() + " does not exist.");
        }
        Optional<medicine> medicineOptional = medicineService.findById(reminderDTO.getMedicine());
        if (!medicineOptional.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "The user with ID " + reminderDTO.getMedicine() + " does not exist.");
        }

        reminder record = convertToModel(reminderDTO, patientOptional.get(),medicineOptional.get());

        reminder savedLoan = reminderRepository.save(record);
        reminderDTO responseLoan = convertToDTO(savedLoan);

        return new responseDTO(HttpStatus.OK, "Reminder saved correctly.", responseLoan);
    }

    public reminderDTO convertToDTO(reminder reminder) {
        return new reminderDTO(
            reminder.getId_reminder(),
            reminder.getPatient().getId_user(),
            reminder.getMedicine().getId_medicine(),
            reminder.getDate_reminder(),
            reminder.getTime_reminder(),
            reminder.getStatus()
        );
    }

    public reminder convertToModel(reminderDTO reminderDTO, patient patient, medicine medicineRol) {
        return new reminder(
            reminderDTO.getId_reminder(),
            patient,
            medicineRol,
            LocalDateTime.now(),
            reminderDTO.getTime_reminder(),
            true
        );
    }
}

package recordatorio.recordatorio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import recordatorio.recordatorio.model.binnacle;
import recordatorio.recordatorio.model.reminder;
import recordatorio.recordatorio.DTO.responseDTO;
import recordatorio.recordatorio.DTO.binnacleDTO;

import recordatorio.recordatorio.repository.IBennacle;
@Service
public class binnacleService {
   @Autowired
    private IBennacle binnacleRepository;

    @Autowired
    private reminderService reminderService;

    public List<binnacle> findAll() {
        return binnacleRepository.findAll();
    }
    
    public Optional<binnacle> findById(int id) {
        return binnacleRepository.findById(id);
    }
    public List<binnacle> getList(String confirmation) {
        return binnacleRepository.getList(confirmation);
    }
    public responseDTO delete(int id) {
        Optional<binnacle> binnacle = findById(id);
        if (!binnacle.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "La factura de préstamo no existe.");
        }
        binnacleRepository.deleteById(id);
        return new responseDTO(HttpStatus.OK, "Factura de préstamo eliminada correctamente.");
    }

    public responseDTO update(int id, binnacleDTO binnacleDTO) {
        Optional<binnacle> existingLoanOpt = findById(id);

        if (!existingLoanOpt.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "La factura de préstamo no existe.");
        }

        Optional<reminder> reminderOptional = reminderService.findById(binnacleDTO.getId_reminder());
        if (!reminderOptional.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "El usuario no existe.");
        }

        try {
            binnacle existingLoan = existingLoanOpt.get();
            existingLoan.setId_reminder(reminderOptional.get());
            existingLoan.setShipping_date(binnacleDTO.getShipping_date());
            existingLoan.setShipping_confirmation(binnacleDTO.getShipping_confirmation());


            binnacleRepository.save(existingLoan);

            return new responseDTO(HttpStatus.OK, "Factura de préstamo actualizada correctamente.");
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDTO save(binnacleDTO binnacleDTO) {
        Optional<reminder> reminderOptional = reminderService.findById(binnacleDTO.getId_reminder());
        if (!reminderOptional.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "The user with ID " + binnacleDTO.getId_reminder() + " does not exist.");
        }

        binnacle record = convertToModel(binnacleDTO, reminderOptional.get());

        binnacle savedLoan = binnacleRepository.save(record);
        binnacleDTO responseLoan = convertToDTO(savedLoan);

        return new responseDTO(HttpStatus.OK, "Loan bill saved correctly.", responseLoan);
    }

    public binnacleDTO convertToDTO(binnacle binnacle) {
        return new binnacleDTO(
            binnacle.getId_binnacle(),
            binnacle.getId_reminder().getId_reminder(),
            binnacle.getShipping_date(),
            binnacle.getShipping_confirmation()
        );
    }

    public binnacle convertToModel(binnacleDTO binnacleDTO, reminder reminder) {
        return new binnacle(
            binnacleDTO.getId_binnacle(),
            reminder,
            LocalDateTime.now(),
            binnacleDTO.getShipping_confirmation()
        );
    }
}

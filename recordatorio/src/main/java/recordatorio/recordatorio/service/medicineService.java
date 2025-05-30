package recordatorio.recordatorio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import recordatorio.recordatorio.DTO.medicineDTO;
import recordatorio.recordatorio.DTO.responseDTO;
import recordatorio.recordatorio.model.medicine;
import recordatorio.recordatorio.repository.Imedicine;
@Service
public class medicineService {
    @Autowired
    private Imedicine data;

    public List<medicine> findAll() {
        return data.findAll();
    }

    public Optional<medicine> findById(int id) {
        return data.findById(id);
    }
    public responseDTO delete(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                    HttpStatus.OK,
                    "The register does not exist");
            return respuesta;
        }
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
                HttpStatus.OK,
                "It was deleted correctly");
        return respuesta;
    }

    // register and update
    public responseDTO save(medicineDTO medicineDTO) {
        // validación longitud del nombre
        if (medicineDTO.get_name().length() < 1 ||
                medicineDTO.get_name().length() > 100) {
            responseDTO respuesta = new responseDTO(
                    HttpStatus.BAD_REQUEST,
                    "El nombre debe estar entre 1 y 100 caracteres");
            return respuesta;
        }
        // otras condiciones
        // n
        medicine author_Registro = convertToModel(medicineDTO);
        data.save(author_Registro);
        responseDTO respuesta = new responseDTO(
                HttpStatus.OK,
                "Se guardó correctamente");
        return respuesta;

    }
    public responseDTO update(int id, medicineDTO medicineDTO) {
        Optional<medicine> existingAuthorOpt = findById(id);
        
        if (!existingAuthorOpt.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "El autor no existe");
        }
    
        // Validaciones
        if (medicineDTO.get_name().length() < 1 || medicineDTO.get_name().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST, "El nombre debe tener entre 1 y 50 caracteres");
        }
    
        medicine existingAuthor = existingAuthorOpt.get();
        existingAuthor.set_name(medicineDTO.get_name());
        existingAuthor.set_dose(medicineDTO.get_dose());
    
        data.save(existingAuthor);
    
        return new responseDTO(HttpStatus.OK, "Medicina actualizado correctamente");
    }
    public medicineDTO convertToDTO(medicine medicine) {
        medicineDTO medicineDTO = new medicineDTO(
                medicine.getId_medicine(),
                medicine.get_name(),
                medicine.get_dose());
        return medicineDTO;
    }

    public medicine convertToModel(medicineDTO medicineDTO) {
        medicine medicine = new medicine(
                0,
                medicineDTO.get_name(),
                medicineDTO.get_dose());
        return medicine;
    }
}

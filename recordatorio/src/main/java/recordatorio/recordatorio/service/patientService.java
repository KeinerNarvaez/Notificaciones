package recordatorio.recordatorio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import recordatorio.recordatorio.model.patient;
import recordatorio.recordatorio.repository.Ipatient;
import recordatorio.recordatorio.DTO.patientDTO;
import recordatorio.recordatorio.DTO.responseDTO;

@Service
public class patientService {
    /*
     * save
     * findAll
     * findById
     * Delete
     */
    /* establish connection with the interface */
    @Autowired
    private Ipatient data;

    public List<patient> findAll() {
        return data.getListUserActive();
    }

    public Optional<patient> findById(int id) {
        return data.findById(id);
    }
    public List<patient> getListPatientByEmail(String email) {
        return data.getListPatientByEmail(email);
    }

    public responseDTO deleteUser(int id) {
        Optional<patient> patient=findById(id);
        if (!patient.isPresent()) {
            responseDTO respuesta = new responseDTO(
                    HttpStatus.OK,
                    "The register does not exist");
            return respuesta;
        }
        patient.get().setStatus(false);
        data.save(patient.get());
        // data.deleteById(id);
        
        responseDTO respuesta = new responseDTO(
                HttpStatus.OK,
                "Se eliminó correctamente");
        return respuesta;
    }
    public responseDTO update(int id, patientDTO patientDTO) {
        Optional<patient> userOptional = findById(id);
        
        if (!userOptional.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "El usuario no existe");
        }
    
        // Validación del nombre
        if (patientDTO.get_name().length() < 1 || patientDTO.get_name().length() > 100) {
            return new responseDTO(HttpStatus.BAD_REQUEST, "El nombre debe tener entre 1 y 100 caracteres");
        }
    
        try {
            patient existingUser = userOptional.get();
            existingUser.set_name(patientDTO.get_name());
            existingUser.setEmail(patientDTO.getEmail());

            data.save(existingUser);
    
            return new responseDTO(HttpStatus.OK, "Usuario actualizado correctamente");
    
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }
    
    // register and update
    public responseDTO save(patientDTO patientDTO) {
        // validación longitud del nombre
        if (patientDTO.get_name().length() < 1 ||
                patientDTO.get_name().length() > 50) {
            responseDTO respuesta = new responseDTO(
                    HttpStatus.BAD_REQUEST,
                    "El nombre debe estar entre 1 y 50 caracteres");
            return respuesta;
        }
        // otras condiciones
        // n
        patient userRegister = converToModel(patientDTO);
        data.save(userRegister);
        responseDTO respuesta = new responseDTO(
                HttpStatus.OK,
                "Se guardó correctamente");
        return respuesta;
    }

    public patientDTO convertToDTO(patient patient) {
        patientDTO patientDTO = new patientDTO(
                patient.getId_user(),
                patient.get_name(),
                patient.getEmail(),
                patient.getStatus()
                );
        return patientDTO;
    }

    public patient converToModel(patientDTO patientDTO) {
        patient patient = new patient(
                0,
                patientDTO.get_name(),
                patientDTO.getEmail(),
                true);
        return patient;
    }
}

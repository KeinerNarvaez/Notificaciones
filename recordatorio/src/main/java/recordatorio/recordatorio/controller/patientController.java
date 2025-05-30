package recordatorio.recordatorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import recordatorio.recordatorio.DTO.patientDTO;
import recordatorio.recordatorio.DTO.responseDTO;
import recordatorio.recordatorio.service.patientService;

@RestController
@RequestMapping("/api/v1/patient")
public class patientController {
    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private patientService patientService;

    @PostMapping("/")
    public ResponseEntity<Object> registerpatient(@RequestBody patientDTO patient) {
        responseDTO respuesta = patientService.save(patient);
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUser() {
        var listaUsuario = patientService.findAll();
        return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
    }

    /*
     * Se requiere un dato, el ID
     * PathVariable=captura de información por la URL
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int id) {
        var usuario = patientService.findById(id);
        if (!usuario.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        var message = patientService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> updateuser(@PathVariable int id,@RequestBody patientDTO patientDTO) {
        responseDTO respuesta = patientService.update(id, patientDTO);
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        var userList = patientService.getListPatientByEmail(email);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }    
}

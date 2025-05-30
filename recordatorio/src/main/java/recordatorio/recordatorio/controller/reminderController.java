package recordatorio.recordatorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import recordatorio.recordatorio.DTO.reminderDTO;
import recordatorio.recordatorio.DTO.responseDTO;
import recordatorio.recordatorio.model.reminder;
import recordatorio.recordatorio.service.reminderService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/reminder")
@RestController

public class reminderController {
    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private reminderService reminderService;

    @PostMapping("/")
    public ResponseEntity<Object> registerreminder(@RequestBody reminderDTO reminder) {
        responseDTO respuesta = reminderService.save(reminder);
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUser() {
        var listaRecordatorio = reminderService.findAll();
        return new ResponseEntity<>(listaRecordatorio, HttpStatus.OK);
    }

    /*
     * Se requiere un dato, el ID
     * PathVariable=captura de información por la URL
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int id) {
        var Recordatorio = reminderService.findById(id);
        if (!Recordatorio.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Recordatorio, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        var message = reminderService.delete(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> updateuser(@PathVariable int id,@RequestBody reminderDTO reminderDTO) {
        responseDTO respuesta = reminderService.update(id, reminderDTO);
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }
    @GetMapping("/active/")
    public ResponseEntity<List<reminder>> getListReminders() {
        return ResponseEntity.ok(reminderService.getListReminders());
    }   
}

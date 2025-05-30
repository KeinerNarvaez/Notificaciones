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

import recordatorio.recordatorio.DTO.medicineDTO;
import recordatorio.recordatorio.DTO.responseDTO;
import recordatorio.recordatorio.service.medicineService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/medicine")
public class medicineController {
    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private medicineService medicineService;

    @PostMapping("/")
    public ResponseEntity<Object> registermedicine(@RequestBody medicineDTO medicine) {
        responseDTO respuesta = medicineService.save(medicine);
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUser() {
        var listaUsuario = medicineService.findAll();
        return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
    }

    /*
     * Se requiere un dato, el ID
     * PathVariable=captura de información por la URL
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int id) {
        var usuario = medicineService.findById(id);
        if (!usuario.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        var message = medicineService.delete(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> updateuser(@PathVariable int id,@RequestBody medicineDTO medicineDTO) {
        responseDTO respuesta = medicineService.update(id, medicineDTO);
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }
}

package recordatorio.recordatorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import recordatorio.recordatorio.DTO.binnacleDTO;
import recordatorio.recordatorio.DTO.responseDTO;
import recordatorio.recordatorio.model.binnacle;

import recordatorio.recordatorio.service.binnacleService;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/v1/binnacle")
@RestController
public class bennacleController {
    @Autowired
    private binnacleService binnacleService;

    @PostMapping("/")
    public ResponseEntity<Object> registerbinnacle(@RequestBody binnacleDTO binnacle) {
        responseDTO respuesta = binnacleService.save(binnacle);
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUser() {
        var listaRecordatorio = binnacleService.findAll();
        return new ResponseEntity<>(listaRecordatorio, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int id) {
        var Recordatorio = binnacleService.findById(id);
        if (!Recordatorio.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Recordatorio, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        var message = binnacleService.delete(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> updateuser(@PathVariable int id,@RequestBody binnacleDTO binnacleDTO) {
        responseDTO respuesta = binnacleService.update(id, binnacleDTO);
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }
    @GetMapping("/confirmation/{confirmation}")
    public ResponseEntity<List<binnacle>> getList(@PathVariable String confirmation) {
        return ResponseEntity.ok(binnacleService.getList(confirmation));
    }  

}

package es.upm.dit.isst.mascotmercioapiback.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.upm.dit.isst.mascotmercioapiback.model.Reservas;
import es.upm.dit.isst.mascotmercioapiback.repository.ReservasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;



@CrossOrigin(origins = "http://localhost:8083/")
@RestController
@RequestMapping("/myApi")
public class ReservasController {
    
     private final ReservasRepository reservasRepository;

    public ReservasController(ReservasRepository reservasRepository) {
        
        this.reservasRepository = reservasRepository;
    }

    @GetMapping("/reservas")
    ResponseEntity<List<Reservas>> getReservas() {
            return ResponseEntity.ok().body(reservasRepository.findAll());
        
    }

    @PostMapping("/reservas")
    ResponseEntity<Reservas> newReservas(@RequestBody Reservas newReserva ) {
        return ResponseEntity.ok().body(reservasRepository.save(newReserva));
    }

    @GetMapping("/reservas/{id}")
    ResponseEntity<Reservas> getReservasById(@PathVariable Long id) {
        return reservasRepository.findById(id).map(reserva -> ResponseEntity.ok().body(reserva))
                .orElse(null);
    }

    
}


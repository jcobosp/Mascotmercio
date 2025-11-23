package es.upm.dit.isst.mascotmercioapiback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.mascotmercioapiback.model.Valoracion;
import es.upm.dit.isst.mascotmercioapiback.repository.ValoracionRepository;

@CrossOrigin(origins = "http://localhost:8083/")
@RestController
@RequestMapping("/myApi")
public class ValoracionController {

    private final ValoracionRepository valoracionRepository;

    public ValoracionController(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    @GetMapping("/valoraciones")
    ResponseEntity<List<Valoracion>> all() {
        return ResponseEntity.ok().body(valoracionRepository.findAll());
    }

    @PostMapping("/valoraciones")
    ResponseEntity<Valoracion> newValoracion(@RequestBody Valoracion newValoracion) {
        return ResponseEntity.ok().body(valoracionRepository.save(newValoracion));
    }

    // Single item

    @GetMapping("/valoraciones/{id}")
    ResponseEntity<Valoracion> one(@PathVariable Long id) {
        return valoracionRepository.findById(id).map(valoracion -> ResponseEntity.ok().body(valoracion))
                .orElse(new ResponseEntity<Valoracion>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/valoraciones/{id}")
    ResponseEntity<Valoracion> update(@PathVariable Long id, @RequestBody Valoracion newValoracion) {
        return valoracionRepository.findById(id).map(valoracion -> {
            valoracion.setPuntuacion(newValoracion.getPuntuacion());
            valoracion.setComentario(newValoracion.getComentario());
            valoracion.setUsuario(newValoracion.getUsuario());
            valoracion.setEstablecimiento(newValoracion.getEstablecimiento());
            valoracionRepository.save(valoracion);
            return ResponseEntity.ok().body(valoracion);
        }).orElse(new ResponseEntity<Valoracion>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/valoraciones/{id}")
    ResponseEntity<Valoracion> delete(@PathVariable Long id) {
        valoracionRepository.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}
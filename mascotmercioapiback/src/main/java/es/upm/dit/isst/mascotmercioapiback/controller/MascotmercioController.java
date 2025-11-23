package es.upm.dit.isst.mascotmercioapiback.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.mascotmercioapiback.model.Reservas;
import es.upm.dit.isst.mascotmercioapiback.model.Valoracion;
import es.upm.dit.isst.mascotmercioapiback.repository.EstablecimientoRepository;
import es.upm.dit.isst.mascotmercioapiback.repository.ReservasRepository;
import es.upm.dit.isst.mascotmercioapiback.repository.UsuarioRepository;
import es.upm.dit.isst.mascotmercioapiback.repository.ValoracionRepository;

@CrossOrigin(origins = "http://localhost:8083/")
@RestController
@RequestMapping("/myApi")
public class MascotmercioController {

    private final EstablecimientoRepository establecimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ValoracionRepository valoracionRepository;
    private final ReservasRepository reservaRepository;

    public MascotmercioController(EstablecimientoRepository establecimientoRepository,
            UsuarioRepository usuarioRepository, ValoracionRepository valoracionRepository,
            ReservasRepository reservaRepository) {
        this.establecimientoRepository = establecimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.valoracionRepository = valoracionRepository;
        this.reservaRepository = reservaRepository;
    }

    @GetMapping("/establecimientos/{id}/valoraciones")
    ResponseEntity<List<Valoracion>> getValoracionesByEstablecimiento(@PathVariable Long id) {
        return ResponseEntity.ok().body(valoracionRepository.findByEstablecimiento(id));
    }

    @PostMapping("/establecimientos/{id}/valoraciones")
    ResponseEntity<Valoracion> createValoracion(@PathVariable Long id,
            @RequestBody Valoracion val) {
        return establecimientoRepository.findById(id).map(establecimiento -> {
            Valoracion newValoracion = new Valoracion();
            newValoracion.setPuntuacion(val.getPuntuacion());
            newValoracion.setTitulo(val.getTitulo());
            newValoracion.setComentario(val.getComentario());
            newValoracion.setUsuario(val.getUsuario());
            newValoracion.setEstablecimiento(establecimiento.getId());
            Valoracion savedValoracion = valoracionRepository.save(newValoracion);
            return ResponseEntity.ok().body(savedValoracion);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/establecimientos/{id}/reservas")
    ResponseEntity<List<Reservas>> getReservasByEstablecimiento(@PathVariable Long id) {
        return ResponseEntity.ok().body(reservaRepository.findByIdEstablecimiento(id));
    }
}

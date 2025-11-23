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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.mascotmercioapiback.model.Establecimiento;
import es.upm.dit.isst.mascotmercioapiback.repository.EstablecimientoRepository;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:8083/")
@RestController
@RequestMapping("/myApi")
public class EstablecimientoController {

    private final EstablecimientoRepository establecimientoRepository;

    public EstablecimientoController(EstablecimientoRepository establecimientoRepository) {

        this.establecimientoRepository = establecimientoRepository;
    }

    @GetMapping("/establecimientos")
    ResponseEntity<List<Establecimiento>> getByCodigoPostal(
            @RequestParam(name = "codigoPostal", required = false) String codigoPostal) {
        if (codigoPostal != null && !codigoPostal.isEmpty()) {
            List<Establecimiento> establecimientosByCodigoPostal = establecimientoRepository
                    .findByCodigoPostal(codigoPostal);
            return ResponseEntity.ok().body(establecimientosByCodigoPostal);
        } else {
            return ResponseEntity.ok().body(establecimientoRepository.findAll());
        }
    }

    @PostMapping("/establecimientos")
    ResponseEntity<Establecimiento> newEstablecimiento(@RequestBody Establecimiento newEstablecimiento,
            HttpServletRequest request) {
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            System.out.println("Header: " + headerName + " Value: " + request.getHeader(headerName));
        });

        System.out.println("Received Request Body: " + newEstablecimiento);
        return ResponseEntity.ok().body(establecimientoRepository.save(newEstablecimiento));
    }

    // Single item

    @GetMapping("/establecimientos/{id}")
    ResponseEntity<Establecimiento> one(@PathVariable Long id) {
        return establecimientoRepository.findById(id).map(establecimiento -> ResponseEntity.ok().body(establecimiento))
                .orElse(new ResponseEntity<Establecimiento>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/establecimientos/usuario/{usuario}")
    ResponseEntity<List<Establecimiento>> filterByOwner(@PathVariable String usuario) {

        List<Establecimiento> establecimientosByCodigoPostal = establecimientoRepository.findByUsuario(usuario);
        return ResponseEntity.ok().body(establecimientosByCodigoPostal);

    }

    @PutMapping("/establecimientos/{id}")
    ResponseEntity<Establecimiento> update(@PathVariable Long id, @RequestBody Establecimiento newEstablecimiento) {
        return establecimientoRepository.findById(id).map(establecimiento -> {
            establecimiento.setNombre(newEstablecimiento.getNombre());
            establecimiento.setFoto(newEstablecimiento.getFoto());
            establecimiento.setEmail(newEstablecimiento.getEmail());
            establecimiento.setPais(newEstablecimiento.getPais());
            establecimiento.setTelefono(newEstablecimiento.getTelefono());
            establecimiento.setDireccion(newEstablecimiento.getDireccion());
            establecimiento.setCodigoPostal(newEstablecimiento.getCodigoPostal());
            establecimiento.setCiudad(newEstablecimiento.getCiudad());
            establecimiento.setProvincia(newEstablecimiento.getProvincia());
            establecimiento.setDescripcion(newEstablecimiento.getDescripcion());
            establecimiento.setServiciosOfrecidos(newEstablecimiento.getServiciosOfrecidos());
            establecimiento.setMascotasAceptadas(newEstablecimiento.getMascotasAceptadas());
            establecimiento.setUsuario(newEstablecimiento.getUsuario());
            establecimiento.setEstrellas(newEstablecimiento.getEstrellas());

            // establecimiento.setValoraciones(newEstablecimiento.getValoraciones());
            establecimientoRepository.save(establecimiento);
            return ResponseEntity.ok().body(establecimiento);
        }).orElse(new ResponseEntity<Establecimiento>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/establecimientos/{id}")
    ResponseEntity<Establecimiento> delete(@PathVariable Long id) {
        establecimientoRepository.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}
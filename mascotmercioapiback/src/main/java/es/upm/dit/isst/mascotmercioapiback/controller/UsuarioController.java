package es.upm.dit.isst.mascotmercioapiback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.mascotmercioapiback.model.Usuario;
import es.upm.dit.isst.mascotmercioapiback.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:8083/")
@RestController
@RequestMapping("/myApi")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuarios")
    ResponseEntity<List<Usuario>> all() {
        return ResponseEntity.ok().body(usuarioRepository.findAll());
    }

    @PostMapping("/usuarios")
    ResponseEntity<Usuario> newUsuario(@RequestBody Usuario newUsuario) {
        return ResponseEntity.ok().body(usuarioRepository.save(newUsuario));
    }

    // Single item

    @GetMapping("/usuarios/{nombreUsuario}")
    ResponseEntity<Usuario> one(@PathVariable String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario).map(usuario -> ResponseEntity.ok().body(usuario))
                .orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/usuarios/{nombreUsuario}")
    ResponseEntity<Usuario> update(@PathVariable String nombreUsuario, @RequestBody Usuario newUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario).map(usuario -> {

            System.out.println("PATCH request received for /usuarios/" + nombreUsuario);

            if (newUsuario.getNombreUsuario() != null) {
                usuario.setNombreUsuario(newUsuario.getNombreUsuario());
            }
            if (newUsuario.getContraseña() != null) {
                usuario.setContraseña(newUsuario.getContraseña());
            }
            if (newUsuario.getNombreCompleto() != null) {
                usuario.setNombreCompleto(newUsuario.getNombreCompleto());
            }
            if (newUsuario.getEmail() != null) {
                usuario.setEmail(newUsuario.getEmail());
            }
            if (newUsuario.getTelefono() != null) {
                usuario.setTelefono(newUsuario.getTelefono());
            }
            if (newUsuario.getCiudad() != null) {
                usuario.setCiudad(newUsuario.getCiudad());
            }
            if (newUsuario.getRol() != null) {
                usuario.setRol(newUsuario.getRol());
            }
            if (newUsuario.getDescripcion() != null) {
                usuario.setDescripcion(newUsuario.getDescripcion());
            }
            if (newUsuario.getFoto() != null) {
                usuario.setFoto(newUsuario.getFoto());
            }
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().body(usuario);
        }).orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/usuarios/{nombreUsuario}")
    ResponseEntity<Usuario> updatePartial(@PathVariable String nombreUsuario, @RequestBody Usuario newUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario).map(usuario -> {

            System.out.println("PATCH request received for /usuarios/" + nombreUsuario);

            if (newUsuario.getNombreUsuario() != null) {
                usuario.setNombreUsuario(newUsuario.getNombreUsuario());
            }
            if (newUsuario.getContraseña() != null) {
                usuario.setContraseña(newUsuario.getContraseña());
            }
            if (newUsuario.getNombreCompleto() != null) {
                usuario.setNombreCompleto(newUsuario.getNombreCompleto());
            }
            if (newUsuario.getEmail() != null) {
                usuario.setEmail(newUsuario.getEmail());
            }
            if (newUsuario.getTelefono() != null) {
                usuario.setTelefono(newUsuario.getTelefono());
            }
            if (newUsuario.getCiudad() != null) {
                usuario.setCiudad(newUsuario.getCiudad());
            }
            if (newUsuario.getRol() != null) {
                usuario.setRol(newUsuario.getRol());
            }
            if (newUsuario.getDescripcion() != null) {
                usuario.setDescripcion(newUsuario.getDescripcion());
            }
            if (newUsuario.getFoto() != null) {
                usuario.setFoto(newUsuario.getFoto());
            }
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().body(usuario);
        }).orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/usuarios/{nombreUsuario}")
    @Transactional
    ResponseEntity<Usuario> delete(@PathVariable String nombreUsuario) {
        usuarioRepository.deleteByNombreUsuario(nombreUsuario);
        return ResponseEntity.ok().body(null);
    }

}

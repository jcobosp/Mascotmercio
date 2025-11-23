package es.upm.dit.isst.mascotmercioapiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.upm.dit.isst.mascotmercioapiback.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    List<Usuario> findByNombreCompleto(String nombreCompleto);

    void deleteByNombreUsuario(String nombreUsuario);

    Usuario findByEmail(String email);
}
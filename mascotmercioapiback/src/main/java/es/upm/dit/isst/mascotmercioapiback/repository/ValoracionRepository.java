package es.upm.dit.isst.mascotmercioapiback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.upm.dit.isst.mascotmercioapiback.model.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {

    List<Valoracion> findByEstablecimiento(Long establecimiento); 

    List<Valoracion> findByPuntuacion(Double puntuacion);
}

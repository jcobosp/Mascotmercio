package es.upm.dit.isst.mascotmercioapiback.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.upm.dit.isst.mascotmercioapiback.model.Establecimiento;
import es.upm.dit.isst.mascotmercioapiback.model.Reservas;
import es.upm.dit.isst.mascotmercioapiback.model.Usuario;

public interface ReservasRepository extends JpaRepository<Reservas, Long> {

    List<Reservas> findAll();

    List<Reservas> findByFecha(String fecha);

    List<Reservas> findByHora(String hora);

    List<Reservas> findByNombreCliente(String nombreCliente);

    List<Reservas> findByTelefono(Integer telefono);

    List<Reservas> findByAsistentes(Integer asistentes);

    List<Reservas> findByMascota(String mascota);

    List<Reservas> findByIdEstablecimiento(Long idEstablecimiento);

    List<Reservas> findByNombreUsuario(String nombreUsuario);
}

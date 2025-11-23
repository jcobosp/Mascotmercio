package es.upm.dit.isst.mascotmercioapiback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.upm.dit.isst.mascotmercioapiback.model.Establecimiento;

public interface EstablecimientoRepository extends JpaRepository<Establecimiento, Long> {

    List<Establecimiento> findAll();

    List<Establecimiento> findByNombre(String nombre);

    List<Establecimiento> findByPais(String pais);

    List<Establecimiento> findByDireccion(String direccion);

    List<Establecimiento> findByCodigoPostal(String codigoPostal);

    List<Establecimiento> findByCiudad(String ciudad);

    List<Establecimiento> findByProvincia(String provincia);

    List<Establecimiento> findByServiciosOfrecidos(List<String> serviciosOfrecios);

    List<Establecimiento> findByMascotasAceptadas(List<String> mascotasAceptadas);

    List<Establecimiento> findByUsuario(String usuario);
}
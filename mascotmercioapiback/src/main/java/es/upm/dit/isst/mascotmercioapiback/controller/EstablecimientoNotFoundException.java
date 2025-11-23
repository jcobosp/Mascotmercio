package es.upm.dit.isst.mascotmercioapiback.controller;

public class EstablecimientoNotFoundException extends RuntimeException {
  EstablecimientoNotFoundException(Long id) {
    super("Could not find establecimiento " + id);
  }
}
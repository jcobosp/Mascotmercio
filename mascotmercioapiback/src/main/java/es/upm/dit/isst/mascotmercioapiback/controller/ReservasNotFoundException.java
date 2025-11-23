package es.upm.dit.isst.mascotmercioapiback.controller;

public class ReservasNotFoundException extends RuntimeException {

    public ReservasNotFoundException(Long id) {
      super("Could not find reservas " + id);
    }
  }

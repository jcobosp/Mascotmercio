package es.upm.dit.isst.mascotmercioapiback.controller;

public class UsuarioNotFoundException extends RuntimeException {

  public UsuarioNotFoundException(String nombreUsuario) {
    super("Could not find user " + nombreUsuario);
  }
}
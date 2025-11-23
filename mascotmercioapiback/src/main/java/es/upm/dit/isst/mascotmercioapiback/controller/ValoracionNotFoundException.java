package es.upm.dit.isst.mascotmercioapiback.controller;

public class ValoracionNotFoundException extends RuntimeException {
    ValoracionNotFoundException(Long id) {
        super("Could not find valoracion " + id);
    }
}

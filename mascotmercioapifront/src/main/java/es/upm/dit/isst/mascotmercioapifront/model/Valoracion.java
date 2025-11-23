package es.upm.dit.isst.mascotmercioapifront.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.PositiveOrZero;

public class Valoracion {
    private Long id;
    @PositiveOrZero
    @DecimalMax("5.0")
    private Double puntuacion;
    private String titulo;
    private String comentario;
    private String usuario;
    private Long establecimiento;

    // #region Constructores
    public Valoracion() {
    }

    public Valoracion(@PositiveOrZero @DecimalMax("5.0") Double puntuacion, String titulo, String comentario,
            String usuario, Long establecimiento) {
        this.puntuacion = puntuacion;
        this.titulo = titulo;
        this.comentario = comentario;
        this.usuario = usuario;
        this.establecimiento = establecimiento;
    }

    // #region Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Long establecimiento) {
        this.establecimiento = establecimiento;
    }

    // #region HashCode
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((puntuacion == null) ? 0 : puntuacion.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        result = prime * result + ((establecimiento == null) ? 0 : establecimiento.hashCode());
        return result;
    }

    // #region Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Valoracion other = (Valoracion) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (puntuacion == null) {
            if (other.puntuacion != null)
                return false;
        } else if (!puntuacion.equals(other.puntuacion))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (comentario == null) {
            if (other.comentario != null)
                return false;
        } else if (!comentario.equals(other.comentario))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        if (establecimiento == null) {
            if (other.establecimiento != null)
                return false;
        } else if (!establecimiento.equals(other.establecimiento))
            return false;
        return true;
    }

    // #region toString
    @Override
    public String toString() {
        return "Valoracion [id=" + id + ", puntuacion=" + puntuacion + ", titulo=" + titulo + ", comentario="
                + comentario + ", usuario=" + usuario + ", establecimiento=" + establecimiento + "]";
    }

}
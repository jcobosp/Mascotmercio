package es.upm.dit.isst.mascotmercioapiback.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;

// #region Entity
@Entity
public class Usuario {
    @Id
    private String nombreUsuario;
    private String contraseña;
    private String nombreCompleto;
    @Email
    private String email;
    private Integer telefono;
    // private String tipoMascota;
    private String ciudad;
    private String rol;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    // @JsonIgnore
    @Lob
    private byte[] foto;

    // #region Constructores
    public Usuario() {
    }

    public Usuario(String nombreUsuario, String contraseña, String nombreCompleto, @Email String email,
            Integer telefono, String ciudad, String rol, String descripcion, byte[] foto) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.rol = rol;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    // #region Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    // #region HashCode
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
        result = prime * result + ((contraseña == null) ? 0 : contraseña.hashCode());
        result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
        result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
        result = prime * result + ((rol == null) ? 0 : rol.hashCode());
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + Arrays.hashCode(foto);
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
        Usuario other = (Usuario) obj;
        if (nombreUsuario == null) {
            if (other.nombreUsuario != null)
                return false;
        } else if (!nombreUsuario.equals(other.nombreUsuario))
            return false;
        if (contraseña == null) {
            if (other.contraseña != null)
                return false;
        } else if (!contraseña.equals(other.contraseña))
            return false;
        if (nombreCompleto == null) {
            if (other.nombreCompleto != null)
                return false;
        } else if (!nombreCompleto.equals(other.nombreCompleto))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (telefono == null) {
            if (other.telefono != null)
                return false;
        } else if (!telefono.equals(other.telefono))
            return false;
        if (ciudad == null) {
            if (other.ciudad != null)
                return false;
        } else if (!ciudad.equals(other.ciudad))
            return false;
        if (rol == null) {
            if (other.rol != null)
                return false;
        } else if (!rol.equals(other.rol))
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (!Arrays.equals(foto, other.foto))
            return false;
        return true;
    }

    // #region toString
    @Override
    public String toString() {
        return "Usuario [nombreUsuario=" + nombreUsuario + ", contraseña=" + contraseña + ", nombreCompleto="
                + nombreCompleto + ", email=" + email + ", telefono=" + telefono + ", ciudad=" + ciudad + ", rol=" + rol
                + ", descripcion=" + descripcion + ", foto=" + Arrays.toString(foto) + "]";
    }

}

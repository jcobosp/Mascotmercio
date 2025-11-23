package es.upm.dit.isst.mascotmercioapifront.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;

public class Establecimiento {
    private Long id;
    private String nombre;
    @Lob
    private byte[] foto;
    @Email
    private String email;
    private String pais;
    private Integer telefono;
    private String direccion;
    private String codigoPostal;
    private String ciudad;
    private String provincia;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private List<String> serviciosOfrecidos;
    private List<String> mascotasAceptadas;
    private String usuario;
    private double estrellas;

    // #region Constructores
    public Establecimiento() {
    }

    public Establecimiento(String nombre, byte[] foto, @Email String email, String pais, Integer telefono,
            String direccion, String codigoPostal, String ciudad, String provincia, String descripcion,
            List<String> serviciosOfrecidos, List<String> mascotasAceptadas, String usuario, double estrellas) {
        this.nombre = nombre;
        this.foto = foto;
        this.email = email;
        this.pais = pais;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.descripcion = descripcion;
        this.serviciosOfrecidos = serviciosOfrecidos;
        this.mascotasAceptadas = mascotasAceptadas;
        this.usuario = usuario;
        this.estrellas = estrellas;
    }

    // #region Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getServiciosOfrecidos() {
        return serviciosOfrecidos;
    }

    public void setServiciosOfrecidos(List<String> serviciosOfrecidos) {
        this.serviciosOfrecidos = serviciosOfrecidos;
    }

    public List<String> getMascotasAceptadas() {
        return mascotasAceptadas;
    }

    public void setMascotasAceptadas(List<String> mascotasAceptadas) {
        this.mascotasAceptadas = mascotasAceptadas;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public double getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(double estrellas) {
        this.estrellas = estrellas;
    }

    // #region HashCode

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + Arrays.hashCode(foto);
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((pais == null) ? 0 : pais.hashCode());
        result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
        result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
        result = prime * result + ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
        result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
        result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((serviciosOfrecidos == null) ? 0 : serviciosOfrecidos.hashCode());
        result = prime * result + ((mascotasAceptadas == null) ? 0 : mascotasAceptadas.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        long temp;
        temp = Double.doubleToLongBits(estrellas);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Establecimiento other = (Establecimiento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (!Arrays.equals(foto, other.foto))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (pais == null) {
            if (other.pais != null)
                return false;
        } else if (!pais.equals(other.pais))
            return false;
        if (telefono == null) {
            if (other.telefono != null)
                return false;
        } else if (!telefono.equals(other.telefono))
            return false;
        if (direccion == null) {
            if (other.direccion != null)
                return false;
        } else if (!direccion.equals(other.direccion))
            return false;
        if (codigoPostal == null) {
            if (other.codigoPostal != null)
                return false;
        } else if (!codigoPostal.equals(other.codigoPostal))
            return false;
        if (ciudad == null) {
            if (other.ciudad != null)
                return false;
        } else if (!ciudad.equals(other.ciudad))
            return false;
        if (provincia == null) {
            if (other.provincia != null)
                return false;
        } else if (!provincia.equals(other.provincia))
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (serviciosOfrecidos == null) {
            if (other.serviciosOfrecidos != null)
                return false;
        } else if (!serviciosOfrecidos.equals(other.serviciosOfrecidos))
            return false;
        if (mascotasAceptadas == null) {
            if (other.mascotasAceptadas != null)
                return false;
        } else if (!mascotasAceptadas.equals(other.mascotasAceptadas))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        if (Double.doubleToLongBits(estrellas) != Double.doubleToLongBits(other.estrellas))
            return false;
        return true;
    }

    // #region toString
    @Override
    public String toString() {
        return "Establecimiento [id=" + id + ", nombre=" + nombre + ", foto=" + Arrays.toString(foto) + ", email="
                + email + ", pais=" + pais + ", telefono=" + telefono + ", direccion=" + direccion + ", codigoPostal="
                + codigoPostal + ", ciudad=" + ciudad + ", provincia=" + provincia + ", descripcion=" + descripcion
                + ", serviciosOfrecidos=" + serviciosOfrecidos + ", mascotasAceptadas=" + mascotasAceptadas
                + ", usuario=" + usuario + ", estrellas=" + estrellas + "]";
    }

}
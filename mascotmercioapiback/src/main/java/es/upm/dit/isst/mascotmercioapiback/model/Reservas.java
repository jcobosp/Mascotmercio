package es.upm.dit.isst.mascotmercioapiback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reservas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fecha;
    private String hora;
    private String nombreCliente;
    private Integer telefono;
    private Integer asistentes;
    private String mascota;
    @Column(columnDefinition = "TEXT")
    private String solicitudes;
    private String nombreUsuario;
    private Long idEstablecimiento;

    public Reservas() {

    }

    public Reservas(String fecha, String hora, String nombreCliente, Integer telefono, Integer asistentes,
            String mascota, String solicitudes, String nombreUsuario, Long idEstablecimiento) {
        this.fecha = fecha;
        this.hora = hora;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.asistentes = asistentes;
        this.mascota = mascota;
        this.solicitudes = solicitudes;
        this.nombreUsuario = nombreUsuario;
        this.idEstablecimiento = idEstablecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(Integer asistentes) {
        this.asistentes = asistentes;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public String getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(String solicitudes) {
        this.solicitudes = solicitudes;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Long idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
        result = prime * result + ((nombreCliente == null) ? 0 : nombreCliente.hashCode());
        result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
        result = prime * result + ((asistentes == null) ? 0 : asistentes.hashCode());
        result = prime * result + ((mascota == null) ? 0 : mascota.hashCode());
        result = prime * result + ((solicitudes == null) ? 0 : solicitudes.hashCode());
        result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
        result = prime * result + ((idEstablecimiento == null) ? 0 : idEstablecimiento.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reservas other = (Reservas) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        if (nombreCliente == null) {
            if (other.nombreCliente != null)
                return false;
        } else if (!nombreCliente.equals(other.nombreCliente))
            return false;
        if (telefono == null) {
            if (other.telefono != null)
                return false;
        } else if (!telefono.equals(other.telefono))
            return false;
        if (asistentes == null) {
            if (other.asistentes != null)
                return false;
        } else if (!asistentes.equals(other.asistentes))
            return false;
        if (mascota == null) {
            if (other.mascota != null)
                return false;
        } else if (!mascota.equals(other.mascota))
            return false;
        if (solicitudes == null) {
            if (other.solicitudes != null)
                return false;
        } else if (!solicitudes.equals(other.solicitudes))
            return false;
        if (nombreUsuario == null) {
            if (other.nombreUsuario != null)
                return false;
        } else if (!nombreUsuario.equals(other.nombreUsuario))
            return false;
        if (idEstablecimiento == null) {
            if (other.idEstablecimiento != null)
                return false;
        } else if (!idEstablecimiento.equals(other.idEstablecimiento))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reservas [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", nombreCliente=" + nombreCliente
                + ", telefono=" + telefono + ", asistentes=" + asistentes + ", mascota=" + mascota + ", solicitudes="
                + solicitudes + ", nombreUsuario=" + nombreUsuario + ", idEstablecimiento=" + idEstablecimiento + "]";
    }

}

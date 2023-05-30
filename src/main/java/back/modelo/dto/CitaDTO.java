package back.modelo.dto;

import java.util.Date;

public class CitaDTO {

    private long id;
    private Date fecha;
    private String hora;
    private long empleadoId;  // Identificador del empleado

    public CitaDTO() {
    }

    public CitaDTO(long id, Date fecha, String hora, long empleadoId) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.empleadoId = empleadoId;
    }

    // Getters y setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(long empleadoId) {
        this.empleadoId = empleadoId;
    }
}

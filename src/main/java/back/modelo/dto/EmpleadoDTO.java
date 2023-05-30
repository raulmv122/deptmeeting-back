package back.modelo.dto;

import java.util.List;

public class EmpleadoDTO {

    private long id;
    private String nombre;
    private String apellido;
    private String password;
    private String email;
    private String rol;
    private List<Long> citas;

    public EmpleadoDTO() {
    }

    public EmpleadoDTO(long id, String nombre, String apellido, String password, String email, String rol, List<Long> citas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.citas = citas;
    }

    // Getters y setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Long> getCitas() {
        return citas;
    }

    public void setCitas(List<Long> citas) {
        this.citas = citas;
    }
}

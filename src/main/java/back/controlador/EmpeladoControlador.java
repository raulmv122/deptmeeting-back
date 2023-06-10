package back.controlador;

import back.excepciones.ResourceNotFoundException;
import back.modelo.Empleado;
import back.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpeladoControlador {

    @Autowired
    private EmpleadoRepositorio repositorio;

    @GetMapping("/empleados")
    public List<Empleado> listarTodosLosEmpleados(){
        return repositorio.findAll();
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable  Long id){
        Empleado empleado = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el id: " + id));
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        Empleado empleadoActual = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el id: " + id));

        empleadoActual.setNombre(empleado.getNombre());
        empleadoActual.setApellido(empleado.getApellido());
        empleadoActual.setEmail(empleado.getEmail());
        empleadoActual.setRol(empleado.getRol());
        empleadoActual.setLoggeado(empleado.isLoggeado());

        Empleado empleadoActualizado = repositorio.save(empleadoActual);
        return ResponseEntity.ok(empleadoActualizado);
    }


    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Empleado> borrarEmpleado(@PathVariable Long id){
        Empleado empleado = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el id: " + id));
        repositorio.delete(empleado);
        return ResponseEntity.ok(empleado);
    }

    @PostMapping("/empleados")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado){
        Empleado empleadoNuevo = repositorio.save(empleado);
        return ResponseEntity.ok(empleadoNuevo);
    }


}

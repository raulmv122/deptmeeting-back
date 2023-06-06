package back.controlador;

import back.modelo.Cita;
import back.modelo.Empleado;
import back.repositorio.CitaRepositorio;
import back.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class CitaControlador {

    @Autowired
    private CitaRepositorio citaRepository;

    @Autowired
    private EmpleadoRepositorio empleadoRepository;

    @PostMapping("/citas/{empleadoId}")
    public ResponseEntity<?> crearCitaParaEmpleado(
            @PathVariable("empleadoId") String empleadoId, @RequestBody Cita cita) {
        try {
            Long empleadoIdCasteado = Long.parseLong(empleadoId);

            Optional<Empleado> empleadoOptional = empleadoRepository.findById(empleadoIdCasteado);
            if (empleadoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El empleado no existe.");
            }

            List<Cita> citasDelEmpleado = citaRepository.findByEmpleado(empleadoOptional.get());
            for (Cita citaExistente : citasDelEmpleado) {
                if (citaExistente.getFecha().equals(cita.getFecha()) &&
                        citaExistente.getHora().equals(cita.getHora())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("El empleado ya tiene una cita programada en esa fecha y hora.");
                }
            }

            cita.setEmpleado(empleadoOptional.get());
            Cita savedCita = citaRepository.save(cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCita);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID del empleado no es válido.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear la cita.");
        }
    }





    @GetMapping("/citas")
    public ResponseEntity<?> obtenerTodasLasCitas() {
        try {
            List<Cita> citas = citaRepository.findAll();
            if (citas == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se encontraron citas.");
            }

            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al obtener las citas.");
        }
    }


    @PutMapping("/citas/{id}")
    public ResponseEntity<?> actualizarCita(@PathVariable("id") String id, @RequestBody Cita cita) {
        try {
            Long idCasteado = Long.parseLong(id);
            Optional<Cita> citaOptional = citaRepository.findById(idCasteado);
            if (citaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cita no existe.");
            }
            Cita citaExistente = citaOptional.get();

            List<Cita> citasDelEmpleado = citaRepository.findByEmpleado(citaExistente.getEmpleado());
            for (Cita citaExistenteEmpleado : citasDelEmpleado) {
                if (citaExistente.getFecha().equals(cita.getFecha()) &&
                        citaExistente.getHora().equals(cita.getHora())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("El empleado ya tiene una cita programada en esa fecha y hora.");
                }
            }

            citaExistente.setFecha(cita.getFecha());
            citaExistente.setHora(cita.getHora());
            Cita citaActualizada = citaRepository.save(citaExistente);
            return ResponseEntity.ok(citaActualizada);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID de la cita no es válido.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al actualizar la cita.");
        }
    }



    @GetMapping("/citas/{id}")
    public ResponseEntity<?> obtenerCitaPorId(@PathVariable("id") String id) {
        try {
            Long idCasteado = Long.parseLong(id);
            Optional<Cita> citaOptional = citaRepository.findById(idCasteado);
            if (citaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cita no existe.");
            }
            Cita cita = citaOptional.get();
            return ResponseEntity.ok(cita);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID de la cita no es válido.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al obtener la cita.");
        }
    }

    @DeleteMapping("/citas/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable("id") String id) {
        try {
            Long idCasteado = Long.parseLong(id);
            Optional<Cita> citaOptional = citaRepository.findById(idCasteado);
            if (citaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cita no existe.");
            }
            citaRepository.deleteById(idCasteado);
            return ResponseEntity.ok("Cita eliminada.");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID de la cita no es válido.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al eliminar la cita.");
        }
    }



}

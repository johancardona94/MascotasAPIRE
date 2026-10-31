package com.ejemplo.mascotas.controller;

import com.ejemplo.mascotas.model.Mascota;
import com.ejemplo.mascotas.repository.MascotaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final MascotaRepository mascotaRepository;

    public MascotaController(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @PostMapping
    public ResponseEntity<Mascota> crear(@Valid @RequestBody Mascota mascota) {
        mascota.setId(null);
        Mascota guardada = mascotaRepository.save(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    @GetMapping
    public ResponseEntity<List<Mascota>> listarTodas() {
        return ResponseEntity.ok(mascotaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return mascotaRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "No se encontró una mascota con id " + id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody Mascota datos) {
        return mascotaRepository.findById(id)
                .<ResponseEntity<?>>map(actual -> {
                    actual.setNombre(datos.getNombre());
                    actual.setEspecie(datos.getEspecie());
                    actual.setEdad(datos.getEdad());
                    actual.setPropietario(datos.getPropietario());
                    Mascota actualizada = mascotaRepository.save(actual);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "No se encontró una mascota con id " + id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!mascotaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        mascotaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/especie")
    public ResponseEntity<List<Mascota>> buscarPorEspecie(@RequestParam String especie) {
        return ResponseEntity.ok(mascotaRepository.findByEspecieIgnoreCase(especie));
    }
}

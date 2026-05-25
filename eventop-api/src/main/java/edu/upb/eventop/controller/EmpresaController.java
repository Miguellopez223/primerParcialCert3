package edu.upb.eventop.controller;

import edu.upb.eventop.repository.dto.request.EmpresaRequestDto;
import edu.upb.eventop.repository.dto.response.EmpresaDto;
import edu.upb.eventop.repository.projection.EmpresaNombreProjection;
import edu.upb.eventop.repository.projection.EmpresaResumenRecord;
import edu.upb.eventop.service.EmpresaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/api/v1/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;


    @GetMapping()
    public ResponseEntity<List<EmpresaDto>> empresas() {
        try {
            return ResponseEntity.ok(empresaService.listar());
        }catch (Exception e) {
            log.error("Error al listar empresas", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody EmpresaRequestDto empresa) {
        try {
            this.empresaService.save(empresa);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Error al guardar empresa", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable("id")String empresaId,
                                        @RequestBody EmpresaRequestDto empresa) {
        try {
            this.empresaService.update(empresaId, empresa);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Error al guardar empresa", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ================================================================
    // TAREA 18/05 — Proyecciones con Spring Data JPA
    // ================================================================

    // GET /api/v1/empresas/proyecciones/interface
    @GetMapping("/proyecciones/interface")
    public ResponseEntity<List<EmpresaNombreProjection>> proyeccionInterface() {
        try {
            return ResponseEntity.ok(empresaService.listarProyeccionInterface());
        } catch (Exception e) {
            log.error("Error al listar proyección interfaz de empresas", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET /api/v1/empresas/proyecciones/record
    @GetMapping("/proyecciones/record")
    public ResponseEntity<List<EmpresaResumenRecord>> proyeccionRecord() {
        try {
            return ResponseEntity.ok(empresaService.listarProyeccionRecord());
        } catch (Exception e) {
            log.error("Error al listar proyección record de empresas", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

package edu.upb.eventop.controller;

import edu.upb.eventop.repository.dto.response.EventoResponseDto;
import edu.upb.eventop.repository.entity.Eventos;
import edu.upb.eventop.repository.projection.EventoResumenProjection;
import edu.upb.eventop.repository.projection.EventoResumenRecord;
import edu.upb.eventop.service.EventosService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/api/v1/eventos")
public class EventoController {
    private final EventosService eventosService;

    @GetMapping()
    public ResponseEntity<List<EventoResponseDto>> eventos() {
        try {
            return ResponseEntity.ok(eventosService.eventos());
        }catch (Exception e) {
            log.error("Error al listar eventos", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{empresaId}")
    public ResponseEntity<Void> guardar(
            @PathVariable("empresaId") String empresaId,
            @RequestBody Eventos eventos) {
        try {
            this.eventosService.guardar(empresaId, eventos);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Error al listar eventos", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ================================================================
    // TAREA 18/05 — Proyecciones con Spring Data JPA
    // ================================================================

    // GET /api/v1/eventos/proyecciones/interface
    @GetMapping("/proyecciones/interface")
    public ResponseEntity<List<EventoResumenProjection>> proyeccionInterface() {
        try {
            return ResponseEntity.ok(eventosService.listarProyeccionInterface());
        } catch (Exception e) {
            log.error("Error al listar proyección interfaz de eventos", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET /api/v1/eventos/proyecciones/record
    @GetMapping("/proyecciones/record")
    public ResponseEntity<List<EventoResumenRecord>> proyeccionRecord() {
        try {
            return ResponseEntity.ok(eventosService.listarProyeccionRecord());
        } catch (Exception e) {
            log.error("Error al listar proyección record de eventos", e);
            return ResponseEntity.internalServerError().build();
        }
    }

}

package edu.upb.eventop.service;

import edu.upb.eventop.repository.EventosRepository;
import edu.upb.eventop.repository.dto.response.EventoResponseDto;
import edu.upb.eventop.repository.entity.Empresa;
import edu.upb.eventop.repository.entity.Eventos;
import edu.upb.eventop.repository.projection.EventoResumenProjection;
import edu.upb.eventop.repository.projection.EventoResumenRecord;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EventosService {
    private final EventosRepository eventosRepository;
    private final EmpresaService empresaService;

    @Transactional(readOnly = true)
    public List<EventoResponseDto> eventos() {
        return eventosRepository.listarEventos3();
    }

    @Transactional
    public void guardar(String empresaId, Eventos eventos) {
        Optional<Empresa> optionalEmpresa = this.empresaService.findByID(empresaId);
        Empresa empresa = null;
        if(optionalEmpresa.isPresent()) {
            empresa = optionalEmpresa.get();
        }
        eventos.setEmpresa(empresa);
        this.eventosRepository.save(eventos);
    }

    // ================================================================
    // TAREA 18/05 — Proyecciones con Spring Data JPA
    // ================================================================

    @Transactional(readOnly = true)
    public List<EventoResponseDto> listarEventosDeEmpresa1() {
        // Llama al @Query con INNER JOIN FETCH filtrado por "Empresa 1"
        return this.eventosRepository.listarEventos();
    }

    @Transactional(readOnly = true)
    public List<EventoResumenProjection> listarProyeccionInterface() {
        return this.eventosRepository.findAllProjectedBy();
    }

    @Transactional(readOnly = true)
    public List<EventoResumenRecord> listarProyeccionRecord() {
        return this.eventosRepository.listarResumenRecord();
    }

}

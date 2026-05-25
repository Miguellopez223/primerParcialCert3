package edu.upb.eventop.repository.projection;

// ================================================================
// TAREA 18/05 — Proyecciones con Spring Data JPA
// ================================================================
/**
 * PROYECCIÓN BASADA EN INTERFAZ CON PROYECCIÓN ANIDADA — Eventos
 *
 * Demuestra dos características:
 *  1. Proyección plana: getId(), getNombre() traen columnas de la tabla eventos.
 *  2. Proyección anidada (nested projection): getEmpresa() devuelve otra interfaz
 *     (EmpresaInfo) que apunta a la relación @ManyToOne. Spring Data hace el JOIN
 *     automáticamente y trae solo el campo nombre de la tabla empresas.
 *
 * El SQL generado es aproximadamente:
 *   SELECT e.id, e.nombre, ee.nombre
 *   FROM eventos e
 *   INNER JOIN empresas ee ON e.empresa_id = ee.id
 */
public interface EventoResumenProjection {

    String getId();
    String getNombre();

    // Proyección anidada: el nombre del metodo debe coincidir
    // con el nombre del atributo en la entidad Eventos (private Empresa empresa)
    EmpresaInfo getEmpresa();

    interface EmpresaInfo {
        String getNombre();
    }
}

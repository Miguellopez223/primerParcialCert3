package edu.upb.eventop.repository.projection;

// ================================================================
// TAREA 18/05 — Proyecciones con Spring Data JPA
// ================================================================
/**
 * PROYECCIÓN BASADA EN INTERFAZ — Empresa
 *
 * Spring Data JPA lee los nombres de los métodos (getNombre, getId) y genera
 * automáticamente un SELECT solo con esas columnas:
 *   SELECT id, nombre FROM empresas
 *
 * No se trae descripcion ni ningún otro campo. Útil cuando la tabla tiene
 * muchas columnas y solo necesitamos unas pocas.
 */
public interface EmpresaNombreProjection {
    String getId();
    String getNombre();
}

package edu.upb.eventop.repository.projection;

// ================================================================
// TAREA 18/05 — Proyecciones con Spring Data JPA
// ================================================================
/**
 * PROYECCIÓN BASADA EN RECORD — Empresa
 *
 * Un record de Java (Java 16+) es una clase inmutable que declara sus campos
 * en el encabezado. El compilador genera automáticamente:
 *   - Constructor con todos los parámetros
 *   - Métodos de acceso (id(), nombre())
 *   - equals(), hashCode() y toString()
 *
 * Para usarlo como proyección se debe escribir una @Query JPQL con la expresión
 * de constructor "new NombreCompleto(campos...)". Spring Data invoca ese
 * constructor al mapear los resultados.
 *
 * El SQL resultante selecciona únicamente las columnas indicadas:
 *   SELECT e.id, e.nombre FROM empresas e
 */
public record EmpresaResumenRecord(String id, String nombre) {
}

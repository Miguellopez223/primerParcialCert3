package edu.upb.eventop.repository.projection;

// ================================================================
// TAREA 18/05 — Proyecciones con Spring Data JPA
// ================================================================
/**
 * PROYECCIÓN BASADA EN RECORD CON JOIN — Eventos
 *
 * Demuestra cómo usar un record para "aplanar" datos de dos tablas en un
 * solo objeto inmutable. A diferencia de la proyección anidada de interfaz,
 * aquí el nombreEmpresa queda como campo directo (no hay objeto anidado).
 *
 * Se usa junto a una @Query JPQL con expresión de constructor:
 *   SELECT new ...EventoResumenRecord(e.id, e.nombre, ee.nombre)
 *   FROM Eventos e INNER JOIN e.empresa ee
 *
 * Ventaja del record frente a una clase DTO clásica: no necesita escribir
 * getters, setters, equals/hashCode ni toString manualmente.
 */
public record EventoResumenRecord(String id, String nombre, String nombreEmpresa) {
}

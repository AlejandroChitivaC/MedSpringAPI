package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

/**
 * A DTO for the {@link Medico} entity
 */
public record DatosActualizarMed(@NotNull long id, String nombre, String documento, DatosDireccion direccion) {
}
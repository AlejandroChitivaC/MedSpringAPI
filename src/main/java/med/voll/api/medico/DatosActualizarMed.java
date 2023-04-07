package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.DatosDireccion;
import med.voll.api.direccion.Direccion;

import java.io.Serializable;

/**
 * A DTO for the {@link Medico} entity
 */
public record DatosActualizarMed(@NotNull long id, String nombre, String documento, DatosDireccion direccion) {
}
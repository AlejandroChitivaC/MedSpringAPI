package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.Especialidad;

public record DatosRegistroMedico(
        @NotBlank(message = "{nombre.obligatorio}") String nombre,
        @NotBlank(message = "{email.obligatorio}") @Email String email,
        @NotBlank(message = "{telefono.obligatorio}") String telefono,
        @NotBlank @Pattern(regexp = "\\d{8,10}") String documento,
        @NotNull(message = "{especialidad.obligatoria}") Especialidad especialidad,
        @NotNull(message = "{direccion.obligatoria}") @Valid DatosDireccion direccion) {

}
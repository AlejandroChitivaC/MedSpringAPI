package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRtaMedico(Long id, String nombre, String email, String telefono, String documento,Especialidad especialidad, DatosDireccion direccion) {
}

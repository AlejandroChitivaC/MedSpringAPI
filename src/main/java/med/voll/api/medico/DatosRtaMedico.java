package med.voll.api.medico;

import med.voll.api.direccion.DatosDireccion;

public record DatosRtaMedico(Long id, String nombre, String email, String telefono, String documento,Especialidad especialidad, DatosDireccion direccion) {
}

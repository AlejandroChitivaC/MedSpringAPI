package med.voll.api.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.DatosDireccion;
import med.voll.api.direccion.Direccion;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Table(name = "Medico")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private boolean activo;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegMed) {
        this.nombre = datosRegMed.nombre();
        this.email = datosRegMed.email();
        this.documento = datosRegMed.documento();
        this.telefono = datosRegMed.telefono();
        this.especialidad = datosRegMed.especialidad();
        this.direccion = new Direccion(datosRegMed.direccion());
        this.activo = true;
    }

    public void updateData( DatosActualizarMed datosActMed) {
        if (datosActMed.nombre() != null) {
            this.nombre = datosActMed.nombre();
        }
        if (datosActMed.documento() != null) {
            this.documento = datosActMed.documento();
        }
        if (datosActMed.direccion() != null) {
            this.direccion = direccion.actualizarDireccion(datosActMed.direccion());
        }
    }

    public void desactivarMed() {
        this.activo = false;
    }
}

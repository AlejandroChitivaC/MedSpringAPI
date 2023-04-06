package med.voll.api.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import med.voll.api.medico.DatosRegistroMedico;
import org.springframework.web.bind.annotation.*;

@RestController
@JsonDeserialize
@RequestMapping("/medicos")
public class MedicoController {
    @PostMapping

    public void registrarMedico(@RequestBody DatosRegistroMedico datosRegMed) {
        System.out.println("Datos Recibidos: "+datosRegMed);
    }
//EL String datosregmed es lo que le debe llegar al api
    // El @RequestBody es para que el api sepa que el String param
    // es un json o el body que se envia desde el cliente
}

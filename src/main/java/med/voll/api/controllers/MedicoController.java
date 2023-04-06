package med.voll.api.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@JsonDeserialize
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepo medicoRepo;
    @PostMapping

    public void registrarMedico(@RequestBody DatosRegistroMedico datosRegMed) {
        System.out.println("Datos Recibidos: "+datosRegMed);
        medicoRepo.save(new Medico(datosRegMed));


    }
//EL String datosregmed es lo que le debe llegar al api
    // El @RequestBody es para que el api sepa que el String param
    // es un json o el body que se envia desde el cliente
}

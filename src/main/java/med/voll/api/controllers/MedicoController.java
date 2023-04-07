package med.voll.api.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@JsonDeserialize
//Ruta para acceder a los metodos de esta clase
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepo medicoRepo;

    //EL String datosRegMed es lo que le debe llegar al api
    // El @RequestBody es para que el api sepa que el String param
    // es un json o el body que se envia desde el cliente
    //Guarda en la base de datos
    @PostMapping
    public void registrarMedico(@RequestBody DatosRegistroMedico datosRegMed) {
        System.out.println("Datos Recibidos: " + datosRegMed);
        medicoRepo.save(new Medico(datosRegMed));
    }

    @GetMapping

    //Paginacion por medio de Spring se manda una Page y se mapea por el Pageable
    //Con el PageableDefault se le puede limitar la cantidad de datos que se muestran
    public Page<DatosListadoMed> listarMedicos(@PageableDefault(size = 2) Pageable pag) {
        System.out.println("Datos Recibidos: " + medicoRepo.findAll());
//        return medicoRepo.findAll(pag).map(DatosListadoMed::new);
        return medicoRepo.findByActivoTrue(pag).map(DatosListadoMed::new);
    }


    //Como no llamamos al repository usamos el @Transactional
    @Transactional
    @PutMapping
    public void ActualizarMedico(@RequestBody @Valid DatosActualizarMed datosActMed) {
        Medico med = medicoRepo.getReferenceById(datosActMed.id());
        med.updateData(datosActMed);
    }
    //Delete Lógico
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteMed(@PathVariable Long id) {
        Medico med = medicoRepo.getReferenceById(id);
        med.desactivarMed();
    }
}



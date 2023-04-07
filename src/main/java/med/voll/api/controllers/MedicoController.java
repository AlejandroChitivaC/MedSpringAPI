package med.voll.api.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@JsonDeserialize
@Validated
//Ruta para acceder a los metodos de esta clase
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepo medicoRepo;

    //EL String datosRegMed es lo que le debe llegar al api
    // El @RequestBody es para que la API sepa que el String datosRegMed
    // es un json o el body que se envia desde el cliente
    //Guarda en la base de datos
    @PostMapping
    public ResponseEntity<DatosRtaMedico> registrarMedico(@RequestBody DatosRegistroMedico datosRegMed, UriComponentsBuilder uriBuilder) {
        System.out.println("Datos Recibidos: " + datosRegMed);
        Medico med = medicoRepo.save(new Medico(datosRegMed));
        DatosRtaMedico datosRtaMedico = new DatosRtaMedico(med.getId(), med.getNombre(), med.getEmail(), med.getTelefono(), med.getDocumento(), med.getEspecialidad(), new DatosDireccion(med.getDireccion().getCalle(), med.getDireccion().getCiudad(), med.getDireccion().getDistrito(), med.getDireccion().getNumero(), med.getDireccion().getComplemento()));
        //Se retorna un ResponseEntity para que el cliente sepa que se registro correctamente
        URI url = uriBuilder.path("/medicos/{id}").buildAndExpand(med.getId()).toUri();
        //finalmente se retorna el ResponseEntity con los datos del medico en JSON
        return ResponseEntity.created(url).body(datosRtaMedico);
    }

    @GetMapping
    //Paginacion por medio de Spring se manda una Page y se mapea por el Pageable
    //Con el PageableDefault se le puede limitar la cantidad de datos que se muestran
    public ResponseEntity<Page<DatosListadoMed>> listarMedicos(@PageableDefault(size = 3) Pageable pag) {
        System.out.println("Datos Recibidos: " + medicoRepo.findAll());
//        return medicoRepo.findAll(pag).map(DatosListadoMed::new);
        return ResponseEntity.ok(medicoRepo.findByActivoTrue(pag).map(DatosListadoMed::new));
    }


    //Como no llamamos al repository usamos el @Transactional
    @Transactional
    @PutMapping
    public ResponseEntity<DatosRtaMedico> ActualizarMedico(@RequestBody @Valid DatosActualizarMed datosActMed) {
        Medico med = medicoRepo.getReferenceById(datosActMed.id());
        med.updateData(datosActMed);
        //Se retorna un ResponseEntity para que el cliente sepa que se actualizo correctamente
        return ResponseEntity.ok(new DatosRtaMedico(med.getId(), med.getNombre(), med.getEmail(), med.getTelefono(), med.getDocumento(), med.getEspecialidad(), new DatosDireccion(med.getDireccion().getCalle(), med.getDireccion().getCiudad(), med.getDireccion().getDistrito(), med.getDireccion().getNumero(), med.getDireccion().getComplemento())));
    }

    //Delete Lógico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMed(@PathVariable Long id) {
        Medico med = medicoRepo.getReferenceById(id);
        med.desactivarMed();
        return ResponseEntity.noContent().build();
    }
    //Obtener datos de un medico en especifico
    @GetMapping("/{id}")
    public ResponseEntity<DatosRtaMedico> retornaDatosMed(@PathVariable Long id) {
        Medico med = medicoRepo.getReferenceById(id);
        var datosMed=new DatosRtaMedico(med.getId(), med.getNombre(), med.getEmail(), med.getTelefono(), med.getDocumento(), med.getEspecialidad(), new DatosDireccion(med.getDireccion().getCalle(), med.getDireccion().getCiudad(), med.getDireccion().getDistrito(), med.getDireccion().getNumero(), med.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMed);


    }

}



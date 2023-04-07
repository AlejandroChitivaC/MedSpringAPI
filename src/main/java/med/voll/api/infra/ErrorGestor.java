package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Extendemos al controller como un escuchador
@RestControllerAdvice
public class ErrorGestor {
    //Con la anotación @ExceptionHandler podemos capturar excepciones y devolver una respuesta personalizada
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handle400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler({org.springframework.dao.DataIntegrityViolationException.class})
    public ResponseEntity handleBadRequest() {
        return ResponseEntity.badRequest().build();
    }


    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(org.springframework.validation.FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}

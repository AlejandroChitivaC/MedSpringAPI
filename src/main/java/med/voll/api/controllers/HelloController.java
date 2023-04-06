package med.voll.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//API Endpoint hello
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String hello() {
        return "Quiubo sapo perro funcione";
    }

}

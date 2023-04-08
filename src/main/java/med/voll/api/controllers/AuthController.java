package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.domain.users.DatosAuth;
import med.voll.api.domain.users.User;
import med.voll.api.infra.security.DatosJWTToken;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authUser(@RequestBody @Valid DatosAuth datosAuth) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAuth.login(), datosAuth.clave());
        var usuarioAutenticado=authManager.authenticate(authToken);
        var tokenJwt = tokenService.generarToken((User) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(tokenJwt));
    }

}

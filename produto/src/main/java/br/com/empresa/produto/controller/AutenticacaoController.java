package br.com.empresa.produto.controller;


import br.com.empresa.produto.domain.usuario.Usuario;
import br.com.empresa.produto.dto.usuario.LoginDto;
import br.com.empresa.produto.dto.usuario.RegistrarDto;
import br.com.empresa.produto.infra.security.TokenService;
import br.com.empresa.produto.service.AutenticacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AutenticacaoService service;


    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> efetuarLogin(@RequestBody @Valid LoginDto dto){

            var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
            var authentication = manager.authenticate(authenticationToken);
            return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));

    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody @Valid RegistrarDto dto) {
        service.register(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}

package br.com.empresa.produto.service;


import br.com.empresa.produto.domain.usuario.Usuario;
import br.com.empresa.produto.dto.usuario.RegistrarDto;
import br.com.empresa.produto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);

    }


    public ResponseEntity<String> register(RegistrarDto dto){
        if(this.repository.findByLogin(dto.login()) != null){

            return ResponseEntity.ok().build();

        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
        Usuario usuario = new Usuario(dto.login(), encryptedPassword, dto.role());

        repository.save(usuario);

        return ResponseEntity.ok().build();
    }
}

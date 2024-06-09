package br.com.empresa.produto.infra.security;


import br.com.empresa.produto.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("$(api.token.secret)")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return  JWT.create()
                    .withIssuer("Empresa Api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
         throw new RuntimeException("Erro ao gerar token jwt",exception);
        }
    }


    public Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return  JWT.require(algoritmo)
                    // specify any specific claim validations
                    .withIssuer("Empresa Api")
                    // reusable verifier instance
                    .build().
                    verify(tokenJWT).
                    getSubject();

        } catch (JWTVerificationException exception){
           throw new RuntimeException("Token JWT expirado ou inválido " + exception);
        }
    }
}

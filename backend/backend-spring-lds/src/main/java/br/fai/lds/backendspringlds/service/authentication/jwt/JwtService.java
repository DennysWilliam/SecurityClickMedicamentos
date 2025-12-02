package br.fai.lds.backendspringlds.service.authentication.jwt;

import br.fai.lds.domain.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

  // A chave secreta deve ser forte e armazenada de forma segura (ex: application.properties)
  // Por enquanto, usaremos a mesma do projeto de referência para manter a compatibilidade.
  private final String secret = "XUFAE3FQG1RLBlgQ93fDSUlj4HfbKi4a1kFl1gDloOg=";

  public String getEmailFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);

  }

  public Date getExpirationDateFromToken(String token) {

    return getClaimFromToken(token, Claims::getExpiration);

  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }


  private Claims getAllClaimsFromToken(String token) {
    // Note: O Jwts.parserBuilder().setSigningKey(secret).build() é a forma moderna
    // O projeto de referência usava uma versão mais antiga, mas vamos manter a compatibilidade
    // com a forma como a chave é usada.
    return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody();
  }

  private boolean tokenExpired(String token) {
    final Date expirationDate = getExpirationDateFromToken(token);
    return expirationDate.before(new Date());
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String email = getEmailFromToken(token);
    return (email.equals(userDetails.getUsername()) && !tokenExpired(token)
    );
  }

  public String generateToken(UserDetails userDetails, String fullname, UserModel.Tipo tipo, String email) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("email", email);
    claims.put("fullname", fullname);
    claims.put("role", tipo.name()); // Envia o enum como texto
    return createToken(claims, userDetails.getUsername());
  }


  private String createToken(Map<String, Object> claims, String subject) {

    return Jwts.builder()
      .setClaims(claims)
      .setSubject(subject)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(
        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10) // 10 horas de validade
      )
      .signWith(SignatureAlgorithm.HS256, secret.getBytes())
      .compact();
  }

}

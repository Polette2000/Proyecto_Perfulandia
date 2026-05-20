package cl.duoc.TokenPerfulandia.service;



import cl.duoc.TokenPerfulandia.configuration.JwtProperties;
import cl.duoc.TokenPerfulandia.dto.response.UsuarioValidacionResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }
public String generarToken(UsuarioValidacionResponseDto usuario) {

    Date fechaActual = new Date();

    Date fechaExpiracion = new Date(
            fechaActual.getTime() + jwtProperties.getExpiration()
    );

    Key key = Keys.hmacShaKeyFor(
            jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8)
    );

    return Jwts.builder()
            .setSubject(usuario.getEmail())
            .claim("idUsuario", usuario.getIdUsuario())
            .claim("nombre", usuario.getNombre())
            .claim("rol", usuario.getRol())
            .setIssuedAt(fechaActual)
            .setExpiration(fechaExpiracion)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
}
}
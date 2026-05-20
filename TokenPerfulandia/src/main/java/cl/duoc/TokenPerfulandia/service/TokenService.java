package cl.duoc.TokenPerfulandia.service;



import cl.duoc.TokenPerfulandia.dto.request.AuthRequestDto;
import cl.duoc.TokenPerfulandia.dto.response.AuthResponseDto;
import cl.duoc.TokenPerfulandia.dto.response.UsuarioValidacionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TokenService {

    private final WebClient webClient;
    private final JwtService jwtService;

    @Value("${usuario.service.url}")
    private String usuarioServiceUrl;

    public TokenService(WebClient webClient, JwtService jwtService) {
        this.webClient = webClient;
        this.jwtService = jwtService;
    }

    public AuthResponseDto generarToken(AuthRequestDto request) {

        UsuarioValidacionResponseDto usuario = webClient.post()
                .uri(usuarioServiceUrl + "/api/v1/usuarios/validar-login")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(UsuarioValidacionResponseDto.class)
                .block();

        if (usuario == null || !usuario.getValido()) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        String token = jwtService.generarToken(usuario);

        return new AuthResponseDto(
                token,
                usuario.getEmail(),
                usuario.getRol());
    }
}
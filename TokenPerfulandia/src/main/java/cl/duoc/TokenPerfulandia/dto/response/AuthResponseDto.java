package cl.duoc.TokenPerfulandia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AuthResponseDto {
    private String token;
    private String username;
    private String rol;



}

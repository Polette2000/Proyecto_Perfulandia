package cl.duoc.SucursalesPerfulandia.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class ComunaRequestDTO {
    
    @NotBlank(message = "El nombre de la comuna es obligatorio")
    private String nombreComuna;

    @NotBlank(message = "La región es obligatoria")
    private String region;

}

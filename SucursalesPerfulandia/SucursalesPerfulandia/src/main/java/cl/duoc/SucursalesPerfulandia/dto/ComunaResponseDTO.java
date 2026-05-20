package cl.duoc.SucursalesPerfulandia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ComunaResponseDTO {

    private Integer idComuna;
    private String nombreComuna;
    private String region;

}

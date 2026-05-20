package cl.duoc.SucursalesPerfulandia.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SucursalResponseDTO {
    private Integer idSucursal;
    private String nombreSucursal;
    private String direccion;
    private String telefono;
    private String horarioAtencion;
    private Boolean activa;

    private Integer idComuna;
    private String nombreComuna;
    private String region;

}

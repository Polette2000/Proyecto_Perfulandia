package cl.duoc.SucursalesPerfulandia.dto;

import jakarta.validation.constraints.NotBlank;


import jakarta.validation.constraints.NotNull;
import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor

public class SucursalRequestDTO {
        @NotBlank(message = "El nombre de la sucursal es obligatorio")
    private String nombreSucursal;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    @NotBlank(message = "El horario de atención es obligatorio")
    private String horarioAtencion;

    @NotNull(message = "Debe indicar si la sucursal está activa")
    private Boolean activa;

    @NotNull(message = "El ID de la comuna es obligatorio")
    private Integer idComuna;
}



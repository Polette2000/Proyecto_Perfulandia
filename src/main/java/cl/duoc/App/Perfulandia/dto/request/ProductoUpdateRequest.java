package cl.duoc.App.Perfulandia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoUpdateRequest {
    
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String marca;
    private Long idCategoria;

}

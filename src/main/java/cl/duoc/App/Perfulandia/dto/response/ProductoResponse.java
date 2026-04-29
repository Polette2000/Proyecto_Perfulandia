package cl.duoc.App.Perfulandia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //permite crear objetos de una forma más ordenada y legible, sin usar un constructor largo.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoResponse {
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String marca;
    private Boolean activo;

    private Long idCategoria;
    private String nombreCategoria;

}

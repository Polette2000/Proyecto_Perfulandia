package cl.duoc.App.Perfulandia.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //permite crear objetos de una forma más ordenada y legible, sin usar un constructor largo.
public class CategoriaResponse {

    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private Boolean activo;

}

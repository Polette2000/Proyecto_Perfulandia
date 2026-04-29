package cl.duoc.App.Perfulandia.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaUpdateRequest {

    private String nombre;
    private String descripcion;

}

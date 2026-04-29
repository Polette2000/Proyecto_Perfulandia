package cl.duoc.App.Perfulandia.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.duoc.App.Perfulandia.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //Lista solo productos activos.
    List<Producto> findByActivoTrue();

    //Busca productos por nombre, sin importar mayúsculas o minúsculas
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCategoriaIdCategoria(Long idCategoria);

    List<Producto> findByMarcaContainingIgnoreCase(String marca);
}

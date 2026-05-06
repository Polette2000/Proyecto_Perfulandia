package cl.duoc.App.Perfulandia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.App.Perfulandia.model.Categoria;
import cl.duoc.App.Perfulandia.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
     // Repositorio que permite acceder a la tabla productos en la base de datos.
    private final CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public List<Categoria> listarCategoriasActivas() {
        return categoriaRepository.findByActivoTrue();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    public Categoria crearCategoria(Categoria categoria) {
        validarDatos(categoria);

        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }

        categoria.setActivo(true);

        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
        Categoria categoria = buscarPorId(id);

        validarDatos(categoriaActualizada);

        if (!categoria.getNombre().equalsIgnoreCase(categoriaActualizada.getNombre())
                && categoriaRepository.existsByNombre(categoriaActualizada.getNombre())) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }

        categoria.setNombre(categoriaActualizada.getNombre());
        categoria.setDescripcion(categoriaActualizada.getDescripcion());

        return categoriaRepository.save(categoria);
    }

    public void eliminarCategoria(Long id) {
        Categoria categoria = buscarPorId(id);
        categoria.setActivo(false);
        categoriaRepository.save(categoria);
    }

    private void validarDatos(Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            throw new RuntimeException("El nombre de la categoría es obligatorio");
        }

        if (categoria.getDescripcion() == null || categoria.getDescripcion().isBlank()) {
            throw new RuntimeException("La descripción de la categoría es obligatoria");
        }
    }
}
package cl.duoc.App.Perfulandia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.App.Perfulandia.dto.request.CategoriaCreateRequest;
import cl.duoc.App.Perfulandia.dto.request.CategoriaUpdateRequest;
import cl.duoc.App.Perfulandia.dto.response.CategoriaResponse;
import cl.duoc.App.Perfulandia.model.Categoria;
import cl.duoc.App.Perfulandia.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
     private final CategoriaRepository categoriaRepository;

    public List<CategoriaResponse> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<CategoriaResponse> listarCategoriasActivas() {
        return categoriaRepository.findByActivoTrue()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public CategoriaResponse buscarPorId(Long id) {
        Categoria categoria = obtenerCategoriaPorId(id);
        return convertirAResponse(categoria);
    }

    public CategoriaResponse crearCategoria(CategoriaCreateRequest request) {
        validarDatosCreate(request);

        if (categoriaRepository.existsByNombre(request.getNombre())) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }

        Categoria categoria = Categoria.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .activo(true)
                .build();

        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        return convertirAResponse(categoriaGuardada);
    }

    public CategoriaResponse actualizarCategoria(Long id, CategoriaUpdateRequest request) {
        Categoria categoria = obtenerCategoriaPorId(id);

        validarDatosUpdate(request);

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        Categoria categoriaActualizada = categoriaRepository.save(categoria);

        return convertirAResponse(categoriaActualizada);
    }

    public void eliminarCategoria(Long id) {
        Categoria categoria = obtenerCategoriaPorId(id);
        categoria.setActivo(false);
        categoriaRepository.save(categoria);
    }

    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    private CategoriaResponse convertirAResponse(Categoria categoria) {
        return CategoriaResponse.builder()
                .idCategoria(categoria.getIdCategoria())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .activo(categoria.getActivo())
                .build();
    }

    private void validarDatosCreate(CategoriaCreateRequest request) {
        if (request.getNombre() == null || request.getNombre().isBlank()) {
            throw new RuntimeException("El nombre de la categoría es obligatorio");
        }

        if (request.getDescripcion() == null || request.getDescripcion().isBlank()) {
            throw new RuntimeException("La descripción de la categoría es obligatoria");
        }
    }

    private void validarDatosUpdate(CategoriaUpdateRequest request) {
        if (request.getNombre() == null || request.getNombre().isBlank()) {
            throw new RuntimeException("El nombre de la categoría es obligatorio");
        }

        if (request.getDescripcion() == null || request.getDescripcion().isBlank()) {
            throw new RuntimeException("La descripción de la categoría es obligatoria");
        }
    }
}

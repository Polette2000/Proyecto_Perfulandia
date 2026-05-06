package cl.duoc.App.Perfulandia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.App.Perfulandia.dto.request.ProductoCreateRequest;
import cl.duoc.App.Perfulandia.dto.request.ProductoUpdateRequest;
import cl.duoc.App.Perfulandia.dto.response.ProductoResponse;
import cl.duoc.App.Perfulandia.model.Categoria;
import cl.duoc.App.Perfulandia.model.Producto;
import cl.duoc.App.Perfulandia.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaService categoriaService;

    public List<ProductoResponse> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<ProductoResponse> listarProductosActivos() {
        return productoRepository.findByActivoTrue()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public ProductoResponse buscarPorId(Long id) {
        Producto producto = obtenerProductoPorId(id);
        return convertirAResponse(producto);
    }

    public ProductoResponse guardarProducto(ProductoCreateRequest request) {

        validarDatosCreate(request);

        Categoria categoria = categoriaService.buscarPorId(request.getIdCategoria());

        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .marca(request.getMarca())
                .categoria(categoria)
                .activo(true)
                .build();

        Producto productoGuardado = productoRepository.save(producto);

        return convertirAResponse(productoGuardado);
    }

    public ProductoResponse actualizarProducto(Long id, ProductoUpdateRequest request) {

        Producto producto = obtenerProductoPorId(id);

        validarDatosUpdate(request);

        Categoria categoria = categoriaService.buscarPorId(request.getIdCategoria());

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setMarca(request.getMarca());
        producto.setCategoria(categoria);

        Producto productoActualizado = productoRepository.save(producto);

        return convertirAResponse(productoActualizado);
    }

    public void eliminarProducto(Long id) {
        Producto producto = obtenerProductoPorId(id);
        producto.setActivo(false);
        productoRepository.save(producto);
    }

    public List<ProductoResponse> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<ProductoResponse> buscarPorCategoria(Long idCategoria) {
        return productoRepository.findByCategoriaIdCategoria(idCategoria)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<ProductoResponse> buscarPorMarca(String marca) {
        return productoRepository.findByMarcaContainingIgnoreCase(marca)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    private ProductoResponse convertirAResponse(Producto producto) {
        return ProductoResponse.builder()
                .idProducto(producto.getIdProducto())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .marca(producto.getMarca())
                .activo(producto.getActivo())
                .idCategoria(producto.getCategoria().getIdCategoria())
                .nombreCategoria(producto.getCategoria().getNombre())
                .build();
    }

    private void validarDatosCreate(ProductoCreateRequest request) {

        if (request.getNombre() == null || request.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del producto es obligatorio");
        }

        if (request.getDescripcion() == null || request.getDescripcion().isBlank()) {
            throw new RuntimeException("La descripción del producto es obligatoria");
        }

        if (request.getPrecio() == null || request.getPrecio() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        if (request.getStock() == null || request.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }

        if (request.getMarca() == null || request.getMarca().isBlank()) {
            throw new RuntimeException("La marca del producto es obligatoria");
        }

        if (request.getIdCategoria() == null) {
            throw new RuntimeException("Debe ingresar una categoría válida");
        }
    }

    private void validarDatosUpdate(ProductoUpdateRequest request) {

        if (request.getNombre() == null || request.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del producto es obligatorio");
        }

        if (request.getDescripcion() == null || request.getDescripcion().isBlank()) {
            throw new RuntimeException("La descripción del producto es obligatoria");
        }

        if (request.getPrecio() == null || request.getPrecio() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        if (request.getStock() == null || request.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }

        if (request.getMarca() == null || request.getMarca().isBlank()) {
            throw new RuntimeException("La marca del producto es obligatoria");
        }

        if (request.getIdCategoria() == null) {
            throw new RuntimeException("Debe ingresar una categoría válida");
        }
    }
}
package cl.duoc.App.Perfulandia.controller;

import java.util.List;

import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.App.Perfulandia.dto.request.ProductoCreateRequest;
import cl.duoc.App.Perfulandia.dto.request.ProductoUpdateRequest;
import cl.duoc.App.Perfulandia.dto.response.ProductoResponse;
import cl.duoc.App.Perfulandia.exception.GlobalExceptionHandler;
import cl.duoc.App.Perfulandia.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;


    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarProductos() {
        List<ProductoResponse> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos); // 200 OK devuelve la lista de productos en el cuerpo de la respuesta
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ProductoResponse>> listarProductosActivos() {
        List<ProductoResponse> productos = productoService.listarProductosActivos();
        return ResponseEntity.ok(productos); // 200 OK Lista de productos activos en el cuerpo de la respuesta
    }

    //Si no existe, el Service lanza RuntimeException
    // Luego GlobalExceptionHandler lo transforma en 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable Long id) {
        ProductoResponse producto = productoService.buscarPorId(id);
        return ResponseEntity.ok(producto); // 200 OK Si existe.                               
    }

    /*
     * @Valid valida los datos del DTO.
     * Si los datos están mal:
     * GlobalExceptionHandler devuelve 400 Bad Request.
     * Si se guarda correctamente:
     * devuelve 201 Created.
     */
    @PostMapping
    public ResponseEntity<ProductoResponse> guardarProducto(
            @Valid @RequestBody ProductoCreateRequest request
    ) {
        ProductoResponse productoGuardado = productoService.guardarProducto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado); // 201 Created
    }

    /*
     * PUT /api/v1/productos/{id}
     * Actualiza un producto existente.
     *
     * Si el producto existe, devuelve 200 OK.
     * Si el producto no existe, GlobalExceptionHandler devuelve 404.
     * Si los datos vienen mal, @Valid genera error 400.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoUpdateRequest request
    ) {
        ProductoResponse productoActualizado = productoService.actualizarProducto(id, request);
        return ResponseEntity.ok(productoActualizado); // 200 OK
    }

    /*
     *Desactiva un producto (activo = false).
     * Si existe, devuelve 204 No Content.
     * 204 significa que la operación fue exitosa, pero no devuelve cuerpo.
     * Si no existe, GlobalExceptionHandler devuelve 404.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    /*
     * GET /api/v1/productos/buscar?nombre=floral
     * Busca productos por nombre.
     * Devuelve 200 OK.
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponse>> buscarPorNombre(@RequestParam String nombre) {
        List<ProductoResponse> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos); // 200 OK
    }

    /*
     * GET /api/v1/productos/categoria/{idCategoria}
     * Busca productos por ID de categoría.
     * Devuelve 200 OK.
     */
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoResponse>> buscarPorCategoria(@PathVariable Long idCategoria) {
        List<ProductoResponse> productos = productoService.buscarPorCategoria(idCategoria);
        return ResponseEntity.ok(productos); // 200 OK
    }

    /*
     * GET /api/v1/productos/marca?marca=Perfulandia
     * Busca productos por marca.
     * Devuelve 200 OK.
     */
    @GetMapping("/marca")
    public ResponseEntity<List<ProductoResponse>> buscarPorMarca(@RequestParam String marca) {
        List<ProductoResponse> productos = productoService.buscarPorMarca(marca);
        return ResponseEntity.ok(productos); // 200 OK
    }
}

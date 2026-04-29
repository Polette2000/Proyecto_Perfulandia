package cl.duoc.App.Perfulandia.controller;

import java.util.List;

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
import cl.duoc.App.Perfulandia.service.ProductoService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<ProductoResponse> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/activos")
    public List<ProductoResponse> listarProductosActivos() {
        return productoService.listarProductosActivos();
    }

    @GetMapping("/{id}")
    public ProductoResponse buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    @PostMapping
    public ProductoResponse guardarProducto(@RequestBody ProductoCreateRequest request) {
        return productoService.guardarProducto(request);
    }

    @PutMapping("/{id}")
    public ProductoResponse actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoUpdateRequest request
    ) {
        return productoService.actualizarProducto(id, request);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "Producto desactivado correctamente";
    }

    @GetMapping("/buscar")
    public List<ProductoResponse> buscarPorNombre(@RequestParam String nombre) {
        return productoService.buscarPorNombre(nombre);
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<ProductoResponse> buscarPorCategoria(@PathVariable Long idCategoria) {
        return productoService.buscarPorCategoria(idCategoria);
    }

    @GetMapping("/marca")
    public List<ProductoResponse> buscarPorMarca(@RequestParam String marca) {
        return productoService.buscarPorMarca(marca);
    }
}

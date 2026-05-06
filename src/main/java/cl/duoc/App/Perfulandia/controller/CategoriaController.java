package cl.duoc.App.Perfulandia.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.App.Perfulandia.model.Categoria;
import cl.duoc.App.Perfulandia.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    //Lista todas las categorías.
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias); // 200 OK
    }

    /*
     * GET /api/v1/categorias/activas
     * Lista solo categorías activas.
     */
    @GetMapping("/activas")
    public ResponseEntity<List<Categoria>> listarCategoriasActivas() {
        List<Categoria> categorias = categoriaService.listarCategoriasActivas();
        return ResponseEntity.ok(categorias); // 200 OK
    }

    /*
     * GET /api/v1/categorias/{id}
     * Busca categoría por ID.
     * Si existe, devuelve 200 OK.
     * Si no existe, el Service lanza RuntimeException.
     * GlobalExceptionHandler lo transforma en 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria); // 200 OK
    }

    /*
     * POST /api/v1/categorias
     * Crea una categoría.
     * @Valid valida los atributos de Categoria.
     * Si faltan datos, devuelve 400 Bad Request.
     * Si ya existe el nombre, devuelve 409 Conflict.
     * Si se guarda correctamente, devuelve 201 Created.
     */
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(
            @Valid @RequestBody Categoria categoria
    ) {
        Categoria categoriaCreada = categoriaService.crearCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCreada); // 201 Created
    }

    /*
     * PUT /api/v1/categorias/{id}
     * Actualiza una categoría.
     * Si existe, devuelve 200 OK.
     * Si no existe, devuelve 404 Not Found.
     * Si el nombre ya existe, devuelve 409 Conflict.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody Categoria categoria
    ) {
        Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, categoria);
        return ResponseEntity.ok(categoriaActualizada); // 200 OK
    }

    /*
     * DELETE /api/v1/categorias/{id}
     * Desactiva la categoría.
     * Si existe, devuelve 204 No Content.
     * Si no existe, devuelve 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}

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
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.App.Perfulandia.dto.request.CategoriaCreateRequest;
import cl.duoc.App.Perfulandia.dto.request.CategoriaUpdateRequest;
import cl.duoc.App.Perfulandia.dto.response.CategoriaResponse;
import cl.duoc.App.Perfulandia.service.CategoriaService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponse> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/activas")
    public List<CategoriaResponse> listarCategoriasActivas() {
        return categoriaService.listarCategoriasActivas();
    }

    @GetMapping("/{id}")
    public CategoriaResponse buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    @PostMapping
    public CategoriaResponse crearCategoria(@RequestBody CategoriaCreateRequest request) {
        return categoriaService.crearCategoria(request);
    }

    @PutMapping("/{id}")
    public CategoriaResponse actualizarCategoria(
            @PathVariable Long id,
            @RequestBody CategoriaUpdateRequest request
    ) {
        return categoriaService.actualizarCategoria(id, request);
    }

    @DeleteMapping("/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return "Categoría desactivada correctamente";
    }

}

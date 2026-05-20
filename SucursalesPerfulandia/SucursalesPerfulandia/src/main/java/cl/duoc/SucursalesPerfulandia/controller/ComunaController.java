package cl.duoc.SucursalesPerfulandia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.SucursalesPerfulandia.dto.ComunaRequestDTO;
import cl.duoc.SucursalesPerfulandia.dto.ComunaResponseDTO;
import cl.duoc.SucursalesPerfulandia.service.ComunaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comunas/v1")
public class ComunaController {
    private final ComunaService comunaService;

    public ComunaController(ComunaService comunaService) {
        this.comunaService = comunaService;
    }

    @GetMapping
    public List<ComunaResponseDTO> listarComunas() {
        return comunaService.listarComunas();
    }

    @GetMapping("/{idComuna}")
    public ComunaResponseDTO buscarComunaPorId(@PathVariable Integer idComuna) {
        return comunaService.buscarComunaPorId(idComuna);
    }

    @PostMapping
    public ComunaResponseDTO crearComuna(@Valid @RequestBody ComunaRequestDTO request) {
        return comunaService.crearComuna(request);
    }

    @PutMapping("/{idComuna}")
    public ComunaResponseDTO actualizarComuna(
            @PathVariable Integer idComuna,
            @Valid @RequestBody ComunaRequestDTO request) {
        return comunaService.actualizarComuna(idComuna, request);
    }

    @DeleteMapping("/{idComuna}")
    public String eliminarComuna(@PathVariable Integer idComuna) {
        comunaService.eliminarComuna(idComuna);
        return "Comuna eliminada correctamente";
    }
    

}

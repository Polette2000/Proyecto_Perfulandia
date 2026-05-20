package cl.duoc.SucursalesPerfulandia.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.duoc.SucursalesPerfulandia.dto.ComunaRequestDTO;
import cl.duoc.SucursalesPerfulandia.dto.ComunaResponseDTO;
import cl.duoc.SucursalesPerfulandia.exception.ResourceNotFoundException;
import cl.duoc.SucursalesPerfulandia.model.Comuna;
import cl.duoc.SucursalesPerfulandia.repository.ComunaRepository;

@Service
public class ComunaService {
      private final ComunaRepository comunaRepository;

    public ComunaService(ComunaRepository comunaRepository) {
        this.comunaRepository = comunaRepository;
    }

    public List<ComunaResponseDTO> listarComunas() {
        return comunaRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public ComunaResponseDTO buscarComunaPorId(Integer idComuna) {
        Comuna comuna = comunaRepository.findById(idComuna)
                .orElseThrow(() -> new ResourceNotFoundException("Comuna no encontrada con ID: " + idComuna));

        return convertirAResponseDTO(comuna);
    }

    public ComunaResponseDTO crearComuna(ComunaRequestDTO request) {
        Comuna comuna = new Comuna();
        comuna.setNombreComuna(request.getNombreComuna());
        comuna.setRegion(request.getRegion());

        Comuna comunaGuardada = comunaRepository.save(comuna);

        return convertirAResponseDTO(comunaGuardada);
    }

    public ComunaResponseDTO actualizarComuna(Integer idComuna, ComunaRequestDTO request) {
        Comuna comuna = comunaRepository.findById(idComuna)
                .orElseThrow(() -> new ResourceNotFoundException("Comuna no encontrada con ID: " + idComuna));

        comuna.setNombreComuna(request.getNombreComuna());
        comuna.setRegion(request.getRegion());

        Comuna comunaActualizada = comunaRepository.save(comuna);

        return convertirAResponseDTO(comunaActualizada);
    }

    public void eliminarComuna(Integer idComuna) {
        Comuna comuna = comunaRepository.findById(idComuna)
                .orElseThrow(() -> new ResourceNotFoundException("Comuna no encontrada con ID: " + idComuna));

        comunaRepository.delete(comuna);
    }

    private ComunaResponseDTO convertirAResponseDTO(Comuna comuna) {
        ComunaResponseDTO dto = new ComunaResponseDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombreComuna(comuna.getNombreComuna());
        dto.setRegion(comuna.getRegion());
        return dto;
    }


}

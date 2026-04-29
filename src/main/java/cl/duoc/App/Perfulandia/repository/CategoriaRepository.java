package cl.duoc.App.Perfulandia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.App.Perfulandia.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

     List<Categoria> findByActivoTrue();

    boolean existsByNombre(String nombre);


}

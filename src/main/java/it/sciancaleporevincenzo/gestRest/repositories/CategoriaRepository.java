package it.sciancaleporevincenzo.gestRest.repositories;

import it.sciancaleporevincenzo.gestRest.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select a from Categoria a where a.menu.id = :idMenu")
    public List<Categoria> findByIdMenu(@Param("idMenu") long idMenu);
}

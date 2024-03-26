package it.sciancaleporevincenzo.gestRest.repositories;

import it.sciancaleporevincenzo.gestRest.entity.ProdottoOriginale;
import it.sciancaleporevincenzo.gestRest.model.ProdottoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdottoOriginaleRepository extends JpaRepository<ProdottoOriginale, Long> {

    @Query("Select p from ProdottoOriginale p where p.categoria.menu.attivo = true")
    List<ProdottoModel> findAllAttivi();
}

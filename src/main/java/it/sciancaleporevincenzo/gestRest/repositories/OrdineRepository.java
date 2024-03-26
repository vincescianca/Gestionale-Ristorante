package it.sciancaleporevincenzo.gestRest.repositories;

import it.sciancaleporevincenzo.gestRest.entity.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    List<Ordine> findByIdTavolo(long idTavolo);

    Optional<Ordine> findByIdTavoloAndIdProdottoOriginale(Long idTavolo, Long idProdottoOriginale);

    @Query("Select o From Ordine o where o.dataInserimento> :data")
    List<Ordine> findLastNDays(@Param("data") Date data);
}

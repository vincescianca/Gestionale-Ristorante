package it.sciancaleporevincenzo.gestRest.repositories;

import it.sciancaleporevincenzo.gestRest.entity.Tavolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Stream;

public interface TavoloRepository extends JpaRepository<Tavolo, Long> {

    @Query("select t from Tavolo t where t.dataInserimento>:date")
    List<Tavolo> findByDataInserimento(Timestamp date);

    @Query("select t from Tavolo t where t.aperto is true")
    Stream<Tavolo> findAllAttivi();
}

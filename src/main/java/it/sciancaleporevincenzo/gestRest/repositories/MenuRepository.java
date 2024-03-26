package it.sciancaleporevincenzo.gestRest.repositories;

import it.sciancaleporevincenzo.gestRest.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu findByDescrizione(String descrizione);

    Optional<Menu> findByAttivo(boolean b);
}

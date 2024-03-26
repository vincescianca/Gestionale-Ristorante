package it.sciancaleporevincenzo.gestRest.services;

import it.sciancaleporevincenzo.gestRest.entity.ProdottoCustom;
import it.sciancaleporevincenzo.gestRest.repositories.ProdottoCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoCustomService {

    @Autowired
    ProdottoCustomRepository prodottoCustomRepository;

    public List<ProdottoCustom> findAll() {
        return prodottoCustomRepository.findAll();
    }

    public ProdottoCustom addProdottoCustom(ProdottoCustom prodotto) {
        return prodottoCustomRepository.save(prodotto);
    }
}

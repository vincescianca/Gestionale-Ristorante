package it.sciancaleporevincenzo.gestRest.services;

import it.sciancaleporevincenzo.gestRest.entity.ProdottoOriginale;
import it.sciancaleporevincenzo.gestRest.model.ProdottoModel;
import it.sciancaleporevincenzo.gestRest.repositories.ProdottoOriginaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdottoOriginaleService {

    @Autowired
    ProdottoOriginaleRepository prodottoOriginaleRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ProdottoModel> findAll() {
        return prodottoOriginaleRepository.findAll().stream().map(x -> new ProdottoModel(x)).collect(Collectors.toList()).
                stream().sorted(Comparator.comparingLong(ProdottoModel::getId)).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addProdotto(ProdottoOriginale prodotto) {
        prodotto.setDataInserimento(new Date(System.currentTimeMillis()));
        prodottoOriginaleRepository.save(prodotto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminaProdotto(long idProdotto) {
        prodottoOriginaleRepository.deleteById(idProdotto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editProdotto(ProdottoOriginale prodotto) {
        ProdottoOriginale tmp = prodottoOriginaleRepository.findById(prodotto.getId()).get();
        tmp.setIdCategoria(prodotto.getIdCategoria());
        tmp.setPrezzo(prodotto.getPrezzo());
        tmp.setNome(prodotto.getNome());
        tmp.setDescrizione(prodotto.getDescrizione());
        prodottoOriginaleRepository.save(tmp);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Optional<ProdottoOriginale> findById(long idProdotto) {
        return prodottoOriginaleRepository.findById(idProdotto);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ProdottoModel> findAllAttivi() {
        return prodottoOriginaleRepository.findAllAttivi();
    }
}

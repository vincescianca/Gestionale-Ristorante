package it.sciancaleporevincenzo.gestRest.services;

import it.sciancaleporevincenzo.gestRest.entity.Categoria;
import it.sciancaleporevincenzo.gestRest.model.CategoriaModel;
import it.sciancaleporevincenzo.gestRest.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<CategoriaModel> findAll() {
        return categoriaRepository.findAll().stream().map(x -> new CategoriaModel(x)).collect(Collectors.toList());
    }

    public List<Categoria> findByMenu(long idMenu) {
        return categoriaRepository.findByIdMenu(idMenu);
    }

    public void addCategoria(Categoria categoria) {
        categoria.setUltimaModifica(new Date(System.currentTimeMillis()));
        categoriaRepository.save(categoria);
    }

    public void eliminaCategoria(long idProdotto) {
        categoriaRepository.deleteById(idProdotto);
    }

    public void editCategoria(Categoria categoria) {
        Categoria tmp = categoriaRepository.findById(categoria.getId()).get();
        tmp.setDescrizione(categoria.getDescrizione());
        tmp.setTipologia(categoria.getTipologia());
        tmp.setIdMenu(categoria.getIdMenu());
        tmp.setNumPortataStandard(categoria.getNumPortataStandard());
        tmp.setUltimaModifica(new Date(System.currentTimeMillis()));
        categoriaRepository.save(tmp);
    }

    public Optional<Categoria> findById(long idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    public int getPortata(long idCategoria) {
        return categoriaRepository.findById(idCategoria).get().getNumPortataStandard();
    }
}

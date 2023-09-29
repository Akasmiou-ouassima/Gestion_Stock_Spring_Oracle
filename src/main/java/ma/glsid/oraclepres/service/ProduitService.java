package ma.glsid.oraclepres.service;

import ma.glsid.oraclepres.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProduitService {
    Page<Produit> findAll(Pageable pageable);

    Produit save(Produit produit);

    boolean delete(Integer id);

    Produit findById(Integer id);
}

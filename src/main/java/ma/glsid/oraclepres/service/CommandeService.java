package ma.glsid.oraclepres.service;

import ma.glsid.oraclepres.model.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommandeService {
    Page<Commande> findAll(Pageable pageable);

    Commande save(Commande commande);


    Commande update(Commande commande);

    boolean delete(Integer id);

    Commande findById(Integer id);
}

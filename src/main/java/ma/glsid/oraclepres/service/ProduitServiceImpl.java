package ma.glsid.oraclepres.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glsid.oraclepres.model.Produit;
import ma.glsid.oraclepres.repository.ProduitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;


    @Override
    public Page<Produit> findAll(Pageable pageable) {
        return produitRepository.findAll(pageable);
    }

    @Override
    public Produit save(Produit produit) {
        if (findById(produit.getId()) != null) {
            log.error("Produit already exists");
            return null;
        }
        return produitRepository.save(produit);
    }

    @Override
    public boolean delete(Integer id) {
        if (findById(id) == null) {
            log.error("Produit doesn't exist");
            return false;
        }
        produitRepository.deleteById(id);
        return true;
    }

    @Override
    public Produit findById(Integer id) {
        return produitRepository.findById(id).orElse(null);
    }
}

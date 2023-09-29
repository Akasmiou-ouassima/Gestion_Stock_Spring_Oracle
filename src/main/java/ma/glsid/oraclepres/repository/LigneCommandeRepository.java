package ma.glsid.oraclepres.repository;

import ma.glsid.oraclepres.model.LigneCommande;
import ma.glsid.oraclepres.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Integer> {
}

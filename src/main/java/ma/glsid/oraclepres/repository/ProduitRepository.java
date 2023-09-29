package ma.glsid.oraclepres.repository;

import ma.glsid.oraclepres.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
}

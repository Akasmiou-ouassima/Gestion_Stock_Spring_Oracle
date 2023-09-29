package ma.glsid.oraclepres.repository;

import ma.glsid.oraclepres.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    Optional<Commande> findByReference(String reference);
}

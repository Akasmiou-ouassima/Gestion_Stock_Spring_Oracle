package ma.glsid.oraclepres.repository;

import ma.glsid.oraclepres.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}

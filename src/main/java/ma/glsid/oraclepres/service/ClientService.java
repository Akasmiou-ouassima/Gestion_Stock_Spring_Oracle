package ma.glsid.oraclepres.service;

import ma.glsid.oraclepres.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<Client> findAll(Pageable pageable);

    Client save(Client client);

    boolean delete(Integer id);

    Client findById(Integer id);
}

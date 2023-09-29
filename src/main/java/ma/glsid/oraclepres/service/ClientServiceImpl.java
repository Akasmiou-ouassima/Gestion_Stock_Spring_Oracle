package ma.glsid.oraclepres.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glsid.oraclepres.model.Client;
import ma.glsid.oraclepres.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;


    @Override
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client save(Client client) {
        if (findById(client.getId()) != null) {
            log.error("Client already exists");
            return null;
        }
        return clientRepository.save(client);
    }

    @Override
    public boolean delete(Integer id) {
        if (findById(id) == null) {
            log.error("Client doesn't exist");
            return false;
        }
        clientRepository.deleteById(id);
        return true;
    }

    @Override
    public Client findById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }
}

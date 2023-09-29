package ma.glsid.oraclepres.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glsid.oraclepres.model.Commande;
import ma.glsid.oraclepres.model.LigneCommande;
import ma.glsid.oraclepres.model.Produit;
import ma.glsid.oraclepres.repository.CommandeRepository;
import ma.glsid.oraclepres.repository.LigneCommandeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
@Slf4j
@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;

    private final LigneCommandeRepository ligneCommandeRepository;

    private final ClientService clientService;

    private final ProduitService produitService;


    @Override
    public Page<Commande> findAll(Pageable pageable) {
        return commandeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Commande save(Commande commande) {

        Commande createCommand = Commande.builder()
                .client(clientService.findById(commande.getClient().getId()))
                .etat(commande.getEtat())
                .reference(commande.getReference())
                .build();

        Commande createdCommande = commandeRepository.save(createCommand);

        AtomicReference<Double> montant = new AtomicReference<>((double) 0);

        List<LigneCommande> ligneCommandes = commande.getLigneCommandes().stream().map(
                ligneCommande -> {
                    ligneCommande.setCommande(createdCommande);
                    Produit currentProduit = produitService.findById(ligneCommande.getProduit().getId());
                    montant.updateAndGet(v -> (v + currentProduit.getPrixUnitaire() * ligneCommande.getQuantite()));
                    return LigneCommande.builder()
                            .commande(createdCommande)
                            .produit(currentProduit)
                            .quantite(ligneCommande.getQuantite())
                            .build();
                }
        ).toList();

        createdCommande.setMontant(montant.get());

        createdCommande.setLigneCommandes(ligneCommandes);

        ligneCommandeRepository.saveAll(ligneCommandes);

        return createdCommande;
    }

    @Override
    public Commande update(Commande commande) {

        log.warn("commande: " + commande);

        Optional<Commande> toBeUpdated = commandeRepository.findByReference(commande.getReference());


        if (toBeUpdated.isEmpty()) {
            log.error("Commande doesn't exist");
            return null;
        }
        Commande commandeToUpdate = toBeUpdated.get();


        if (commande.getEtat() != null)
            commandeToUpdate.setEtat(commande.getEtat());
        if (commande.getReference() != null)
            commandeToUpdate.setReference(commande.getReference());

        return commandeRepository.save(commandeToUpdate);
    }

    @Override
    public boolean delete(Integer id) {
        if (findById(id) == null) {
            log.error("Commande doesn't exist");
            return false;
        }
        commandeRepository.deleteById(id);
        return true;
    }

    @Override
    public Commande findById(Integer id) {
        return commandeRepository.findById(id).orElse(null);
    }
}

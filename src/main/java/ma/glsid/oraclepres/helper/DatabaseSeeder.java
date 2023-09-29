package ma.glsid.oraclepres.helper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glsid.oraclepres.model.*;
import ma.glsid.oraclepres.repository.ClientRepository;
import ma.glsid.oraclepres.repository.CommandeRepository;
import ma.glsid.oraclepres.repository.LigneCommandeRepository;
import ma.glsid.oraclepres.repository.ProduitRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Slf4j
@AllArgsConstructor
@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final ClientRepository clientRepository;

    private final ProduitRepository produitRepository;

    private final CommandeRepository commandeRepository;

    private final LigneCommandeRepository ligneCommandeRepository;

    private final Faker faker = new Faker();

    @Override
    public void run(String... args) throws Exception {
        List<Client> clients = IntStream.rangeClosed(1, 100).mapToObj(
                i -> Client.builder()
                        .nom(faker.name().firstName())
                        .prenom(faker.name().lastName())
                        .adresse(faker.address().fullAddress())
                        .telephone(faker.phoneNumber().phoneNumber())
                        .email(faker.internet().emailAddress())
                        .ville(Ville.values()[faker.number().numberBetween(0, Ville.values().length - 1)])
                        .build()
        ).toList();


        List<Produit> produits = IntStream.rangeClosed(1, 10).mapToObj(
                i -> Produit.builder()
                        .designation(faker.commerce().productName())
                        .description(faker.lorem().sentence())
                        .prixUnitaire(Double.parseDouble(faker.commerce().price()) * 10)
                        .quantiteEnStock(faker.number().numberBetween(1, 100))
                        .build()
        ).toList();


        List<Commande> commandes = IntStream.rangeClosed(1, 100).mapToObj(
                i -> Commande.builder()
                        .reference(faker.code().ean8())
                        .dateCommande(faker.date().birthday())
                        .etat(Etat.values()[faker.number().numberBetween(0, Etat.values().length)])
                        .client(clients.get(faker.number().numberBetween(0, clients.size() - 1)))
                        .build()
        ).toList();


        List<LigneCommande> lignes = new ArrayList<>();

        commandes.forEach(commande -> {
            List<LigneCommande> ligneCommandes = IntStream.rangeClosed(1, faker.number().numberBetween(1, 5)).mapToObj(
                    i -> LigneCommande.builder()
                            .produit(produits.get(faker.number().numberBetween(0, produits.size() - 1)))
                            .commande(commande)
                            .quantite(faker.number().numberBetween(1, 10))
                            .build()
            ).toList();
            double montant = ligneCommandes.stream().mapToDouble(ligneCommande -> ligneCommande.getProduit().getPrixUnitaire() * ligneCommande.getQuantite()).sum();

            commande.setMontant(montant);
            commande.setLigneCommandes(ligneCommandes);
            lignes.addAll(ligneCommandes);
        });


        log.info("Saving {} clients", clients.size());
        clientRepository.saveAll(clients);
        log.info("Done saving clients");

        log.info("Saving {} produits", produits.size());
        produitRepository.saveAll(produits);
        log.info("Done saving produits");

        log.info("Saving {} commandes", commandes.size());
        commandeRepository.saveAll(commandes);
        log.info("Done saving commandes");

        log.info("Saving {} ligneCommandes", lignes.size());
        ligneCommandeRepository.saveAll(lignes);
        log.info("Done saving ligneCommandes");

    }
}

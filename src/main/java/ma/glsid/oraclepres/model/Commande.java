package ma.glsid.oraclepres.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String reference;
    private Date dateCommande;

    private double montant;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.REMOVE)
    private List<LigneCommande> ligneCommandes = new ArrayList<>();

}

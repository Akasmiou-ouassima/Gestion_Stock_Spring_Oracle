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
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String designation, description;
    private double prixUnitaire;
    private int quantiteEnStock;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.REMOVE)
    private List<LigneCommande> ligneCommandes = new ArrayList<>();
}

package ma.glsid.oraclepres.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @ManyToOne
    @JoinColumn(name = "produit_id")
    Produit produit;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    Commande commande;

    private int quantite;


}
//
//@Embeddable
//class LigneCommandeId implements Serializable {
//
//    @Column(name = "produit_id")
//    private int produitId;
//    @Column(name = "commande_id")
//    private int commandeId;
//
//    public int hashCode() {
//        return (int) (produitId + commandeId);
//    }
//
//    public boolean equals(Object object) {
//        if (object instanceof LigneCommandeId otherId) {
//            return (otherId.produitId == this.produitId)
//                    && (otherId.commandeId == this.commandeId);
//        }
//        return false;
//    }
//}
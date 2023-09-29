package ma.glsid.oraclepres.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom, prenom, adresse, telephone, email;

    @Enumerated(EnumType.STRING)
    private Ville ville;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    List<Commande> commandes;

}

package ma.glsid.oraclepres.dto;

import lombok.Builder;
import ma.glsid.oraclepres.model.Etat;
import ma.glsid.oraclepres.model.LigneCommande;

import java.util.List;

@Builder
public record CommandeRequestDto(
        int clientId,
        Etat etat,
        double montant,

        String reference,
        List<LigneDeCommandeRequestDto> ligneCommandes
) {
}

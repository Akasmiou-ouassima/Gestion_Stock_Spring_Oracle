package ma.glsid.oraclepres.dto;

import lombok.Builder;
import ma.glsid.oraclepres.model.Etat;
import ma.glsid.oraclepres.model.LigneCommande;

import java.util.Date;
import java.util.List;

@Builder
public record CommandeResponseDto(
        int id,
        ClientResponseDto client,
        Etat etat,
        double montant,
        Date dateCommande,

        String reference,
        List<LigneDeCommandeResponseDto> ligneCommandes
) {
}

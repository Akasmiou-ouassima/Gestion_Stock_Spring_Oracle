package ma.glsid.oraclepres.dto;

import lombok.Builder;
import ma.glsid.oraclepres.model.Ville;

@Builder
public record ClientRequestDto(
        String nom,
        String prenom,
        String adresse,
        String telephone,
        String email,
        Ville ville
) {
}

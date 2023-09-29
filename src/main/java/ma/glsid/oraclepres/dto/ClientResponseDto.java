package ma.glsid.oraclepres.dto;

import lombok.Builder;
import ma.glsid.oraclepres.model.Ville;

@Builder
public record ClientResponseDto(
        int id,
        String nom,
        String prenom,
        String adresse,
        String telephone,
        String email,
        Ville ville
) {
}

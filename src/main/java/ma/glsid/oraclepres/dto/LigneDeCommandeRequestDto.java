package ma.glsid.oraclepres.dto;

import lombok.Builder;

@Builder
public record LigneDeCommandeRequestDto(
        int quantite,
        int produitId
) {
}

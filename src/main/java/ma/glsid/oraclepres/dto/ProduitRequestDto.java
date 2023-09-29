package ma.glsid.oraclepres.dto;

import lombok.Builder;

@Builder
public record ProduitRequestDto(
        String designation,
        String description,
        double prix,
        int quantite
) {
}

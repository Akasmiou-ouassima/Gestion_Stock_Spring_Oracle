package ma.glsid.oraclepres.dto;

import lombok.Builder;

@Builder
public record ProduitResponseDto(
        int id,
        String designation,
        String description,
        double prix,
        int quantite
) {
}

package ma.glsid.oraclepres.dto;

import lombok.Builder;

@Builder
public record LigneDeCommandeResponseDto(
        int id,
        int quantite,
        ProduitResponseDto produit
) {
}

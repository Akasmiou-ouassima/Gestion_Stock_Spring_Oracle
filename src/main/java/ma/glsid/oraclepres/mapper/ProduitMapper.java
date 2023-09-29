package ma.glsid.oraclepres.mapper;

import ma.glsid.oraclepres.dto.ProduitRequestDto;
import ma.glsid.oraclepres.dto.ProduitResponseDto;
import ma.glsid.oraclepres.model.Produit;

public class ProduitMapper {

    public static ProduitResponseDto toProduitResponseDto(Produit produit) {
        return ProduitResponseDto.builder()
                .id(produit.getId())
                .designation(produit.getDesignation())
                .description(produit.getDescription())
                .prix(produit.getPrixUnitaire())
                .quantite(produit.getQuantiteEnStock())
                .build();

    }

    public static Produit toProduit(ProduitRequestDto produitRequestDto) {
        return Produit.builder()
                .designation(produitRequestDto.designation())
                .description(produitRequestDto.description())
                .prixUnitaire(produitRequestDto.prix())
                .quantiteEnStock(produitRequestDto.quantite())
                .build();
    }


}

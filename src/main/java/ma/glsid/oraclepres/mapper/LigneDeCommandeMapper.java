package ma.glsid.oraclepres.mapper;

import ma.glsid.oraclepres.dto.LigneDeCommandeRequestDto;
import ma.glsid.oraclepres.dto.LigneDeCommandeResponseDto;
import ma.glsid.oraclepres.model.LigneCommande;
import ma.glsid.oraclepres.model.Produit;

public class LigneDeCommandeMapper {
    public static LigneCommande toLigneCommande(LigneDeCommandeRequestDto ligneDeCommandeRequestDto) {
        return LigneCommande.builder()
                .produit(
                        Produit.builder()
                                .id(ligneDeCommandeRequestDto.produitId())
                                .build()
                )
                .quantite(ligneDeCommandeRequestDto.quantite())
                .build();
    }


    public static LigneDeCommandeResponseDto toLigneDeCommandeResponseDto(LigneCommande ligneCommande) {
        return LigneDeCommandeResponseDto.builder()
                .id(ligneCommande.getId())
                .quantite(ligneCommande.getQuantite())
                .produit(ProduitMapper.toProduitResponseDto(ligneCommande.getProduit()))
                .build();
    }
}

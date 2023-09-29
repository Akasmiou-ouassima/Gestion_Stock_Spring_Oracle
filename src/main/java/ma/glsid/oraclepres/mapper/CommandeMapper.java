package ma.glsid.oraclepres.mapper;

import ma.glsid.oraclepres.dto.CommandeRequestDto;
import ma.glsid.oraclepres.dto.CommandeResponseDto;
import ma.glsid.oraclepres.model.Client;
import ma.glsid.oraclepres.model.Commande;

import java.util.ArrayList;
import java.util.Date;

public class CommandeMapper {

    public static Commande toCommande(CommandeRequestDto commandeRequestDto) {
        return Commande.builder()
                .client(Client.builder()
                        .id(commandeRequestDto.clientId())
                        .build()
                )
                .dateCommande(new Date())
                .etat(commandeRequestDto.etat())
                .montant(commandeRequestDto.montant())
                .reference(commandeRequestDto.reference())
                .ligneCommandes(
                        commandeRequestDto.ligneCommandes() != null ? commandeRequestDto.ligneCommandes()
                                .stream()
                                .map(LigneDeCommandeMapper::toLigneCommande)
                                .toList()
                                : new ArrayList<>()
                )
                .build();
    }

    public static CommandeResponseDto toCommandeResponseDto(Commande commande) {
        return CommandeResponseDto.builder()
                .id(commande.getId())
                .client(ClientMapper.toClientResponseDto(commande.getClient()))
                .etat(commande.getEtat())
                .montant(commande.getMontant())
                .dateCommande(commande.getDateCommande())
                .reference(commande.getReference())
                .ligneCommandes(
                        (commande.getLigneCommandes() != null)
                                ?
                                commande.getLigneCommandes()
                                        .stream()
                                        .map(LigneDeCommandeMapper::toLigneDeCommandeResponseDto)
                                        .toList()
                                :
                                new ArrayList<>()
                )
                .build();
    }

}

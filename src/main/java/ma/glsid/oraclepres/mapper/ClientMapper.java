package ma.glsid.oraclepres.mapper;

import ma.glsid.oraclepres.dto.ClientRequestDto;
import ma.glsid.oraclepres.dto.ClientResponseDto;
import ma.glsid.oraclepres.model.Client;

public class ClientMapper {

    public static ClientResponseDto toClientResponseDto(Client client) {
        return ClientResponseDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .adresse(client.getAdresse())
                .telephone(client.getTelephone())
                .email(client.getEmail())
                .ville(client.getVille())
                .build();
    }

    public static Client toClient(ClientRequestDto clientRequestDto) {
        return Client.builder()
                .nom(clientRequestDto.nom())
                .prenom(clientRequestDto.prenom())
                .adresse(clientRequestDto.adresse())
                .telephone(clientRequestDto.telephone())
                .email(clientRequestDto.email())
                .ville(clientRequestDto.ville())
                .build();
    }


}

package ma.glsid.oraclepres.controller;

import lombok.AllArgsConstructor;
import ma.glsid.oraclepres.dto.CommandeRequestDto;
import ma.glsid.oraclepres.dto.CommandeResponseDto;
import ma.glsid.oraclepres.mapper.CommandeMapper;
import ma.glsid.oraclepres.model.Commande;
import ma.glsid.oraclepres.service.CommandeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commandes")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CommandeController {
    private final CommandeService commandeService;


    @PostMapping
    public ResponseEntity<CommandeResponseDto> save(@RequestBody CommandeRequestDto commandeResponseDto) {
        Commande commande = CommandeMapper.toCommande(commandeResponseDto);
        Commande savedCommande = commandeService.save(commande);
        CommandeResponseDto savedCommandeResponseDto = CommandeMapper.toCommandeResponseDto(savedCommande);
        return new ResponseEntity<>(
                savedCommandeResponseDto,
                HttpStatus.CREATED
        );
    }

    @PutMapping
    public ResponseEntity<CommandeResponseDto> update(@RequestBody CommandeRequestDto commandeRequestDto) {
        Commande commande = CommandeMapper.toCommande(commandeRequestDto);
        Commande updatedCommande = commandeService.update(commande);
        CommandeResponseDto updatedCommandeResponseDto = CommandeMapper.toCommandeResponseDto(updatedCommande);
        return new ResponseEntity<>(
                updatedCommandeResponseDto,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = commandeService.delete(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeResponseDto> findById(@PathVariable Integer id) {
        Commande commande = commandeService.findById(id);
        CommandeResponseDto commandeResponseDto = CommandeMapper.toCommandeResponseDto(commande);
        return new ResponseEntity<>(
                commandeResponseDto,
                HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<Page<CommandeResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Commande> commandes = commandeService.findAll(pageRequest);

        Page<CommandeResponseDto> commandeResponse = commandes.map(CommandeMapper::toCommandeResponseDto);

        return new ResponseEntity<>(
                commandeResponse,
                HttpStatus.OK
        );
    }
}

package ma.glsid.oraclepres.controller;

import lombok.AllArgsConstructor;
import ma.glsid.oraclepres.dto.ClientRequestDto;
import ma.glsid.oraclepres.dto.ClientResponseDto;
import ma.glsid.oraclepres.mapper.ClientMapper;
import ma.glsid.oraclepres.model.Client;
import ma.glsid.oraclepres.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {
    private final ClientService clientService;


    @PostMapping
    public ResponseEntity<ClientResponseDto> save(@RequestBody ClientRequestDto clientResponseDto) {
        Client client = ClientMapper.toClient(clientResponseDto);
        Client savedClient = clientService.save(client);
        ClientResponseDto savedClientResponseDto = ClientMapper.toClientResponseDto(savedClient);
        return new ResponseEntity<>(
                savedClientResponseDto,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = clientService.delete(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable Integer id) {
        Client client = clientService.findById(id);
        ClientResponseDto clientResponseDto = ClientMapper.toClientResponseDto(client);
        return new ResponseEntity<>(
                clientResponseDto,
                HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<Page<ClientResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Client> clients = clientService.findAll(pageRequest);

        Page<ClientResponseDto> clientResponse = clients.map(ClientMapper::toClientResponseDto);

        return new ResponseEntity<>(
                clientResponse,
                HttpStatus.OK
        );
    }
}

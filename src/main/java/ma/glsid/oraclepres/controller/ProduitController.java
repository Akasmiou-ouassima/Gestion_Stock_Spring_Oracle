package ma.glsid.oraclepres.controller;

import lombok.AllArgsConstructor;
import ma.glsid.oraclepres.dto.ProduitRequestDto;
import ma.glsid.oraclepres.dto.ProduitResponseDto;
import ma.glsid.oraclepres.mapper.ProduitMapper;
import ma.glsid.oraclepres.model.Produit;
import ma.glsid.oraclepres.service.ProduitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produits")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProduitController {
    private final ProduitService produitService;


    @PostMapping
    public ResponseEntity<ProduitResponseDto> save(@RequestBody ProduitRequestDto produitResponseDto) {
        Produit produit = ProduitMapper.toProduit(produitResponseDto);
        Produit savedProduit = produitService.save(produit);
        ProduitResponseDto savedProduitResponseDto = ProduitMapper.toProduitResponseDto(savedProduit);
        return new ResponseEntity<>(
                savedProduitResponseDto,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = produitService.delete(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitResponseDto> findById(@PathVariable Integer id) {
        Produit produit = produitService.findById(id);
        ProduitResponseDto produitResponseDto = ProduitMapper.toProduitResponseDto(produit);
        return new ResponseEntity<>(
                produitResponseDto,
                HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<Page<ProduitResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Produit> produits = produitService.findAll(pageRequest);

        Page<ProduitResponseDto> produitResponse = produits.map(ProduitMapper::toProduitResponseDto);

        return new ResponseEntity<>(
                produitResponse,
                HttpStatus.OK
        );
    }
}

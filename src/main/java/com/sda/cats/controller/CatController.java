package com.sda.cats.controller;

import com.sda.cats.model.*;
import com.sda.cats.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping
    public ResponseEntity<List<CatResponse>> getAll(Principal principal){
        System.out.println("I'm: " + principal.getName());
        return ResponseEntity.ok().body(catService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(catService.getCat(id));
    }

    @PostMapping
    public ResponseEntity<Void> createCat(@RequestBody @Valid CreateCatRequest cat) {
        URI location = catService.createCat(cat);
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable("id") Long id) {
        catService.deleteCat(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> createCat(@RequestBody @Valid List<CreateCatRequest> cats) {
        catService.createCats(cats);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatResponse> updateCat(@PathVariable("id") Long id,
                                                 @RequestBody @Valid UpdateCatRequest updateCatRequest) {
        CatResponse updatedCat = catService.updateCat(id, updateCatRequest);
        return ResponseEntity.ok().body(updatedCat);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CatResponse> patchCat(@PathVariable("id") Long id,
                                                @RequestBody @Valid PatchCatRequest patchCatRequest) {
        return ResponseEntity.ok().body(catService.patchCat(id, patchCatRequest));
    }
}

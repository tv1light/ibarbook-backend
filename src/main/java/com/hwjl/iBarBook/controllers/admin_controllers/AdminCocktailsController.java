package com.hwjl.iBarBook.controllers.admin_controllers;

import com.hwjl.iBarBook.models.cocktails.Cocktail;
import com.hwjl.iBarBook.services.CocktailService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/cocktails")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@AllArgsConstructor
class AdminCocktailsController {
    private final CocktailService cocktailService;

    @PutMapping("/{id}/edit")
    public Cocktail editCocktail(@PathVariable Long id, @RequestBody Cocktail updatedCocktail) {
        return cocktailService.updateCocktail(id, updatedCocktail);
    }

    @PostMapping("/add")
    public Cocktail addCocktail(@RequestBody Cocktail cocktail) {
        return cocktailService.save(cocktail);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteCocktail(@PathVariable Long id) {
        return cocktailService.deleteCocktail(id);
    }

    @GetMapping("all")
    public List<Cocktail> cocktails() {
        return cocktailService.findAll();
    }
}

package com.hwjl.iBarBook.controllers.admin_controllers;

import com.hwjl.iBarBook.models.ingredients.Ingredient;
import com.hwjl.iBarBook.services.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/ingredients")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@AllArgsConstructor
class AdminIngredientsController {
    private final IngredientService ingredientService;

    @PostMapping("/add")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.save(ingredient);
    }

    @PutMapping("/{id}/edit")
    public Ingredient editIngredient(@PathVariable Long id, @RequestBody Ingredient updatedIngredient) {
        return ingredientService.updateIngredient(id, updatedIngredient);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        return ingredientService.deleteIngredient(id);
    }
}

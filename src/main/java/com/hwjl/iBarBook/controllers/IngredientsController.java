package com.hwjl.iBarBook.controllers;

import com.hwjl.iBarBook.models.ingredients.Ingredient;
import com.hwjl.iBarBook.models.tags.Ingredient_tag;
import com.hwjl.iBarBook.services.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*todo:
*  1) Добавление картинки
*  2) Добавление ингредиента в хранилище пользователя
* */


@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/ingredients")
@AllArgsConstructor
public class IngredientsController {
    private final IngredientService ingredientService;


    @GetMapping("")
    public List<Ingredient> ingredients(){
        return ingredientService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Ingredient> ingredient(@PathVariable Long id){
        return ingredientService.findById(id);
    }

    @GetMapping("/{id}/tags")
    public List<Ingredient_tag> ingredientTags(@PathVariable Long id){
        return ingredientService.findByIngredientId(id);
    }

    @GetMapping("/{id}/cocktails")
    public List<Long> cocktailsWithIngredient(@PathVariable Long id){
        return ingredientService.findCocktailsWithIngredient(id);
    }

    @GetMapping("/tags")
    public List<Ingredient_tag> tags(){
        return ingredientService.findAllTags();
    }
}
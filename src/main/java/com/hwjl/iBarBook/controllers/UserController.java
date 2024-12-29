package com.hwjl.iBarBook.controllers;


import com.hwjl.iBarBook.models.ingredients.Ingredient;
import com.hwjl.iBarBook.models.roles.Role;
import com.hwjl.iBarBook.models.user.User;
import com.hwjl.iBarBook.services.IngredientService;
import com.hwjl.iBarBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/* todo: 1) Просмотр  */

@SuppressWarnings("unused")
@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    private final IngredientService ingredientService;

    @GetMapping("all")
    public List<User> users(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> user(Long id){
        return userService.findById(id);
    }

    @PutMapping("/{id}/edit")
    public User editUser(@PathVariable Long id, @RequestBody User updatedUser){
        return userService.updateUser(id, updatedUser);
    }

    @GetMapping("/{id}/ingredients")
    public List<Ingredient> userIngredients(@PathVariable("id") Long id){
        return ingredientService.findByUserId(id);
    }

    @GetMapping("/{id}/roles")
    public List<Role> userRoles(@PathVariable Long id){
        return userService.findRolesByUserId(id);
    }

    @PutMapping("/{id}/add_ingredients")
    public List<Ingredient> addIngredientsToStore(@PathVariable Long id, @RequestBody List<Long> ingredients){
        return ingredientService.findByUserId(id);
    }

}

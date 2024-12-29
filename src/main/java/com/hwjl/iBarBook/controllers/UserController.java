package com.hwjl.iBarBook.controllers;


import com.hwjl.iBarBook.models.ingredients.Ingredient;
import com.hwjl.iBarBook.models.roles.Role;
import com.hwjl.iBarBook.models.user.User;
import com.hwjl.iBarBook.services.IngredientService;
import com.hwjl.iBarBook.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @PutMapping("/add-ingredients")
    public List<Ingredient> addIngredientsToUser(
            @RequestHeader("Authorization") String jwt,
            @RequestBody AddIngredientsRequest request) {
        Long userId = userService.getUserIdFromJwt(jwt);
        return userService.addIngredientsToStore(userId, request.getIngredientIds());
    }

}


@NoArgsConstructor
@AllArgsConstructor
@Data
class AddIngredientsRequest {
    private List<Long> ingredientIds;
}
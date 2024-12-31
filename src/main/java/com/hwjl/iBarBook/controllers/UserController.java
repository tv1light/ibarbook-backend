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

    @PutMapping("/edit")
    public User editUser(@RequestHeader("Authorization") String jwt, @RequestBody User updatedUser){
        Long userId = userService.getUserIdFromJwt(jwt);
        return userService.updateUser(userId, updatedUser);
    }

    @GetMapping("/ingredients")
    public List<Ingredient> userIngredients(@RequestHeader("Authorization") String jwt){
        Long userId = userService.getUserIdFromJwt(jwt);
        return ingredientService.findByUserId(userId);
    }

    @GetMapping("/roles")
    public List<Role> userRoles(@RequestHeader("Authorization") String jwt){
        Long userId = userService.getUserIdFromJwt(jwt);
        return userService.findRolesByUserId(userId);
    }

    @PutMapping("/add-ingredients")
    public List<Ingredient> addIngredientsToUser(
            @RequestHeader("Authorization") String jwt,
            @RequestBody AddIngredientsRequest request) {
        Long userId = userService.getUserIdFromJwt(jwt);
        return userService.addIngredientsToStore(userId, request.getIngredientIds());
    }

    @DeleteMapping("/ingredient/{ingredientId}")
    public void deleteIngredient(@RequestHeader("Authorization") String jwt, @PathVariable Long ingredientId){
        Long userId = userService.getUserIdFromJwt(jwt);
        userService.deleteIngredientByUserId(ingredientId, userId);
    }


}


@NoArgsConstructor
@AllArgsConstructor
@Data
class AddIngredientsRequest {
    private List<Long> ingredientIds;
}
package com.hwjl.iBarBook.services;

import com.hwjl.iBarBook.models.composite_keys.Ingredients_store;
import com.hwjl.iBarBook.models.composite_keys.Ingredients_storeRepository;
import com.hwjl.iBarBook.models.composite_keys.User_role;
import com.hwjl.iBarBook.models.composite_keys.User_roleRepository;
import com.hwjl.iBarBook.models.ingredients.Ingredient;
import com.hwjl.iBarBook.models.ingredients.IngredientRepository;
import com.hwjl.iBarBook.models.roles.Role;
import com.hwjl.iBarBook.models.roles.RoleRepository;
import com.hwjl.iBarBook.models.user.User;
import com.hwjl.iBarBook.models.user.UserRepository;
import com.hwjl.iBarBook.security.auth.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Service
@AllArgsConstructor
public class UserService {

     private final UserRepository userRepository;
     private final RoleRepository roleRepository;
     private final User_roleRepository userRoleRepository;
     private final Ingredients_storeRepository ingredientsStoreRepository;
     private final IngredientRepository ingredientRepository;

     private PasswordEncoder passwordEncoder;


     public List<Role> findRolesByUserId(Long id){
         return roleRepository.findByUserId(id);
     }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public String registration(User user){
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         userRepository.save(user);
         User_role ur = new User_role();
         ur.setUser_id(user.getId());
         ur.setRole_id(2L);
         userRoleRepository.save(ur);
         return "Пользователь успешно создан";
    }

    public User updateUser(Long id, User updatedUser) {
         User exUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        exUser.setEmail(updatedUser.getEmail());
        exUser.setUsername(updatedUser.getUsername());
        exUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        return userRepository.save(exUser);
    }

    @Transactional
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User has been deleted";
    }

    public List<Ingredient> addIngredientsToStore(Long userId, List<Long> ingredientIds) {
        List<Ingredients_store> stores= ingredientIds.stream()
                .map(ingredientId -> {
                    Ingredients_store ing = new Ingredients_store();
                    ing.setIngredient_id(ingredientId);
                    ing.setUser_id(userId);
                    return ing;
                }).collect(Collectors.toList());
        ingredientsStoreRepository.saveAll(stores);
        return ingredientIds.stream()
                .map(ingredientRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

     public Long getUserIdFromJwt(String jwt) {
         String username = JwtProvider.getUsernameFromJwtToken(jwt);
         User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
         return user.getId();
     }

    public void deleteIngredientByUserId(Long userId, Long ingredientId) {
         int i = ingredientsStoreRepository.deleteByIngredientIdAndUserId(ingredientId, userId);
    }
}

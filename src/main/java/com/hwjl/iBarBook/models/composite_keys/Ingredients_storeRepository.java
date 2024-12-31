package com.hwjl.iBarBook.models.composite_keys;

import com.hwjl.iBarBook.models.composite_keys.CK_id.Ingredients_storeId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface Ingredients_storeRepository extends JpaRepository<Ingredients_store, Ingredients_storeId> {


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM public.ingredients_store WHERE ingredient_id = ?1 AND user_id = ?2",
            nativeQuery = true)
    int deleteByIngredientIdAndUserId(Long ingredientId, Long userId);
}
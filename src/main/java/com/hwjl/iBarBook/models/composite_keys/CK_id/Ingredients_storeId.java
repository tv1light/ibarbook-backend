package com.hwjl.iBarBook.models.composite_keys.CK_id;


import lombok.Data;

import java.io.Serializable;

@Data
public class Ingredients_storeId implements Serializable {
    private Long user_id;
    private Long ingredient_id;

}
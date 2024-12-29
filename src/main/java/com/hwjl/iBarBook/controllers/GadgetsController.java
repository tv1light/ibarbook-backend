package com.hwjl.iBarBook.controllers;

import com.hwjl.iBarBook.models.gadgets.Gadget;
import com.hwjl.iBarBook.models.tags.Gadget_tag;
import com.hwjl.iBarBook.services.GadgetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*todo:
*  1) Проверить правильность удаления гаджета
*  2) Добаваление картинки
* */

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/gadgets")
@AllArgsConstructor
public class GadgetsController {
    private final GadgetService gadgetService;

    @GetMapping("")
    public List<Gadget> gadgets(){
        return gadgetService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Gadget> gadget(@PathVariable Long id){
        return gadgetService.findById(id);
    }



    @GetMapping("/{id}/tags")
    public List<Gadget_tag> gadgetTags(@PathVariable Long id){
        return gadgetService.findTagsByGadgetId(id);
    }
}


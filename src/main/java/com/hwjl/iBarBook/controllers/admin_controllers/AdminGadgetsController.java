package com.hwjl.iBarBook.controllers.admin_controllers;

import com.hwjl.iBarBook.models.gadgets.Gadget;
import com.hwjl.iBarBook.services.GadgetService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/gadgets")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@AllArgsConstructor
class AdminGadgetsController {
    private final GadgetService gadgetService;

    @PostMapping("/add")
    public Gadget addGadget(@RequestBody Gadget gadget) {
        return gadgetService.save(gadget);
    }

    @PutMapping("/{id}/edit")
    public Gadget editGadget(@PathVariable Long id, @RequestBody Gadget updatedGadget) {
        return gadgetService.updateGadget(id, updatedGadget);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGadget(@PathVariable Long id) {
        return gadgetService.deleteGadget(id);
    }
}

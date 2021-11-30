package com.example.r6guidebackend.controllers;

import com.example.r6guidebackend.models.Gadget;
import com.example.r6guidebackend.repositories.IGadgetRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GadgetController {

    private final IGadgetRepository IGadgetRepository;

    public GadgetController(IGadgetRepository IGadgetRepository) {
        this.IGadgetRepository = IGadgetRepository;
    }

    @PostMapping("/gadgets/")
    public String AddGadget(Gadget gadget) {
        IGadgetRepository.save(gadget);
        return gadget.getName();
    }

//    @GetMapping("/gadgets/{id}")
//    public Gadget GetGadget(@PathVariable int id) {
//        Optional<Gadget> optionalGadget = gadgetRepository.findById(id);
//        if (!optionalGadget.isPresent()) {
//            return null
//        }
//        return (Gadget)gadget;
//    }
}

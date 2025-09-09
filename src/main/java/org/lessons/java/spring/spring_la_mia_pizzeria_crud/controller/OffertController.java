package org.lessons.java.spring.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring.spring_la_mia_pizzeria_crud.model.Offert;
import org.lessons.java.spring.spring_la_mia_pizzeria_crud.service.OffertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/offerts")
public class OffertController {

    @Autowired
    private OffertService offertService;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offert") Offert formOffert, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offerts/create";
        }

        offertService.create(formOffert);

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("offert", offertService.getById(id));
        model.addAttribute("edit", true);

        return "offerts/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offert") Offert formOffert, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offerts/edit";
        }

        offertService.create(formOffert);

        return "redirect:/pizzas/" + formOffert.getPizza().getId();
    }

}

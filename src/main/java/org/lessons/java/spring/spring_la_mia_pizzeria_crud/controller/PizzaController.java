package org.lessons.java.spring.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring.spring_la_mia_pizzeria_crud.model.Offert;
import org.lessons.java.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.spring.spring_la_mia_pizzeria_crud.repository.OffertRepository;
import org.lessons.java.spring.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OffertRepository offertRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(@RequestParam(name = "name", required = false) String name, Model model) {

        List<Pizza> pizzas;
        if (name != null && !name.isEmpty()) {
            pizzas = pizzaService.findByName(name);
        } else {
            pizzas = pizzaService.findAll();
        }

        model.addAttribute("pizzas", pizzas);

        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        Pizza pizza = pizzaService.getById(id);
        model.addAttribute("pizza", pizza);

        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizzas/create";
        }
        pizzaService.create(formPizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizzas/edit";
        }
        pizzaService.update(formPizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Pizza pizza = pizzaService.getById(id);

        for (Offert offertToDelete : pizza.getOfferts()) {
            offertRepository.delete(offertToDelete);
        }

        pizzaService.delete(pizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/offerta")
    public String offerta(@PathVariable("id") Integer id, Model model) {

        Offert offert = new Offert();
        offert.setPizza(pizzaService.getById(id));

        model.addAttribute("offert", offert);
        return "offerts/create";
    }
}

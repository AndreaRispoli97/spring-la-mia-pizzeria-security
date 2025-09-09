package org.lessons.java.spring.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> findByName(String name) {
        return pizzaRepository.findByNameContainingIgnoreCase(name);
    }

    public Pizza getById(Integer id) {
        return pizzaRepository.findById(id).get();
    }

    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza) {
        pizzaRepository.delete(pizza);
    }

    public void deleteById(Integer id) {
        pizzaRepository.deleteById(id);
    }

    public Boolean existsById(Integer id) {
        return pizzaRepository.existsById(id);
    }
}

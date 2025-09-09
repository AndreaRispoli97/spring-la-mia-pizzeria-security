package org.lessons.java.spring.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The name must not be null or empty or blank")
    @Size(min = 3, max = 30, message = "The name need min 3 characters and max 30")
    private String name;

    @Lob
    private String description;

    private String image;

    @NotNull(message = "The price must not be null or empty or blank")
    private BigDecimal price;

    // public Pizza(String name, String description, String image, BigDecimal price)
    // {

    // this.name = name;
    // this.description = description;
    // this.image = image;
    // this.price = price;

    // }

    // RELAZIONI
    // per rimuovere si può anche utilizzare cascade= CascadeType.Remove
    @JsonIgnore
    @OneToMany(mappedBy = "pizza")
    private List<Offert> offerts;

    public List<Offert> getOfferts() {
        return this.offerts;
    }

    public void setOfferts(List<Offert> offerts) {
        this.offerts = offerts;
    }

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "pizza_ingredient", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    // FINE PARTE RELAZIONI

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s Ingredienti: %s", this.name, this.description);
    }
}

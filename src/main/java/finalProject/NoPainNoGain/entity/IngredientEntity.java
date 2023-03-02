package finalProject.NoPainNoGain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(schema = "app", name = "ingredient")
public class IngredientEntity {

    @Id
    @JsonIgnore
    private UUID uuid;

    private UUID product;

    @OneToOne
    @Transient
    private ProductEntity productEntity;

    private int weight;

    private int calories;

    private double proteins;

    private double fats;

    private double carbohydrates;

    private UUID recipe;

    @ManyToOne
    @Transient
    private RecipeEntity recipeEntity;

    public IngredientEntity() {
    }

    public IngredientEntity(UUID uuid, UUID product, int weight, int calories, double proteins,
                            double fats, double carbohydrates, UUID recipe) {
        this.uuid = uuid;
        this.product = product;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.recipe = recipe;
    }

    public UUID getRecipe() {
        return recipe;
    }

    public void setRecipe(UUID recipe) {
        this.recipe = recipe;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getProduct() {
        return product;
    }

    public void setProduct(UUID product) {
        this.product = product;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
}

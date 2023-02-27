package finalProject.NoPainNoGain.core.dto.nutrition;

import java.util.UUID;

public class SaveIngredientDTO extends IngredientDTO {

    private short calories;

    private double proteins;

    private double fats;

    private double carbohydrates;

    public SaveIngredientDTO() {
    }

    public SaveIngredientDTO(UUID product, short weight, short calories, double proteins, double fats,
                             double carbohydrates) {
        super(product, weight);
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public short getCalories() {
        return calories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }
}

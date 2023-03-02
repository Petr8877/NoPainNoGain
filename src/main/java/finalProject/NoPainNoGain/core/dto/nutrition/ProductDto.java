package finalProject.NoPainNoGain.core.dto.nutrition;

import jakarta.validation.constraints.NotEmpty;

public class ProductDto {

    @NotEmpty
    private final String title;

    @NotEmpty
    private final int weight;

    @NotEmpty
    private final int calories;

    @NotEmpty
    private final double proteins;

    @NotEmpty
    private final double fats;

    @NotEmpty
    private final double carbohydrates;

    public ProductDto(String title, int weight, int calories, double proteins, double fats, double carbohydrates) {
        this.title = title;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public String getTitle() {
        return title;
    }

    public int getWeight() {
        return weight;
    }

    public int getCalories() {
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

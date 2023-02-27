package finalProject.NoPainNoGain.core.dto.nutrition;

public class ProductDTO {

    private String title;

    private short weight;

    private short calories;

    private double proteins;

    private double fats;

    private double carbohydrates;

    public ProductDTO() {
    }

    public ProductDTO(String title, short weight, short calories, double proteins, double fats, double carbohydrates) {
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

    public short getWeight() {
        return weight;
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

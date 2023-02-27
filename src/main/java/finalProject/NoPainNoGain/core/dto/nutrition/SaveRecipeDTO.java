package finalProject.NoPainNoGain.core.dto.nutrition;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class SaveRecipeDTO {

    private UUID uuid;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private String title;

    private List<SaveIngredientDTO> composition;

    private short weight;

    private short calories;

    private double proteins;

    private double fats;

    private double carbohydrates;

    public SaveRecipeDTO() {
    }

    public SaveRecipeDTO(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title,
                         List<SaveIngredientDTO> composition, short weight, short calories, double proteins,
                         double fats, double carbohydrates) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.composition = composition;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public long getDtUpdate() {
        return ZonedDateTime.of(dtUpdate, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public String getTitle() {
        return title;
    }

    public List<SaveIngredientDTO> getComposition() {
        return composition;
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

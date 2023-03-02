package finalProject.NoPainNoGain.core.dto.nutrition;

import java.util.UUID;

public record IngredientCPFCDto(UUID recipe,
                                UUID product,
                                int weight,
                                int calories,
                                double proteins,
                                double fats,
                                double carbohydrates) {
}

package finalProject.NoPainNoGain.core.dto.nutrition;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record RecipeDto(@NotEmpty String title, @NotEmpty List<IngredientDto> composition) {

}

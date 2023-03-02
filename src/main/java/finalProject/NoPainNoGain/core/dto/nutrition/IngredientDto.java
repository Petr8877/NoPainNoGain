package finalProject.NoPainNoGain.core.dto.nutrition;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public record IngredientDto(@NonNull UUID product, @NotEmpty short weight) {

}

package finalProject.NoPainNoGain.service.api;

import finalProject.NoPainNoGain.core.dto.PageDto;
import finalProject.NoPainNoGain.core.dto.nutrition.RecipeDto;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveRecipeDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IRecipeService {

    void createRecipe(RecipeDto recipeDTO);

    PageDto<SaveRecipeDto> getRecipePage(Pageable pageable);

    void updateRecipe(UUID uuid, long dtUpdate, RecipeDto recipeDTO);
}

package finalProject.NoPainNoGain.service.api;

import finalProject.NoPainNoGain.core.dto.PageDTO;
import finalProject.NoPainNoGain.core.dto.nutrition.RecipeDTO;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveRecipeDTO;

import java.util.UUID;

public interface IRecipeService {

    void createRecipe(RecipeDTO recipeDTO);

    PageDTO<SaveRecipeDTO> getRecipePage(Integer page, int size);

    void updateRecipe(UUID uuid, long dtUpdate, RecipeDTO recipeDTO);
}

package finalProject.NoPainNoGain.util.converters;

import finalProject.NoPainNoGain.core.dto.nutrition.SaveIngredientDto;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveRecipeDto;
import finalProject.NoPainNoGain.entity.IngredientEntity;
import finalProject.NoPainNoGain.entity.RecipeEntity;
import finalProject.NoPainNoGain.repository.IngredientRepository;
import finalProject.NoPainNoGain.repository.RecipeRepository;
import finalProject.NoPainNoGain.util.Utils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeEntityToDto implements Converter<RecipeEntity, SaveRecipeDto> {

    private final RecipeRepository recipeRepository;

    private final IngredientRepository ingredientRepository;

    public RecipeEntityToDto(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public SaveRecipeDto convert(RecipeEntity source) {
        List<RecipeEntity> recipeEntities = (List<RecipeEntity>) recipeRepository.findAll();
        List<SaveIngredientDto> ingredientDtos = new ArrayList<>();
        for (RecipeEntity recipeEntity : recipeEntities) {
            List<IngredientEntity> allByRecipe = ingredientRepository.findAllByRecipe(recipeEntity.getUuid());
            for (IngredientEntity ingredientEntity : allByRecipe) {
                ingredientDtos.add(new SaveIngredientDto(ingredientEntity.getUuid(), ingredientEntity.getWeight(),
                        ingredientEntity.getCalories(), ingredientEntity.getProteins(),
                        ingredientEntity.getFats(), ingredientEntity.getCarbohydrates()));
            }
        }
        return new SaveRecipeDto(source.getUuid(),source.getDtCreate(),
                Utils.convertLocalDateTimeToLong(source.getDtUpdate()), source.getTitle(), ingredientDtos,
                source.getWeight(), source.getCalories(), source.getProteins(), source.getFats(), source.getCarbohydrates());
    }
}

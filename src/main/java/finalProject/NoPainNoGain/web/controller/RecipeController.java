package finalProject.NoPainNoGain.web.controller;

import finalProject.NoPainNoGain.core.dto.PageDto;
import finalProject.NoPainNoGain.core.dto.nutrition.RecipeDto;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveRecipeDto;
import finalProject.NoPainNoGain.service.api.IRecipeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/recipe")
public class RecipeController {

    private final IRecipeService service;

    public RecipeController(IRecipeService service) {
        this.service = service;
    }

    @PostMapping
    public void addRecipe(@RequestBody @Validated RecipeDto recipeDTO) {
        service.createRecipe(recipeDTO);
    }

    @GetMapping
    public PageDto<SaveRecipeDto> getRecipePage(
            @PageableDefault(page = 0, size = 20)
            Pageable pageable) {
        return service.getRecipePage(pageable);
    }

    @PutMapping(path = "/{uuid}/dt_update/{dt_update}")
    public void updateRecipe(@PathVariable("uuid") UUID uuid, @PathVariable("dt_update") long dtUpdate,
                              @RequestBody @Validated RecipeDto recipeDTO) {
        service.updateRecipe(uuid, dtUpdate, recipeDTO);
    }
}

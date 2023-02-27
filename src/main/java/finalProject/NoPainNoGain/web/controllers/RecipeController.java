package finalProject.NoPainNoGain.web.controllers;

import finalProject.NoPainNoGain.core.dto.PageDTO;
import finalProject.NoPainNoGain.core.dto.nutrition.RecipeDTO;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveRecipeDTO;
import finalProject.NoPainNoGain.service.api.IRecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/recipe")
public class RecipeController {

    private final IRecipeService service;

    public RecipeController(IRecipeService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addRecipe(@RequestBody RecipeDTO recipeDTO) {
        service.createRecipe(recipeDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PageDTO<SaveRecipeDTO> getRecipePage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return service.getRecipePage(page, size);
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable("uuid") UUID uuid, @PathVariable("dt_update") long dtUpdate,
                              @RequestBody RecipeDTO recipeDTO) {
        service.updateRecipe(uuid, dtUpdate, recipeDTO);
    }
}

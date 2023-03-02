package finalProject.NoPainNoGain.service.impl;

import finalProject.NoPainNoGain.core.dto.nutrition.*;
import finalProject.NoPainNoGain.core.dto.PageDto;
import finalProject.NoPainNoGain.core.exception.SingleErrorResponse;
import finalProject.NoPainNoGain.entity.IngredientEntity;
import finalProject.NoPainNoGain.entity.ProductEntity;
import finalProject.NoPainNoGain.entity.RecipeEntity;
import finalProject.NoPainNoGain.repository.IngredientRepository;
import finalProject.NoPainNoGain.repository.ProductRepository;
import finalProject.NoPainNoGain.repository.RecipeRepository;
import finalProject.NoPainNoGain.service.api.IRecipeService;
import finalProject.NoPainNoGain.util.Utils;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements IRecipeService {

    private final RecipeRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final ProductRepository productRepo;

    private final ConversionService conversionService;

    public RecipeServiceImpl(RecipeRepository repo, IngredientRepository ingredientRepo, ProductRepository productRepo, ConversionService conversionService) {
        this.recipeRepo = repo;
        this.ingredientRepo = ingredientRepo;
        this.productRepo = productRepo;
        this.conversionService = conversionService;
    }

    @Override
    public void createRecipe(RecipeDto recipeDTO) {
        if (!recipeRepo.existsByTitle(recipeDTO.title())) {
            UUID uuid = UUID.randomUUID();
            LocalDateTime dtCreate = LocalDateTime.now();
            List<IngredientCPFCDto> dtos = countIngredientCPFC(recipeDTO.composition(), uuid);
            RecipeCPFCDto recipeCPFCDto = countRecipeCPFC(dtos);
            recipeRepo.save(new RecipeEntity(uuid, dtCreate, dtCreate, recipeDTO.title(), recipeCPFCDto.weight(), recipeCPFCDto.calories(),
                    recipeCPFCDto.proteins(), recipeCPFCDto.fats(), recipeCPFCDto.carbohydrates()));
            for (IngredientCPFCDto dto : dtos) {
                ingredientRepo.save(Objects.requireNonNull(conversionService.convert(dto, IngredientEntity.class)));
            }
        } else {
            throw new SingleErrorResponse("Рецепт с таким названием уже существует");
        }
    }

    @Override
    public PageDto<SaveRecipeDto> getRecipePage(Pageable pageable) {
        Page<RecipeEntity> allPage = recipeRepo.findAllPage(pageable);
        List<SaveRecipeDto> content = new ArrayList<>();
        for (RecipeEntity recipeEntity : allPage) {
            content.add(conversionService.convert(recipeEntity, SaveRecipeDto.class));
        }
        return new PageDto<>(allPage.getNumber(), allPage.getSize(), allPage.getTotalPages(),
                allPage.getTotalElements(), allPage.isFirst(), allPage.getNumberOfElements(),
                allPage.isLast(), content);
    }

    @Override
    public void updateRecipe(UUID uuid, long dtUpdate, RecipeDto recipeDTO) {
            RecipeEntity recipeById = recipeRepo.findById(uuid).orElseThrow(()
                    -> new SingleErrorResponse("Продукта с таким ид не существует"));
            if (dtUpdate == Utils.convertLocalDateTimeToLong(recipeById.getDtUpdate())) {
                RecipeCPFCDto recipeCPFCDto = countRecipeCPFC(countIngredientCPFC(recipeDTO.composition(), uuid));
                recipeById.setTitle(recipeDTO.title());
                recipeById.setWeight(recipeCPFCDto.weight());
                recipeById.setCalories(recipeCPFCDto.calories());
                recipeById.setProteins(recipeCPFCDto.proteins());
                recipeById.setFats(recipeCPFCDto.fats());
                recipeById.setCarbohydrates(recipeCPFCDto.carbohydrates());
                recipeById.setDtUpdate(LocalDateTime.now());
                recipeRepo.save(recipeById);
            } else {
                throw new SingleErrorResponse("Не верная версия");
            }
    }

    private List<IngredientCPFCDto> countIngredientCPFC(List<IngredientDto> ingredientDtoList, UUID recipe) {
        List<IngredientCPFCDto> result = new ArrayList<>();
        for (IngredientDto ingredientDto : ingredientDtoList) {
            ProductEntity product = productRepo.findById(ingredientDto.product()).get();
            int weight = ingredientDto.weight();
            double coif = (double) weight / product.getWeight();
            int calories = (int) (product.getCalories() * coif);
            double proteins = product.getProteins() * coif;
            double fats = product.getFats() * coif;
            double carbohydrates = product.getCarbohydrates() * coif;
            result.add(new IngredientCPFCDto(recipe, product.getUuid(), weight, calories, proteins, fats, carbohydrates));
        }
        return result;
    }

    private RecipeCPFCDto countRecipeCPFC(List<IngredientCPFCDto> ingredientCPFCDtos) {
        int weight = 0;
        int calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        for (IngredientCPFCDto dto : ingredientCPFCDtos) {
            weight += dto.weight();
            calories += dto.calories();
            proteins += dto.proteins();
            fats += dto.fats();
            carbohydrates += dto.carbohydrates();
        }
        return new RecipeCPFCDto(weight, calories, proteins, fats, carbohydrates);
    }
}

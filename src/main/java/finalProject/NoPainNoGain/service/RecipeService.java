package finalProject.NoPainNoGain.service;

import finalProject.NoPainNoGain.core.dto.nutrition.*;
import finalProject.NoPainNoGain.core.dto.PageDTO;
import finalProject.NoPainNoGain.core.exception.MultipleErrorResponse;
import finalProject.NoPainNoGain.core.exception.MyError;
import finalProject.NoPainNoGain.core.exception.SingleErrorResponse;
import finalProject.NoPainNoGain.entity.IngredientEntity;
import finalProject.NoPainNoGain.entity.ProductEntity;
import finalProject.NoPainNoGain.entity.RecipeEntity;
import finalProject.NoPainNoGain.repository.IngredientRepo;
import finalProject.NoPainNoGain.repository.ProductRepo;
import finalProject.NoPainNoGain.repository.RecipeRepo;
import finalProject.NoPainNoGain.service.api.IRecipeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecipeService implements IRecipeService {

    private final RecipeRepo recipeRepo;

    private final IngredientRepo ingredientRepo;
    private final ProductRepo productRepo;

    public RecipeService(RecipeRepo repo, IngredientRepo ingredientRepo, ProductRepo productRepo) {
        this.recipeRepo = repo;
        this.ingredientRepo = ingredientRepo;
        this.productRepo = productRepo;
    }

    @Override
    public void createRecipe(RecipeDTO recipeDTO) {
        check(recipeDTO);
        if (!recipeRepo.existsByTitle(recipeDTO.getTitle())) {
            List<IngredientEntity> composition = new ArrayList<>();
            String title = recipeDTO.getTitle();
            List<IngredientDTO> compositionRaw = recipeDTO.getComposition();
            UUID uuid = UUID.randomUUID();
            LocalDateTime dtCreate = LocalDateTime.now();
            short weightTotal = 0;
            short caloriesTotal = 0;
            double proteinsTotal = 0;
            double fatsTotal = 0;
            double carbohydratesTotal = 0;
            for (IngredientDTO ingredientDTO : compositionRaw) {
                ProductEntity product = productRepo.findById(ingredientDTO.getProduct()).get();
                short weightIngrid = ingredientDTO.getWeight();
                weightTotal += weightIngrid;
                double coif = (double) weightIngrid / product.getWeight();
                short caloriesIngrid = (short) (product.getCalories() * coif);
                caloriesTotal += caloriesIngrid;
                double proteinsIngrid = product.getProteins() * coif;
                proteinsTotal += proteinsIngrid;
                double fatsIngrid = product.getFats() * coif;
                fatsTotal += fatsIngrid;
                double carbohydratesIngrid = product.getCarbohydrates() * coif;
                carbohydratesTotal += carbohydratesIngrid;
                IngredientEntity ingredientEntity = new IngredientEntity(UUID.randomUUID(), product.getUuid(),
                        weightIngrid, caloriesIngrid, proteinsIngrid, fatsIngrid, carbohydratesIngrid, uuid);
                composition.add(ingredientEntity);
            }
            recipeRepo.save(new RecipeEntity(uuid, dtCreate, dtCreate, title, weightTotal, caloriesTotal,
                    proteinsTotal, fatsTotal, carbohydratesTotal));
            for (IngredientEntity ingredientEntity : composition) {
                ingredientRepo.save(ingredientEntity);
            }
        } else {
            throw new SingleErrorResponse("Рецепт с таким названием уже существует");
        }
    }

    @Override
    public PageDTO<SaveRecipeDTO> getRecipePage(Integer page, int size) {
        List<SaveRecipeDTO> productsPages = new ArrayList<>();
        List<RecipeEntity> recipeEntities = (List<RecipeEntity>) recipeRepo.findAll();
        long totalElements = recipeEntities.size();
        int totalPage = (int) Math.floor((double) totalElements / size);
        boolean first = false;
        boolean last = false;
        long numberOfElements = size;
        if (page == 0) {
            first = true;
        }
        if (page == totalPage) {
            last = true;
        }
        if (last) {
            numberOfElements = totalElements - ((long) size * page);
        }
        if (page > totalPage || page < 0) {
            throw new SingleErrorResponse("Страницы № " + page + " не существует. Последняя страница № " + totalPage);
        }
        for (int i = size * page; i < recipeEntities.size(); i++) {
            RecipeEntity recipe = recipeEntities.get(i);
            List<IngredientEntity> allByRecipe = ingredientRepo.findAllByRecipe(recipe.getUuid());
            List<SaveIngredientDTO> saveIngredientDTOList = new ArrayList<>();
            for (IngredientEntity ingredientEntity : allByRecipe) {
                saveIngredientDTOList.add(new SaveIngredientDTO(ingredientEntity.getUuid(), ingredientEntity.getWeight(),
                        ingredientEntity.getCalories(), ingredientEntity.getProteins(),
                        ingredientEntity.getFats(), ingredientEntity.getCarbohydrates()));
            }
            productsPages.add(new SaveRecipeDTO(recipe.getUuid(), recipe.getDtCreate(), recipe.getDtUpdateTime(),
                    recipe.getTitle(), saveIngredientDTOList, recipe.getWeight(), recipe.getCalories(),
                    recipe.getProteins(), recipe.getFats(),
                    recipe.getCarbohydrates()));
        }
        return new PageDTO<>(page, size, totalPage, totalElements, first, numberOfElements, last, productsPages);
    }

    @Override
    public void updateRecipe(UUID uuid, long dtUpdate, RecipeDTO recipeDTO) {
        check(recipeDTO);
        if (recipeRepo.existsById(uuid)) {
            RecipeEntity recipeById = recipeRepo.findById(uuid).get();
            if (dtUpdate == recipeById.getDtUpdate()) {
                List<IngredientDTO> compositionDTO = recipeDTO.getComposition();
                short weightTotal = 0;
                short caloriesTotal = 0;
                double proteinsTotal = 0;
                double fatsTotal = 0;
                double carbohydratesTotal = 0;
                for (IngredientDTO ingredientDTO : compositionDTO) {
                    ProductEntity product = productRepo.findById(ingredientDTO.getProduct()).get();
                    short weightIngrid = ingredientDTO.getWeight();
                    weightTotal += weightIngrid;
                    double coif = (double) weightIngrid / product.getWeight();
                    short caloriesIngrid = (short) (product.getCalories() * coif);
                    caloriesTotal += caloriesIngrid;
                    double proteinsIngrid = product.getProteins() * coif;
                    proteinsTotal += proteinsIngrid;
                    double fatsIngrid = product.getFats() * coif;
                    fatsTotal += fatsIngrid;
                    double carbohydratesIngrid = product.getCarbohydrates() * coif;
                    carbohydratesTotal += carbohydratesIngrid;
                }
                recipeById.setTitle(recipeDTO.getTitle());
                recipeById.setWeight(weightTotal);
                recipeById.setCalories(caloriesTotal);
                recipeById.setProteins(proteinsTotal);
                recipeById.setFats(fatsTotal);
                recipeById.setCarbohydrates(carbohydratesTotal);
                recipeById.setDtUpdate(LocalDateTime.now());
                recipeRepo.save(recipeById);
            } else {
                throw new SingleErrorResponse("Не верная версия");
            }
        } else {
            throw new SingleErrorResponse("Продукта с таким ид не существует");
        }
    }

    private void check(RecipeDTO recipeDTO) {
        MultipleErrorResponse errorResponse = new MultipleErrorResponse();

        if (recipeDTO == null) {
            throw new SingleErrorResponse("Заполните форму добавления рецепта");
        }
        if (recipeDTO.getTitle() == null || recipeDTO.getTitle().isBlank()) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Поле не заполнено", "title"));
        }
        if (recipeDTO.getComposition() == null || recipeDTO.getComposition().size() == 0) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Нет ни одного ингридиента", "composition"));
        }
        for (IngredientDTO ingredientDTO : recipeDTO.getComposition()) {
            if (ingredientDTO.getProduct() == null) {
                errorResponse.setErrors(new MyError("Не введен uuid продукта", "IngredientDTO.productUuid"));
            } else if (productRepo.existsById(ingredientDTO.getProduct())) {
                errorResponse.setErrors(new MyError("Продукта с данным uuid нет в базе", "IngredientDTO.productUuid"));
            } else if (ingredientDTO.getWeight() <= 0) {
                errorResponse.setErrors(new MyError("Не введен вес продукта", "IngredientDTO.weight"));
            }
        }
        if (errorResponse.getLogref() != null) {
            throw errorResponse;
        }
    }
}

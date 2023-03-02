package finalProject.NoPainNoGain.repository;

import finalProject.NoPainNoGain.entity.IngredientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface IngredientRepository extends CrudRepository<IngredientEntity, UUID> {

    List<IngredientEntity> findAllByRecipe(UUID uuid);
}

package finalProject.NoPainNoGain.repository;

import finalProject.NoPainNoGain.entity.IngredientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IngredientRepo extends CrudRepository<IngredientEntity, UUID> {

    List<IngredientEntity> findAllByRecipe(UUID uuid);
}

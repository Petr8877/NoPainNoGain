package finalProject.NoPainNoGain.repository;

import finalProject.NoPainNoGain.entity.RecipeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeRepo extends CrudRepository<RecipeEntity, UUID> {

    boolean existsByTitle(String title);
}

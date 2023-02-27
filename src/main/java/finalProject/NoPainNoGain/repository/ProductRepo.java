package finalProject.NoPainNoGain.repository;

import finalProject.NoPainNoGain.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepo extends CrudRepository<ProductEntity, UUID> {

    boolean existsByTitle(String title);
}

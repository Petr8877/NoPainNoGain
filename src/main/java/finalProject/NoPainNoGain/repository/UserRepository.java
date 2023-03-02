package finalProject.NoPainNoGain.repository;

import finalProject.NoPainNoGain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    @Query("select c from UserEntity c")
    Page<UserEntity> findAllPage(Pageable pageable);
}

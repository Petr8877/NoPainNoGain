package finalProject.NoPainNoGain.repository;

import finalProject.NoPainNoGain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RegistrationUserRepo extends JpaRepository<UserEntity, UUID> {

    UserEntity findByMail(String mail);

    boolean existsByMail(String mail);
}

package finalProject.NoPainNoGain.core.dto.user;


import finalProject.NoPainNoGain.usersEnum.RoleUser;
import finalProject.NoPainNoGain.usersEnum.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

public record UserDto(@NotBlank @Email String mail,
                      @NotBlank String fio,
                      @NonNull RoleUser role,
                      @NonNull Status status,
                      @NotBlank String password) {

}

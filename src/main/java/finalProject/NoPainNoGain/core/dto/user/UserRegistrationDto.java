package finalProject.NoPainNoGain.core.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserRegistrationDto(@NotEmpty @Email String mail,
                                  @NotEmpty String fio,
                                  @NotEmpty String password) {

}

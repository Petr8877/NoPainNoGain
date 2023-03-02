package finalProject.NoPainNoGain.core.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginDto(@NotEmpty @Email String mail,
                       @NotEmpty String password) {

}

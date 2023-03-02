package finalProject.NoPainNoGain.core.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import finalProject.NoPainNoGain.usersEnum.RoleUser;
import finalProject.NoPainNoGain.usersEnum.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.time.*;
import java.util.UUID;

public record SaveUserDto(@NonNull UUID uuid,
                          @NonNull @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss.SS") @JsonProperty("dt_create") LocalDateTime dtCreate,
                          @NotEmpty @JsonProperty("dt_update") long dtUpdate,
                          @NotEmpty @Email String mail,
                          @NotEmpty String fio,
                          @NonNull RoleUser role,
                          @NonNull Status status) {

}

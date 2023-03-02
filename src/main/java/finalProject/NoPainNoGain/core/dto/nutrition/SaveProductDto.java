package finalProject.NoPainNoGain.core.dto.nutrition;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class SaveProductDto extends ProductDto {

    @NonNull
    private final UUID uuid;

    @NonNull
    private final LocalDateTime dtCreate;

    @NotEmpty
    private final long dtUpdate;

    public SaveProductDto(String title, int weight, int calories, double proteins, double fats,
                          double carbohydrates, UUID uuid, LocalDateTime dtCreate, long dtUpdate) {
        super(title, weight, calories, proteins, fats, carbohydrates);
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public long getDtUpdate() {
        return dtUpdate;
    }
}

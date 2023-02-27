package finalProject.NoPainNoGain.core.dto.nutrition;

import java.time.LocalDateTime;
import java.util.UUID;

public class SaveProductDTO extends ProductDTO {

    private UUID uuid;

    private LocalDateTime dtCreate;

    private long dtUpdate;

    public SaveProductDTO() {
    }

    public SaveProductDTO(String title, short weight, short calories, double proteins, double fats,
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

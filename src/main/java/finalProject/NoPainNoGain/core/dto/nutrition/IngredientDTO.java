package finalProject.NoPainNoGain.core.dto.nutrition;

import java.util.UUID;

public class IngredientDTO {

    private UUID product;

    private short weight;

    public IngredientDTO() {
    }

    public IngredientDTO(UUID product, short weight) {
        this.product = product;
        this.weight = weight;
    }

    public UUID getProduct() {
        return product;
    }

    public short getWeight() {
        return weight;
    }
}

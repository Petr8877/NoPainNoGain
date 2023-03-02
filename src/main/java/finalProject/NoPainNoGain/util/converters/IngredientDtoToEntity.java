package finalProject.NoPainNoGain.util.converters;

import finalProject.NoPainNoGain.core.dto.nutrition.IngredientCPFCDto;
import finalProject.NoPainNoGain.entity.IngredientEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IngredientDtoToEntity implements Converter<IngredientCPFCDto, IngredientEntity> {
    @Override
    public IngredientEntity convert(IngredientCPFCDto source) {
        return new IngredientEntity(UUID.randomUUID(), source.product(), source.weight(), source.calories(),
                source.proteins(), source.fats(), source.carbohydrates(), source.recipe());
    }
}

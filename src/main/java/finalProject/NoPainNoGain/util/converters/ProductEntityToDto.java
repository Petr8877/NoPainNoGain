package finalProject.NoPainNoGain.util.converters;

import finalProject.NoPainNoGain.core.dto.nutrition.SaveProductDto;
import finalProject.NoPainNoGain.entity.ProductEntity;
import finalProject.NoPainNoGain.util.Utils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToDto implements Converter<ProductEntity, SaveProductDto> {
    @Override
    public SaveProductDto convert(ProductEntity source) {
        return new SaveProductDto(source.getTitle(), source.getWeight(), source.getCalories(), source.getProteins(),
                source.getFats(), source.getCarbohydrates(), source.getUuid(), source.getDtCreate(),
                Utils.convertLocalDateTimeToLong(source.getDtUpdate()));
    }
}

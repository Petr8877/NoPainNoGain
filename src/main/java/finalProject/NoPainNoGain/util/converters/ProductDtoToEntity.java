package finalProject.NoPainNoGain.util.converters;

import finalProject.NoPainNoGain.core.dto.nutrition.ProductDto;
import finalProject.NoPainNoGain.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ProductDtoToEntity implements Converter<ProductDto, ProductEntity> {
    @Override
    public ProductEntity convert(ProductDto source) {
        LocalDateTime time = LocalDateTime.now();
        return new ProductEntity(UUID.randomUUID(), time, time, source.getTitle(), source.getWeight(),
                source.getCalories(), source.getProteins(), source.getFats(), source.getCarbohydrates());
    }
}

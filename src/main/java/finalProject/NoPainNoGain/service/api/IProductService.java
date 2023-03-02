package finalProject.NoPainNoGain.service.api;

import finalProject.NoPainNoGain.core.dto.PageDto;
import finalProject.NoPainNoGain.core.dto.nutrition.ProductDto;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveProductDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IProductService {

    void addProduct(ProductDto productDTO);

    void updateProduct(UUID uuid, long dtUpdate, ProductDto productDTO);

    PageDto<SaveProductDto> getProductPage(Pageable pageable);
}

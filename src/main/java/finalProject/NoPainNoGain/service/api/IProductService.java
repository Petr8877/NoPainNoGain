package finalProject.NoPainNoGain.service.api;

import finalProject.NoPainNoGain.core.dto.PageDTO;
import finalProject.NoPainNoGain.core.dto.nutrition.ProductDTO;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveProductDTO;

import java.util.UUID;

public interface IProductService {

    void addProduct(ProductDTO productDTO);

    void updateProduct(UUID uuid, long dtUpdate, ProductDTO productDTO);

    PageDTO<SaveProductDTO> getProductPage(Integer page, int size);
}

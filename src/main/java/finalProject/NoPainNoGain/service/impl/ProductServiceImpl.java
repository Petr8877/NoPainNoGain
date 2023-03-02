package finalProject.NoPainNoGain.service.impl;

import finalProject.NoPainNoGain.core.dto.PageDto;
import finalProject.NoPainNoGain.core.dto.nutrition.ProductDto;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveProductDto;
import finalProject.NoPainNoGain.core.exception.SingleErrorResponse;
import finalProject.NoPainNoGain.entity.ProductEntity;
import finalProject.NoPainNoGain.repository.ProductRepository;
import finalProject.NoPainNoGain.service.api.IProductService;
import finalProject.NoPainNoGain.util.Utils;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repo;

    private final ConversionService conversionService;

    public ProductServiceImpl(ProductRepository repo, ConversionService conversionService) {
        this.repo = repo;
        this.conversionService = conversionService;
    }

    @Override
    public void addProduct(ProductDto productDTO) {
        if (!repo.existsByTitle(productDTO.getTitle())) {
            repo.save(Objects.requireNonNull(conversionService.convert(productDTO, ProductEntity.class)));
        } else {
            throw new SingleErrorResponse("Продукт с таким наименованием уже существует");
        }
    }

    @Override
    public void updateProduct(UUID uuid, long dtUpdate, ProductDto productDTO) {
            ProductEntity productById = repo.findById(uuid).orElseThrow(()
                    -> new SingleErrorResponse("Продукта с таким ид не существует"));
            if (dtUpdate == Utils.convertLocalDateTimeToLong(productById.getDtUpdate())) {
                productById.setTitle(productDTO.getTitle());
                productById.setWeight(productDTO.getWeight());
                productById.setCalories(productDTO.getCalories());
                productById.setProteins(productDTO.getProteins());
                productById.setFats(productDTO.getFats());
                productById.setCarbohydrates(productDTO.getCarbohydrates());
                productById.setDtUpdate(LocalDateTime.now());
                repo.save(productById);
            } else {
                throw new SingleErrorResponse("Не верная версия");
            }
    }

    @Override
    public PageDto<SaveProductDto> getProductPage(Pageable pageable) {
        Page<ProductEntity> allPage = repo.findAllPage(pageable);
        List<SaveProductDto> content = new ArrayList<>();
        for (ProductEntity product : allPage) {
            content.add(conversionService.convert(product, SaveProductDto.class));
        }
        return new PageDto<>(allPage.getNumber(), allPage.getSize(), allPage.getTotalPages(),
                allPage.getTotalElements(), allPage.isFirst(), allPage.getNumberOfElements(),
                allPage.isLast(), content);
    }
}

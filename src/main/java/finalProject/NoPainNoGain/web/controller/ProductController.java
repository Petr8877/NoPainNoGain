package finalProject.NoPainNoGain.web.controller;

import finalProject.NoPainNoGain.core.dto.PageDto;
import finalProject.NoPainNoGain.core.dto.nutrition.ProductDto;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveProductDto;
import finalProject.NoPainNoGain.service.api.IProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @PostMapping
    public void addProduct(@RequestBody @Validated ProductDto productDTO) {
        service.addProduct(productDTO);
    }

    @PutMapping(path = "/{uuid}/dt_update/{dt_update}")
    public void updateProduct(@PathVariable("uuid") UUID uuid, @PathVariable("dt_update") long dtUpdate,
                              @RequestBody @Validated ProductDto productDTO) {
        service.updateProduct(uuid, dtUpdate, productDTO);
    }

    @GetMapping
    public PageDto<SaveProductDto> getProductPage(
            @PageableDefault(page = 0, size = 20)
            Pageable pageable) {
        return service.getProductPage(pageable);
    }
}

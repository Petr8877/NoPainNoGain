package finalProject.NoPainNoGain.web.controllers;

import finalProject.NoPainNoGain.core.dto.PageDTO;
import finalProject.NoPainNoGain.core.dto.nutrition.ProductDTO;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveProductDTO;
import finalProject.NoPainNoGain.service.api.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addProduct(@RequestBody ProductDTO productDTO) {
        service.addProduct(productDTO);
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable("uuid") UUID uuid, @PathVariable("dt_update") long dtUpdate,
                              @RequestBody ProductDTO productDTO) {
        service.updateProduct(uuid, dtUpdate, productDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PageDTO<SaveProductDTO> getProductPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return service.getProductPage(page, size);
    }
}

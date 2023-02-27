package finalProject.NoPainNoGain.service;

import finalProject.NoPainNoGain.core.dto.PageDTO;
import finalProject.NoPainNoGain.core.dto.nutrition.ProductDTO;
import finalProject.NoPainNoGain.core.dto.nutrition.SaveProductDTO;
import finalProject.NoPainNoGain.core.exception.MultipleErrorResponse;
import finalProject.NoPainNoGain.core.exception.MyError;
import finalProject.NoPainNoGain.core.exception.SingleErrorResponse;
import finalProject.NoPainNoGain.entity.ProductEntity;
import finalProject.NoPainNoGain.repository.ProductRepo;
import finalProject.NoPainNoGain.service.api.IProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IProductService {

    private final ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        check(productDTO);
        if (!repo.existsByTitle(productDTO.getTitle())) {
            String title = productDTO.getTitle();
            short weight = productDTO.getWeight();
            short calories = productDTO.getCalories();
            double proteins = productDTO.getProteins();
            double fats = productDTO.getFats();
            double carbohydrates = productDTO.getCarbohydrates();
            UUID uuid = UUID.randomUUID();
            LocalDateTime dtCreate = LocalDateTime.now();
            repo.save(new ProductEntity(uuid, dtCreate, dtCreate, title, weight, calories, proteins, fats, carbohydrates));
        } else {
            throw new SingleErrorResponse("Продукт с таким наименованием уже существует");
        }
    }

    @Override
    public void updateProduct(UUID uuid, long dtUpdate, ProductDTO productDTO) {
        if (repo.existsById(uuid)){
            ProductEntity productById = repo.findById(uuid).get();
            if (dtUpdate == productById.getDtUpdate()){
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
        } else {
            throw new SingleErrorResponse("Продукта с таким ид не существует");
        }
    }

    @Override
    public PageDTO<SaveProductDTO> getProductPage(Integer page, int size) {
        List<SaveProductDTO> productsPages = new ArrayList<>();
        List<ProductEntity> productEntities = (List<ProductEntity>) repo.findAll();
        long totalElements = productEntities.size();
        int totalPage = (int) Math.floor((double) totalElements / size);
        boolean first = false;
        boolean last = false;
        long numberOfElements = size;
        if (page == 0) {
            first = true;
        }
        if (page == totalPage) {
            last = true;
        }
        if (last) {
            numberOfElements = totalElements - ((long) size * page);
        }
        if (page > totalPage || page < 0) {
            throw new SingleErrorResponse("Страницы № " + page + " не существует. Последняя страница № " + totalPage);
        }
        for (int i = size * page; i < productEntities.size(); i++) {
            ProductEntity product = productEntities.get(i);
            productsPages.add(new SaveProductDTO(product.getTitle(), product.getWeight(), product.getCalories(), product.getProteins(),
                    product.getFats(), product.getCarbohydrates(), product.getUuid(), product.getDtCreate(), product.getDtUpdate()));
        }
        return new PageDTO<>(page, size, totalPage, totalElements, first, numberOfElements, last, productsPages);
    }
    private void check(ProductDTO productDTO) {
        MultipleErrorResponse errorResponse = new MultipleErrorResponse();

        if (productDTO == null) {
            throw new SingleErrorResponse("Заполните форму добавления продукта");
        }
        if (productDTO.getTitle() == null || productDTO.getTitle().isBlank()) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Поле не заполнено", "title"));
        }
        if (productDTO.getWeight() <= 0) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("вес не может быть меньше или равен 0", "weight"));
        }
        if (productDTO.getCalories() < 0) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Значение калорий не может быть меньше 0", "calories"));
        }
        if (productDTO.getProteins() < 0) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Значение белков не может быть меньше 0", "proteins"));
        }
        if (productDTO.getFats() < 0) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Значение жиров не может быть меньше 0", "fats"));
        }
        if (productDTO.getCarbohydrates() < 0) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Значение углеводов не может быть меньше 0", "carbohydrates"));
        }
        if (errorResponse.getLogref() != null) {
            throw errorResponse;
        }
    }
}

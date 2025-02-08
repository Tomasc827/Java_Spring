package lt.techin.media_site.service;


import lt.techin.media_site.model.Category;
import lt.techin.media_site.model.media_enum.CategoryEnum;
import lt.techin.media_site.repository.CategoryRepository;
import lt.techin.media_site.validation.exception.IdDoesNotExistException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategoryById(byte id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IdDoesNotExistException("Category with id'" + id + "does not exist"));
    }

    public Category findCategoryByCategory(CategoryEnum categoryEnum) {
        return categoryRepository.findByCategory(categoryEnum).orElseThrow(() -> new IdDoesNotExistException("Category with id'" + categoryEnum + "does not exist"));
    }
}

package LapTrinhWeb.SpringBoot.controller;

import LapTrinhWeb.SpringBoot.entity.Category;
import LapTrinhWeb.SpringBoot.entity.User;
import LapTrinhWeb.SpringBoot.repository.CategoryRepository;
import LapTrinhWeb.SpringBoot.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class CategoryGraphQLController {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private UserCategoryService userCategoryService;

    @QueryMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @QueryMapping
    public Optional<Category> getCategory(@Argument Long id) {
        return categoryRepository.findById(id);
    }
    
    @QueryMapping
    public List<User> getCategoryUsers(@Argument Long categoryId) {
        return userCategoryService.getUsersByCategoryId(categoryId);
    }

    @MutationMapping
    public Category createCategory(@Argument Map<String, Object> input) {
        Category category = new Category();
        category.setName((String) input.get("name"));
        category.setImages((String) input.get("images"));
        
        return categoryRepository.save(category);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument Map<String, Object> input) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        
        category.setName((String) input.get("name"));
        category.setImages((String) input.get("images"));
        
        return categoryRepository.save(category);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
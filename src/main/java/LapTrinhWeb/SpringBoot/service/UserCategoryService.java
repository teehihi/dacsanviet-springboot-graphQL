package LapTrinhWeb.SpringBoot.service;

import LapTrinhWeb.SpringBoot.entity.User;
import LapTrinhWeb.SpringBoot.entity.Category;
import LapTrinhWeb.SpringBoot.repository.UserRepository;
import LapTrinhWeb.SpringBoot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserCategoryService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategoriesByUserId(Long userId) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null && user.getCategories() != null) {
                // Force initialization of lazy collection
                user.getCategories().size();
                return user.getCategories().stream().collect(Collectors.toList());
            }
        } catch (Exception e) {
            System.err.println("Error getting categories for user " + userId + ": " + e.getMessage());
        }
        return List.of();
    }

    public List<User> getUsersByCategoryId(Long categoryId) {
        try {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            if (category != null && category.getUsers() != null) {
                // Force initialization of lazy collection
                category.getUsers().size();
                return category.getUsers().stream().collect(Collectors.toList());
            }
        } catch (Exception e) {
            System.err.println("Error getting users for category " + categoryId + ": " + e.getMessage());
        }
        return List.of();
    }

    public User addCategoriesToUser(Long userId, List<Long> categoryIds) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : categoryIds) {
            Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
            categories.add(category);
        }
        
        user.setCategories(categories);
        return userRepository.save(user);
    }

    public User updateUserCategories(Long userId, List<Long> categoryIds) {
        return addCategoriesToUser(userId, categoryIds);
    }
}
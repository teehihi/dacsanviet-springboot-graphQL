package LapTrinhWeb.SpringBoot.service;

import LapTrinhWeb.SpringBoot.entity.User;
import LapTrinhWeb.SpringBoot.entity.Category;
import LapTrinhWeb.SpringBoot.entity.UserCategory;
import LapTrinhWeb.SpringBoot.repository.UserRepository;
import LapTrinhWeb.SpringBoot.repository.CategoryRepository;
import LapTrinhWeb.SpringBoot.repository.UserCategoryRepository;
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
    
    @Autowired
    private UserCategoryRepository userCategoryRepository;

    public List<Category> getCategoriesByUserId(Long userId) {
        try {
            // Use direct query to avoid N+1 problem
            List<UserCategory> userCategories = userCategoryRepository.findByUserId(userId);
            return userCategories.stream()
                .map(uc -> categoryRepository.findById(uc.getCategoryId()).orElse(null))
                .filter(category -> category != null)
                .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error getting categories for user " + userId + ": " + e.getMessage());
        }
        return List.of();
    }

    public List<User> getUsersByCategoryId(Long categoryId) {
        try {
            // Use direct query to avoid N+1 problem
            List<UserCategory> userCategories = userCategoryRepository.findByCategoryId(categoryId);
            return userCategories.stream()
                .map(uc -> userRepository.findById(uc.getUserId()).orElse(null))
                .filter(user -> user != null)
                .collect(Collectors.toList());
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
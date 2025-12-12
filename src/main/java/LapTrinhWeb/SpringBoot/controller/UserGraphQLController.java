package LapTrinhWeb.SpringBoot.controller;

import LapTrinhWeb.SpringBoot.entity.User;
import LapTrinhWeb.SpringBoot.entity.Category;
import LapTrinhWeb.SpringBoot.repository.UserRepository;
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
public class UserGraphQLController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserCategoryService userCategoryService;

    @QueryMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public Optional<User> getUser(@Argument Long id) {
        return userRepository.findById(id);
    }
    
    @QueryMapping
    public List<Category> getUserCategories(@Argument Long userId) {
        return userCategoryService.getCategoriesByUserId(userId);
    }

    @MutationMapping
    public User createUser(@Argument Map<String, Object> input) {
        User user = new User();
        user.setFullname((String) input.get("fullname"));
        user.setEmail((String) input.get("email"));
        user.setPassword((String) input.get("password"));
        user.setPhone((String) input.get("phone"));
        
        User savedUser = userRepository.save(user);
        
        // Handle categories if provided
        @SuppressWarnings("unchecked")
        List<String> categoryIds = (List<String>) input.get("categoryIds");
        if (categoryIds != null && !categoryIds.isEmpty()) {
            List<Long> categoryIdLongs = categoryIds.stream()
                .map(Long::valueOf)
                .toList();
            savedUser = userCategoryService.addCategoriesToUser(savedUser.getId(), categoryIdLongs);
        }
        
        return savedUser;
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument Map<String, Object> input) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setFullname((String) input.get("fullname"));
        user.setEmail((String) input.get("email"));
        user.setPassword((String) input.get("password"));
        user.setPhone((String) input.get("phone"));
        
        User savedUser = userRepository.save(user);
        
        // Handle categories if provided
        @SuppressWarnings("unchecked")
        List<String> categoryIds = (List<String>) input.get("categoryIds");
        if (categoryIds != null) {
            List<Long> categoryIdLongs = categoryIds.stream()
                .map(Long::valueOf)
                .toList();
            savedUser = userCategoryService.updateUserCategories(savedUser.getId(), categoryIdLongs);
        }
        
        return savedUser;
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
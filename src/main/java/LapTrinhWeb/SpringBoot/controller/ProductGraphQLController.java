package LapTrinhWeb.SpringBoot.controller;

import LapTrinhWeb.SpringBoot.entity.Product;
import LapTrinhWeb.SpringBoot.entity.User;
import LapTrinhWeb.SpringBoot.entity.Category;
import LapTrinhWeb.SpringBoot.repository.ProductRepository;
import LapTrinhWeb.SpringBoot.repository.UserRepository;
import LapTrinhWeb.SpringBoot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProductGraphQLController {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @QueryMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @QueryMapping
    public List<Product> getProductsSortedByPrice() {
        return productRepository.findAllOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> getProductsByCategory(@Argument Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @QueryMapping
    public Product getProduct(@Argument Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Product createProduct(@Argument Map<String, Object> input) {
        Product product = new Product();
        product.setTitle((String) input.get("title"));
        product.setQuantity((Integer) input.get("quantity"));
        product.setDesc((String) input.get("desc"));
        
        // Convert price to BigDecimal
        Object priceObj = input.get("price");
        BigDecimal price = null;
        if (priceObj instanceof Double) {
            price = BigDecimal.valueOf((Double) priceObj);
        } else if (priceObj instanceof Integer) {
            price = BigDecimal.valueOf((Integer) priceObj);
        }
        product.setPrice(price);
        
        // Set user ID
        Long userId = Long.valueOf(input.get("userId").toString());
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        product.setUserId(userId);
        
        // Set category ID if provided
        Object categoryIdObj = input.get("categoryId");
        if (categoryIdObj != null) {
            Long categoryId = Long.valueOf(categoryIdObj.toString());
            if (!categoryRepository.existsById(categoryId)) {
                throw new RuntimeException("Category not found");
            }
            product.setCategoryId(categoryId);
        }
        
        return productRepository.save(product);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument Map<String, Object> input) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        product.setTitle((String) input.get("title"));
        product.setQuantity((Integer) input.get("quantity"));
        product.setDesc((String) input.get("desc"));
        
        // Convert price to BigDecimal
        Object priceObj = input.get("price");
        BigDecimal price = null;
        if (priceObj instanceof Double) {
            price = BigDecimal.valueOf((Double) priceObj);
        } else if (priceObj instanceof Integer) {
            price = BigDecimal.valueOf((Integer) priceObj);
        }
        product.setPrice(price);
        
        // Update user ID
        Long userId = Long.valueOf(input.get("userId").toString());
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        product.setUserId(userId);
        
        // Update category ID if provided
        Object categoryIdObj = input.get("categoryId");
        if (categoryIdObj != null) {
            Long categoryId = Long.valueOf(categoryIdObj.toString());
            if (!categoryRepository.existsById(categoryId)) {
                throw new RuntimeException("Category not found");
            }
            product.setCategoryId(categoryId);
        }
        
        return productRepository.save(product);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
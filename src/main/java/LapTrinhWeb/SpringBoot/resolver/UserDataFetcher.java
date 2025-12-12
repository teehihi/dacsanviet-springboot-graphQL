package LapTrinhWeb.SpringBoot.resolver;

import LapTrinhWeb.SpringBoot.entity.User;
import LapTrinhWeb.SpringBoot.entity.Category;
import LapTrinhWeb.SpringBoot.entity.Product;
import LapTrinhWeb.SpringBoot.repository.ProductRepository;
import LapTrinhWeb.SpringBoot.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
public class UserDataFetcher {

    @Autowired
    private UserCategoryService userCategoryService;
    
    @Autowired
    private ProductRepository productRepository;

    @SchemaMapping(typeName = "User", field = "categories")
    @Transactional(readOnly = true)
    public List<Category> getCategories(User user) {
        try {
            return userCategoryService.getCategoriesByUserId(user.getId());
        } catch (Exception e) {
            System.err.println("Error in UserDataFetcher.getCategories: " + e.getMessage());
            return List.of();
        }
    }

    @SchemaMapping(typeName = "User", field = "products")
    @Transactional(readOnly = true)
    public List<Product> getProducts(User user) {
        try {
            return productRepository.findByUserId(user.getId());
        } catch (Exception e) {
            System.err.println("Error in UserDataFetcher.getProducts: " + e.getMessage());
            return List.of();
        }
    }
}
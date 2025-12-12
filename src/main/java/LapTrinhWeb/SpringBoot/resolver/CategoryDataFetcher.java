package LapTrinhWeb.SpringBoot.resolver;

import LapTrinhWeb.SpringBoot.entity.Category;
import LapTrinhWeb.SpringBoot.entity.User;
import LapTrinhWeb.SpringBoot.entity.Product;
import LapTrinhWeb.SpringBoot.repository.ProductRepository;
import LapTrinhWeb.SpringBoot.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
public class CategoryDataFetcher {

    @Autowired
    private UserCategoryService userCategoryService;
    
    @Autowired
    private ProductRepository productRepository;

    @SchemaMapping(typeName = "Category", field = "users")
    @Transactional(readOnly = true)
    public List<User> getUsers(Category category) {
        try {
            return userCategoryService.getUsersByCategoryId(category.getId());
        } catch (Exception e) {
            System.err.println("Error in CategoryDataFetcher.getUsers: " + e.getMessage());
            return List.of();
        }
    }

    @SchemaMapping(typeName = "Category", field = "products")
    @Transactional(readOnly = true)
    public List<Product> getProducts(Category category) {
        try {
            return productRepository.findByCategoryId(category.getId());
        } catch (Exception e) {
            System.err.println("Error in CategoryDataFetcher.getProducts: " + e.getMessage());
            return List.of();
        }
    }
}
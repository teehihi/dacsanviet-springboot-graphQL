package LapTrinhWeb.SpringBoot.resolver;

import LapTrinhWeb.SpringBoot.entity.Product;
import LapTrinhWeb.SpringBoot.entity.User;
import LapTrinhWeb.SpringBoot.entity.Category;
import LapTrinhWeb.SpringBoot.repository.UserRepository;
import LapTrinhWeb.SpringBoot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ProductDataFetcher {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @SchemaMapping(typeName = "Product", field = "user")
    public User getUser(Product product) {
        if (product.getUserId() != null) {
            return userRepository.findById(product.getUserId()).orElse(null);
        }
        return null;
    }

    @SchemaMapping(typeName = "Product", field = "category")
    public Category getCategory(Product product) {
        if (product.getCategoryId() != null) {
            return categoryRepository.findById(product.getCategoryId()).orElse(null);
        }
        return null;
    }
}
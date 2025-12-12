package LapTrinhWeb.SpringBoot.repository;

import LapTrinhWeb.SpringBoot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllOrderByPriceAsc();
    
    List<Product> findByCategoryId(Long categoryId);
    
    List<Product> findByUserId(Long userId);
}
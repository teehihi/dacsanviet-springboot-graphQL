package LapTrinhWeb.SpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String title;
    
    private Integer quantity;
    
    @Column(name = "description", columnDefinition = "NVARCHAR(1000)")
    private String desc;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "category_id")
    private Long categoryId;
}
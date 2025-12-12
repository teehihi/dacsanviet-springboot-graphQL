package LapTrinhWeb.SpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"categories"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String fullname;
    
    @Column(nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
    private String email;
    
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String password;
    
    @Column(columnDefinition = "NVARCHAR(20)")
    private String phone;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_categories",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private Set<Category> categories;
}
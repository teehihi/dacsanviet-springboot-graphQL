package LapTrinhWeb.SpringBoot.repository;

import LapTrinhWeb.SpringBoot.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    
    List<UserCategory> findByUserId(Long userId);
    
    List<UserCategory> findByCategoryId(Long categoryId);
    
    void deleteByUserIdAndCategoryId(Long userId, Long categoryId);
    
    void deleteByUserId(Long userId);
}
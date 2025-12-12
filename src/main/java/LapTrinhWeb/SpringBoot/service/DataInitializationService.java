package LapTrinhWeb.SpringBoot.service;

import LapTrinhWeb.SpringBoot.entity.Category;
import LapTrinhWeb.SpringBoot.entity.Product;
import LapTrinhWeb.SpringBoot.entity.User;
import LapTrinhWeb.SpringBoot.repository.CategoryRepository;
import LapTrinhWeb.SpringBoot.repository.ProductRepository;
import LapTrinhWeb.SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class DataInitializationService implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only initialize if database is empty
        if (categoryRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Create Categories với tiếng Việt
        Category electronics = new Category();
        electronics.setName("Điện tử & Công nghệ");
        electronics.setImages("https://example.com/electronics.jpg");
        electronics = categoryRepository.save(electronics);

        Category fashion = new Category();
        fashion.setName("Thời trang & Phụ kiện");
        fashion.setImages("https://example.com/fashion.jpg");
        fashion = categoryRepository.save(fashion);

        Category books = new Category();
        books.setName("Sách & Giáo dục");
        books.setImages("https://example.com/books.jpg");
        books = categoryRepository.save(books);

        Category food = new Category();
        food.setName("Thực phẩm & Đồ uống");
        food.setImages("https://example.com/food.jpg");
        food = categoryRepository.save(food);

        // Create Users với tên tiếng Việt đầy đủ
        User user1 = new User();
        user1.setFullname("Nguyễn Văn An");
        user1.setEmail("nguyenvanan@gmail.com");
        user1.setPassword("matkhau123");
        user1.setPhone("0901234567");
        // Set categories for user1
        Set<Category> user1Categories = new HashSet<>();
        user1Categories.add(electronics);
        user1Categories.add(books);
        user1.setCategories(user1Categories);
        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setFullname("Trần Thị Bình An");
        user2.setEmail("tranthibinhan@gmail.com");
        user2.setPassword("matkhau123");
        user2.setPhone("0912345678");
        // Set categories for user2
        Set<Category> user2Categories = new HashSet<>();
        user2Categories.add(fashion);
        user2Categories.add(food);
        user2.setCategories(user2Categories);
        user2 = userRepository.save(user2);

        User user3 = new User();
        user3.setFullname("Lê Văn Cường Thịnh");
        user3.setEmail("levancuongthinh@gmail.com");
        user3.setPassword("matkhau123");
        user3.setPhone("0923456789");
        // Set categories for user3
        Set<Category> user3Categories = new HashSet<>();
        user3Categories.add(electronics);
        user3Categories.add(fashion);
        user3.setCategories(user3Categories);
        user3 = userRepository.save(user3);

        // Create Products
        // Electronics products với mô tả tiếng Việt chi tiết
        Product laptop = new Product();
        laptop.setTitle("Laptop Dell XPS 13 - Màn hình 4K");
        laptop.setQuantity(10);
        laptop.setDesc("Laptop cao cấp với hiệu năng mạnh mẽ, màn hình 4K sắc nét, thiết kế mỏng nhẹ, phù hợp cho công việc và giải trí");
        laptop.setPrice(new BigDecimal("25000000"));
        laptop.setUserId(user1.getId());
        laptop.setCategoryId(electronics.getId());
        productRepository.save(laptop);

        Product phone = new Product();
        phone.setTitle("iPhone 15 Pro - Titanium Tự Nhiên");
        phone.setQuantity(5);
        phone.setDesc("Điện thoại thông minh mới nhất từ Apple với chip A17 Pro, camera 48MP, khung titanium bền bỉ và sang trọng");
        phone.setPrice(new BigDecimal("30000000"));
        phone.setUserId(user3.getId());
        phone.setCategoryId(electronics.getId());
        productRepository.save(phone);

        Product headphones = new Product();
        headphones.setTitle("Sony WH-1000XM5 - Chống ồn thông minh");
        headphones.setQuantity(15);
        headphones.setDesc("Tai nghe chống ồn cao cấp với công nghệ AI, âm thanh Hi-Res, pin 30 giờ, phù hợp cho âm nhạc và công việc");
        headphones.setPrice(new BigDecimal("8000000"));
        headphones.setUserId(user1.getId());
        headphones.setCategoryId(electronics.getId());
        productRepository.save(headphones);

        // Fashion products với mô tả tiếng Việt
        Product shirt = new Product();
        shirt.setTitle("Áo sơ mi nam công sở - Cotton Cao Cấp");
        shirt.setQuantity(20);
        shirt.setDesc("Áo sơ mi chất liệu cotton 100% cao cấp, thiết kế lịch lãm, phù hợp cho môi trường công sở và các buổi gặp gỡ quan trọng");
        shirt.setPrice(new BigDecimal("500000"));
        shirt.setUserId(user2.getId());
        shirt.setCategoryId(fashion.getId());
        productRepository.save(shirt);

        Product dress = new Product();
        dress.setTitle("Váy dạ hội nữ - Thiết kế Haute Couture");
        dress.setQuantity(8);
        dress.setDesc("Váy dạ hội sang trọng với thiết kế độc đáo, chất liệu lụa cao cấp, phù hợp cho các sự kiện quan trọng và tiệc tối");
        dress.setPrice(new BigDecimal("2000000"));
        dress.setUserId(user3.getId());
        dress.setCategoryId(fashion.getId());
        productRepository.save(dress);

        // Books
        Product javaBook = new Product();
        javaBook.setTitle("Effective Java");
        javaBook.setQuantity(30);
        javaBook.setDesc("Sách lập trình Java nâng cao");
        javaBook.setPrice(new BigDecimal("350000"));
        javaBook.setUserId(user1.getId());
        javaBook.setCategoryId(books.getId());
        productRepository.save(javaBook);

        Product springBook = new Product();
        springBook.setTitle("Spring Boot in Action");
        springBook.setQuantity(25);
        springBook.setDesc("Hướng dẫn Spring Boot từ cơ bản đến nâng cao");
        springBook.setPrice(new BigDecimal("450000"));
        springBook.setUserId(user1.getId());
        springBook.setCategoryId(books.getId());
        productRepository.save(springBook);

        // Food products
        Product coffee = new Product();
        coffee.setTitle("Cà phê Arabica nguyên chất");
        coffee.setQuantity(50);
        coffee.setDesc("Cà phê rang xay từ hạt Arabica cao cấp");
        coffee.setPrice(new BigDecimal("200000"));
        coffee.setUserId(user2.getId());
        coffee.setCategoryId(food.getId());
        productRepository.save(coffee);

        Product tea = new Product();
        tea.setTitle("Trà Oolong Đài Loan");
        tea.setQuantity(40);
        tea.setDesc("Trà Oolong thượng hạng từ Đài Loan");
        tea.setPrice(new BigDecimal("150000"));
        tea.setUserId(user2.getId());
        tea.setCategoryId(food.getId());
        productRepository.save(tea);

        Product chocolate = new Product();
        chocolate.setTitle("Socola đen 85%");
        chocolate.setQuantity(60);
        chocolate.setDesc("Socola đen nguyên chất với 85% cacao");
        chocolate.setPrice(new BigDecimal("80000"));
        chocolate.setUserId(user2.getId());
        chocolate.setCategoryId(food.getId());
        productRepository.save(chocolate);

        System.out.println("✅ Sample data initialized successfully!");
        System.out.println("📊 Created:");
        System.out.println("   - 4 Categories");
        System.out.println("   - 3 Users");
        System.out.println("   - 10 Products");
        System.out.println("🔗 Many-to-many relationships established between Users and Categories");
    }
}
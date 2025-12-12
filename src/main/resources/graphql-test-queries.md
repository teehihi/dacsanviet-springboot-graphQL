# GraphQL Test Queries

## 🔍 QUERIES (Truy vấn dữ liệu)

### 1. Hiển thị tất cả sản phẩm sắp xếp theo giá từ thấp đến cao
```graphql
query {
  getProductsSortedByPrice {
    id
    title
    price
    quantity
    desc
    user {
      id
      fullname
      email
    }
    category {
      id
      name
    }
  }
}
```

### 2. Lấy tất cả sản phẩm của một danh mục (ví dụ: categoryId = 1)
```graphql
query {
  getProductsByCategory(categoryId: "1") {
    id
    title
    price
    quantity
    user {
      fullname
    }
    category {
      name
    }
  }
}
```

### 3. Lấy tất cả sản phẩm
```graphql
query {
  getAllProducts {
    id
    title
    price
    quantity
    desc
    user {
      id
      fullname
    }
    category {
      id
      name
    }
  }
}
```

### 4. Lấy một sản phẩm theo ID
```graphql
query {
  getProduct(id: "1") {
    id
    title
    price
    quantity
    desc
    user {
      fullname
      email
    }
    category {
      name
    }
  }
}
```

### 5. Lấy tất cả người dùng với danh mục và sản phẩm
```graphql
query {
  getAllUsers {
    id
    fullname
    email
    phone
    categories {
      id
      name
    }
    products {
      id
      title
      price
    }
  }
}
```

### 6. Lấy tất cả danh mục với người dùng và sản phẩm
```graphql
query {
  getAllCategories {
    id
    name
    images
    users {
      id
      fullname
    }
    products {
      id
      title
      price
    }
  }
}
```

## ✏️ MUTATIONS (Thay đổi dữ liệu)

### 1. Tạo danh mục mới
```graphql
mutation {
  createCategory(input: {
    name: "Đồ chơi"
    images: "https://example.com/toys.jpg"
  }) {
    id
    name
    images
  }
}
```

### 2. Tạo người dùng mới với danh mục quan tâm
```graphql
mutation {
  createUser(input: {
    fullname: "Phạm Thị Dung"
    email: "dung@example.com"
    password: "password123"
    phone: "0934567890"
    categoryIds: ["1", "2"]
  }) {
    id
    fullname
    email
    categories {
      name
    }
  }
}
```

### 3. Tạo sản phẩm mới
```graphql
mutation {
  createProduct(input: {
    title: "MacBook Pro M3"
    quantity: 5
    desc: "Laptop Apple mới nhất với chip M3"
    price: 45000000
    userId: "1"
    categoryId: "1"
  }) {
    id
    title
    price
    user {
      fullname
    }
    category {
      name
    }
  }
}
```

### 4. Cập nhật sản phẩm
```graphql
mutation {
  updateProduct(id: "1", input: {
    title: "Laptop Dell XPS 13 - Updated"
    quantity: 12
    desc: "Laptop cao cấp với hiệu năng mạnh mẽ - Phiên bản cập nhật"
    price: 24000000
    userId: "1"
    categoryId: "1"
  }) {
    id
    title
    price
    quantity
  }
}
```

### 5. Cập nhật người dùng
```graphql
mutation {
  updateUser(id: "1", input: {
    fullname: "Nguyễn Văn An - Updated"
    email: "an.updated@example.com"
    password: "newpassword123"
    phone: "0901234567"
    categoryIds: ["1", "3"]
  }) {
    id
    fullname
    email
    categories {
      name
    }
  }
}
```

### 6. Xóa sản phẩm
```graphql
mutation {
  deleteProduct(id: "10")
}
```

### 7. Xóa người dùng
```graphql
mutation {
  deleteUser(id: "4")
}
```

### 8. Xóa danh mục
```graphql
mutation {
  deleteCategory(id: "5")
}
```

## 🧪 Test Cases để kiểm tra mối quan hệ Many-to-Many

### Kiểm tra User có nhiều Categories
```graphql
query {
  getUser(id: "1") {
    id
    fullname
    categories {
      id
      name
    }
  }
}
```

### Kiểm tra Category có nhiều Users
```graphql
query {
  getCategory(id: "1") {
    id
    name
    users {
      id
      fullname
    }
  }
}
```

## 📝 Hướng dẫn sử dụng:

1. **Khởi động ứng dụng**: `./mvnw spring-boot:run`
2. **Truy cập GraphiQL**: `http://localhost:8088/graphiql`
3. **Copy và paste** các query/mutation ở trên vào GraphiQL
4. **Chạy từng query** để kiểm tra kết quả
5. **Kiểm tra database** để xem dữ liệu đã được tạo/cập nhật/xóa chưa

## ✅ Checklist hoàn thành:

- [x] GraphQL Schema với đầy đủ Query và Mutation
- [x] Mối quan hệ Many-to-Many giữa User và Category
- [x] Mối quan hệ One-to-Many giữa User-Product và Category-Product
- [x] CRUD operations cho tất cả entities
- [x] Query sắp xếp sản phẩm theo giá
- [x] Query lọc sản phẩm theo danh mục
- [x] Sample data initialization
- [x] Web interface với AJAX
- [x] GraphiQL interface enabled
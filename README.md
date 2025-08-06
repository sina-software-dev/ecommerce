# ERP System - Spring Boot Application

A comprehensive Enterprise Resource Planning (ERP) system built with Spring Boot, featuring user management, inventory management, and secure authentication.

## Features

### 🔐 Authentication & Authorization
- JWT-based authentication
- Role-based access control (RBAC)
- Method-level security with custom permissions
- Password encryption with BCrypt

### 👥 User Management
- User registration and login
- Role and permission management
- User profile management
- Department and position tracking

### 📦 Inventory Management
- Product catalog management
- Category hierarchy support
- Stock tracking and management
- Reorder level monitoring
- Barcode and SKU support

### 🏗️ Architecture
- Layered architecture (Controller → Service → Repository → Entity)
- RESTful API design
- Data Transfer Objects (DTOs) with validation
- MapStruct for entity-DTO mapping
- Global exception handling

## Technology Stack

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Security 6** with JWT
- **Spring Data JPA** with Hibernate
- **H2 Database** (in-memory for development)
- **MySQL** support (configurable)
- **MapStruct** for object mapping
- **Lombok** for reducing boilerplate
- **OpenAPI/Swagger** for API documentation
- **Gradle** for build management

## Getting Started

### Prerequisites
- Java 21 or higher
- Gradle 8.0 or higher

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd erp-system
```

2. Build the project:
```bash
./gradlew build
```

3. Run the application:
```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080/api`

### Default Credentials

**Admin User:**
- Username: `admin`
- Password: `admin123`
- Email: `admin@erp.com`

## API Documentation

Once the application is running, you can access:

- **Swagger UI**: `http://localhost:8080/api/swagger-ui.html`
- **OpenAPI Docs**: `http://localhost:8080/api/api-docs`
- **H2 Console**: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:erpdb`
  - Username: `sa`
  - Password: (empty)

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `GET /api/auth/me` - Get current user details

### User Management
- `GET /api/users` - Get all users (paginated)
- `POST /api/users` - Create new user
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user
- `GET /api/users/search?search=query` - Search users

### Inventory Management
- `GET /api/products` - Get all products (paginated)
- `POST /api/products` - Create new product
- `GET /api/products/{id}` - Get product by ID
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

## Database Schema

### Core Tables
- `users` - User information
- `roles` - System roles
- `permissions` - System permissions
- `user_roles` - User-role mapping
- `role_permissions` - Role-permission mapping
- `categories` - Product categories
- `products` - Product catalog
- `stock` - Inventory stock levels

## Security

### Authentication Flow
1. User logs in with username/password
2. System validates credentials
3. JWT token is generated and returned
4. Client includes token in `Authorization` header for subsequent requests
5. System validates token and extracts user information

### Authorization
- **Role-based**: Users are assigned roles (ADMIN, MANAGER, EMPLOYEE, VIEWER)
- **Permission-based**: Fine-grained permissions for specific resources and actions
- **Method-level**: `@PreAuthorize` annotations on controller methods

### Default Roles and Permissions

**ROLE_ADMIN**: All permissions
**ROLE_MANAGER**: Create, Read, Update permissions
**ROLE_EMPLOYEE**: Read permissions for most resources
**ROLE_VIEWER**: Read-only access

## Configuration

### Application Properties

The application can be configured via `application.yml`:

```yaml
app:
  jwt:
    secret: mySecretKey123456789012345678901234567890
    expiration: 86400000 # 24 hours

spring:
  datasource:
    url: jdbc:h2:mem:erpdb
    username: sa
    password: 
```

### Environment-specific Configuration

Create additional configuration files for different environments:
- `application-dev.yml` - Development
- `application-prod.yml` - Production
- `application-test.yml` - Testing

## Sample Data

The application comes with sample data including:
- Default admin user
- Basic roles and permissions
- Sample product categories
- Sample products with stock information

## Building for Production

1. Build the JAR file:
```bash
./gradlew bootJar
```

2. Run the JAR:
```bash
java -jar build/libs/erp-system-1.0.0-SNAPSHOT.jar
```

3. For production deployment, consider:
   - Using MySQL or PostgreSQL instead of H2
   - Configuring proper JWT secret
   - Setting up proper logging
   - Enabling HTTPS
   - Configuring CORS properly

## Development

### Adding New Modules

1. Create entity in `com.erp.{module}.entity`
2. Create DTOs in `com.erp.{module}.dto`
3. Create repository in `com.erp.{module}.repository`
4. Create mapper in `com.erp.{module}.mapper`
5. Create service interface and implementation
6. Create REST controller
7. Add appropriate permissions and roles

### Testing

Run tests with:
```bash
./gradlew test
```

## Contributing

1. Follow the existing code structure and naming conventions
2. Add appropriate validation to DTOs
3. Include proper error handling
4. Add security annotations where needed
5. Update documentation for new features

## Future Enhancements

- [ ] Customer Management module
- [ ] Supplier Management module
- [ ] Purchase Order management
- [ ] Sales Order management
- [ ] Financial Management (Invoicing, Payments)
- [ ] Reporting and Analytics
- [ ] File upload and management
- [ ] Email notifications
- [ ] Audit logging
- [ ] Multi-tenancy support

## License

This project is licensed under the MIT License.

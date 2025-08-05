# E-Commerce Application (Amazon Clone)

A full-stack e-commerce web application built with React and Node.js, featuring a modern Amazon-like interface and functionality.

## Features

### Core Features
- User authentication and authorization
- Product catalog with categories
- Advanced search and filtering
- Shopping cart functionality
- Secure checkout process
- Order management
- User profiles and order history

### Advanced Features
- Product reviews and ratings
- Wishlist functionality
- Admin dashboard for product management
- Responsive design for mobile and desktop
- Real-time inventory updates
- Payment integration ready

## Tech Stack

### Frontend
- React 18 with hooks
- React Router for navigation
- Axios for API calls
- CSS Modules for styling
- React Context for state management

### Backend
- Node.js with Express
- MongoDB for database
- JWT for authentication
- Bcrypt for password hashing
- Multer for file uploads
- CORS for cross-origin requests

## Getting Started

### Prerequisites
- Node.js (v16 or higher)
- MongoDB
- npm or yarn

### Installation

1. Clone the repository
2. Install backend dependencies:
   ```bash
   cd backend
   npm install
   ```

3. Install frontend dependencies:
   ```bash
   cd ../frontend
   npm install
   ```

4. Set up environment variables (see .env.example files)

5. Start the development servers:
   ```bash
   # Backend (from backend directory)
   npm run dev

   # Frontend (from frontend directory)
   npm start
   ```

### Environment Variables

Create `.env` files in both backend and frontend directories with the required environment variables.

## Project Structure

```
ecommerce-app/
├── backend/                 # Node.js backend
│   ├── controllers/        # Route controllers
│   ├── models/            # Database models
│   ├── routes/            # API routes
│   ├── middleware/        # Custom middleware
│   ├── utils/             # Utility functions
│   └── server.js          # Main server file
├── frontend/              # React frontend
│   ├── public/           # Static files
│   ├── src/              # Source code
│   │   ├── components/   # Reusable components
│   │   ├── pages/        # Page components
│   │   ├── context/      # React context
│   │   ├── utils/        # Utility functions
│   │   └── App.js        # Main App component
│   └── package.json
└── README.md
```

## API Endpoints

### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `GET /api/auth/profile` - Get user profile

### Products
- `GET /api/products` - Get all products
- `GET /api/products/:id` - Get product by ID
- `GET /api/products/category/:category` - Get products by category
- `POST /api/products/search` - Search products

### Cart & Orders
- `GET /api/cart` - Get user cart
- `POST /api/cart/add` - Add item to cart
- `PUT /api/cart/update` - Update cart item
- `DELETE /api/cart/remove` - Remove cart item
- `POST /api/orders` - Create order
- `GET /api/orders` - Get user orders

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.
const express = require('express');
const router = express.Router();
const {
  getProducts,
  getProductById,
  getProductsByCategory,
  searchProducts,
  getFeaturedProducts,
  getCategories,
  getBrands,
  createProductReview
} = require('../controllers/productController');
const { protect } = require('../middleware/auth');
const { validateReview } = require('../middleware/validation');

// @route   GET /api/products
router.get('/', getProducts);

// @route   GET /api/products/featured
router.get('/featured', getFeaturedProducts);

// @route   GET /api/products/categories
router.get('/categories', getCategories);

// @route   GET /api/products/brands
router.get('/brands', getBrands);

// @route   POST /api/products/search
router.post('/search', searchProducts);

// @route   GET /api/products/category/:category
router.get('/category/:category', getProductsByCategory);

// @route   GET /api/products/:id
router.get('/:id', getProductById);

// @route   POST /api/products/:id/reviews
router.post('/:id/reviews', protect, validateReview, createProductReview);

module.exports = router;
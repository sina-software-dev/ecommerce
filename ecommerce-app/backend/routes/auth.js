const express = require('express');
const router = express.Router();
const {
  registerUser,
  loginUser,
  getUserProfile,
  updateUserProfile,
  toggleWishlist
} = require('../controllers/authController');
const { protect } = require('../middleware/auth');
const { validateRegistration, validateLogin } = require('../middleware/validation');

// @route   POST /api/auth/register
router.post('/register', validateRegistration, registerUser);

// @route   POST /api/auth/login
router.post('/login', validateLogin, loginUser);

// @route   GET /api/auth/profile
router.get('/profile', protect, getUserProfile);

// @route   PUT /api/auth/profile
router.put('/profile', protect, updateUserProfile);

// @route   POST /api/auth/wishlist/:productId
router.post('/wishlist/:productId', protect, toggleWishlist);

module.exports = router;
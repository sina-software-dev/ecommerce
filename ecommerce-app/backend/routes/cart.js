const express = require('express');
const router = express.Router();
const {
  getCart,
  addToCart,
  updateCartItem,
  removeFromCart,
  clearCart
} = require('../controllers/cartController');
const { protect } = require('../middleware/auth');

// @route   GET /api/cart
router.get('/', protect, getCart);

// @route   POST /api/cart/add
router.post('/add', protect, addToCart);

// @route   PUT /api/cart/update
router.put('/update', protect, updateCartItem);

// @route   DELETE /api/cart/remove/:productId
router.delete('/remove/:productId', protect, removeFromCart);

// @route   DELETE /api/cart/clear
router.delete('/clear', protect, clearCart);

module.exports = router;
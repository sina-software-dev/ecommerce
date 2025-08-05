const express = require('express');
const router = express.Router();
const {
  createOrder,
  getOrderById,
  getMyOrders,
  updateOrderToPaid,
  updateOrderStatus,
  cancelOrder,
  getOrderStats
} = require('../controllers/orderController');
const { protect, admin } = require('../middleware/auth');
const { validateAddress } = require('../middleware/validation');

// @route   POST /api/orders
router.post('/', protect, createOrder);

// @route   GET /api/orders
router.get('/', protect, getMyOrders);

// @route   GET /api/orders/stats
router.get('/stats', protect, getOrderStats);

// @route   GET /api/orders/:id
router.get('/:id', protect, getOrderById);

// @route   PUT /api/orders/:id/pay
router.put('/:id/pay', protect, updateOrderToPaid);

// @route   PUT /api/orders/:id/status
router.put('/:id/status', protect, admin, updateOrderStatus);

// @route   PUT /api/orders/:id/cancel
router.put('/:id/cancel', protect, cancelOrder);

module.exports = router;
import React, { createContext, useContext, useReducer, useEffect } from 'react';
import axios from 'axios';
import toast from 'react-hot-toast';
import { useAuth } from './AuthContext';

const CartContext = createContext();

const initialState = {
  items: [],
  totalAmount: 0,
  totalItems: 0,
  isLoading: false,
};

const cartReducer = (state, action) => {
  switch (action.type) {
    case 'CART_LOADING':
      return {
        ...state,
        isLoading: true,
      };
    case 'CART_LOADED':
      return {
        ...state,
        isLoading: false,
        items: action.payload.items || [],
        totalAmount: action.payload.totalAmount || 0,
        totalItems: action.payload.totalItems || 0,
      };
    case 'CART_ERROR':
      return {
        ...state,
        isLoading: false,
      };
    case 'CLEAR_CART':
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

export const CartProvider = ({ children }) => {
  const [state, dispatch] = useReducer(cartReducer, initialState);
  const { isAuthenticated, token } = useAuth();

  // Load cart when user is authenticated
  useEffect(() => {
    if (isAuthenticated && token) {
      loadCart();
    } else {
      dispatch({ type: 'CLEAR_CART' });
    }
  }, [isAuthenticated, token]);

  const loadCart = async () => {
    if (!isAuthenticated) return;
    
    dispatch({ type: 'CART_LOADING' });
    try {
      const res = await axios.get('/api/cart');
      dispatch({ type: 'CART_LOADED', payload: res.data });
    } catch (error) {
      console.error('Load cart error:', error);
      dispatch({ type: 'CART_ERROR' });
    }
  };

  const addToCart = async (productId, quantity = 1) => {
    if (!isAuthenticated) {
      toast.error('Please login to add items to cart');
      return { success: false };
    }

    dispatch({ type: 'CART_LOADING' });
    try {
      const res = await axios.post('/api/cart/add', { productId, quantity });
      dispatch({ type: 'CART_LOADED', payload: res.data });
      toast.success('Item added to cart');
      return { success: true };
    } catch (error) {
      const message = error.response?.data?.message || 'Failed to add item to cart';
      dispatch({ type: 'CART_ERROR' });
      toast.error(message);
      return { success: false, message };
    }
  };

  const updateCartItem = async (productId, quantity) => {
    if (!isAuthenticated) return { success: false };

    dispatch({ type: 'CART_LOADING' });
    try {
      const res = await axios.put('/api/cart/update', { productId, quantity });
      dispatch({ type: 'CART_LOADED', payload: res.data });
      toast.success('Cart updated');
      return { success: true };
    } catch (error) {
      const message = error.response?.data?.message || 'Failed to update cart';
      dispatch({ type: 'CART_ERROR' });
      toast.error(message);
      return { success: false, message };
    }
  };

  const removeFromCart = async (productId) => {
    if (!isAuthenticated) return { success: false };

    dispatch({ type: 'CART_LOADING' });
    try {
      const res = await axios.delete(`/api/cart/remove/${productId}`);
      dispatch({ type: 'CART_LOADED', payload: res.data });
      toast.success('Item removed from cart');
      return { success: true };
    } catch (error) {
      const message = error.response?.data?.message || 'Failed to remove item from cart';
      dispatch({ type: 'CART_ERROR' });
      toast.error(message);
      return { success: false, message };
    }
  };

  const clearCart = async () => {
    if (!isAuthenticated) return { success: false };

    dispatch({ type: 'CART_LOADING' });
    try {
      await axios.delete('/api/cart/clear');
      dispatch({ type: 'CLEAR_CART' });
      toast.success('Cart cleared');
      return { success: true };
    } catch (error) {
      const message = error.response?.data?.message || 'Failed to clear cart';
      dispatch({ type: 'CART_ERROR' });
      toast.error(message);
      return { success: false, message };
    }
  };

  const getCartItemCount = (productId) => {
    const item = state.items.find(item => item.product._id === productId);
    return item ? item.quantity : 0;
  };

  const isInCart = (productId) => {
    return state.items.some(item => item.product._id === productId);
  };

  const value = {
    ...state,
    addToCart,
    updateCartItem,
    removeFromCart,
    clearCart,
    loadCart,
    getCartItemCount,
    isInCart,
  };

  return <CartContext.Provider value={value}>{children}</CartContext.Provider>;
};

export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error('useCart must be used within a CartProvider');
  }
  return context;
};
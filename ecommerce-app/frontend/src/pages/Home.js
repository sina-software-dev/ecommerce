import React from 'react';
import { Link } from 'react-router-dom';
import { useQuery } from 'react-query';
import { ArrowRight, Star, ShoppingCart, Heart } from 'lucide-react';
import axios from 'axios';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';

const Home = () => {
  const { addToCart } = useCart();
  const { toggleWishlist, user } = useAuth();

  // Fetch featured products
  const { data: featuredProducts, isLoading } = useQuery(
    'featuredProducts',
    () => axios.get('/api/products/featured').then(res => res.data),
    {
      staleTime: 5 * 60 * 1000, // 5 minutes
    }
  );

  const handleAddToCart = async (productId) => {
    await addToCart(productId, 1);
  };

  const handleToggleWishlist = async (productId) => {
    await toggleWishlist(productId);
  };

  const formatPrice = (price) => {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD'
    }).format(price);
  };

  const renderStars = (rating) => {
    return Array.from({ length: 5 }, (_, i) => (
      <Star
        key={i}
        size={16}
        className={i < Math.floor(rating) ? 'text-yellow-400 fill-current' : 'text-gray-300'}
      />
    ));
  };

  return (
    <div>
      {/* Hero Section */}
      <section className="hero-section">
        <div className="container text-center">
          <h1 className="text-4xl md:text-6xl font-bold mb-6">
            Welcome to E-Store
          </h1>
          <p className="text-xl md:text-2xl mb-8 opacity-90">
            Discover amazing products at unbeatable prices
          </p>
          <Link to="/products" className="btn btn-lg bg-white text-blue-600 hover:bg-gray-100">
            Shop Now
            <ArrowRight className="ml-2" size={20} />
          </Link>
        </div>
      </section>

      {/* Categories Section */}
      <section className="py-16">
        <div className="container">
          <h2 className="text-3xl font-bold text-center mb-12">Shop by Category</h2>
          <div className="grid grid-cols-2 md:grid-cols-4 gap-6">
            {[
              { name: 'Electronics', image: '📱', color: 'bg-blue-100' },
              { name: 'Clothing', image: '👕', color: 'bg-purple-100' },
              { name: 'Books', image: '📚', color: 'bg-green-100' },
              { name: 'Home & Garden', image: '🏠', color: 'bg-yellow-100' },
            ].map((category) => (
              <Link
                key={category.name}
                to={`/category/${category.name}`}
                className={`${category.color} p-8 rounded-xl text-center hover:scale-105 transition-transform`}
              >
                <div className="text-4xl mb-4">{category.image}</div>
                <h3 className="text-lg font-semibold">{category.name}</h3>
              </Link>
            ))}
          </div>
        </div>
      </section>

      {/* Featured Products */}
      <section className="py-16 bg-gray-50">
        <div className="container">
          <div className="flex justify-between items-center mb-12">
            <h2 className="text-3xl font-bold">Featured Products</h2>
            <Link to="/products" className="btn btn-outline">
              View All
            </Link>
          </div>

          {isLoading ? (
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
              {Array.from({ length: 8 }, (_, i) => (
                <div key={i} className="bg-white rounded-lg p-4 animate-pulse">
                  <div className="bg-gray-200 h-48 rounded mb-4"></div>
                  <div className="bg-gray-200 h-4 rounded mb-2"></div>
                  <div className="bg-gray-200 h-4 rounded w-2/3"></div>
                </div>
              ))}
            </div>
          ) : (
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
              {featuredProducts?.map((product) => (
                <div key={product._id} className="product-card">
                  <div className="relative">
                    <img
                      src={product.images[0]?.url || 'https://via.placeholder.com/300x200?text=No+Image'}
                      alt={product.name}
                      className="product-image"
                    />
                    <button
                      onClick={() => handleToggleWishlist(product._id)}
                      className="absolute top-2 right-2 p-2 bg-white rounded-full shadow-md hover:bg-gray-50"
                    >
                      <Heart
                        size={16}
                        className={
                          user?.wishlist?.includes(product._id)
                            ? 'text-red-500 fill-current'
                            : 'text-gray-400'
                        }
                      />
                    </button>
                  </div>
                  
                  <div className="p-4">
                    <h3 className="font-semibold mb-2 truncate">{product.name}</h3>
                    <div className="flex items-center mb-2">
                      <div className="flex">{renderStars(product.rating)}</div>
                      <span className="text-sm text-gray-500 ml-1">
                        ({product.numReviews})
                      </span>
                    </div>
                    <div className="flex items-center justify-between mb-4">
                      <div>
                        <span className="text-lg font-bold text-blue-600">
                          {formatPrice(product.price)}
                        </span>
                        {product.originalPrice > product.price && (
                          <span className="text-sm text-gray-500 line-through ml-2">
                            {formatPrice(product.originalPrice)}
                          </span>
                        )}
                      </div>
                      {product.stock > 0 ? (
                        <span className="badge badge-success">In Stock</span>
                      ) : (
                        <span className="badge badge-danger">Out of Stock</span>
                      )}
                    </div>
                    <div className="flex gap-2">
                      <Link
                        to={`/products/${product._id}`}
                        className="btn btn-outline btn-sm flex-1"
                      >
                        View Details
                      </Link>
                      <button
                        onClick={() => handleAddToCart(product._id)}
                        disabled={product.stock === 0}
                        className="btn btn-primary btn-sm"
                      >
                        <ShoppingCart size={16} />
                      </button>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      </section>

      {/* Features Section */}
      <section className="py-16">
        <div className="container">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div className="text-center">
              <div className="bg-blue-100 rounded-full w-16 h-16 flex items-center justify-center mx-auto mb-4">
                <span className="text-2xl">🚚</span>
              </div>
              <h3 className="text-xl font-semibold mb-2">Free Shipping</h3>
              <p className="text-gray-600">
                Free shipping on orders over $50. Fast and reliable delivery.
              </p>
            </div>
            <div className="text-center">
              <div className="bg-green-100 rounded-full w-16 h-16 flex items-center justify-center mx-auto mb-4">
                <span className="text-2xl">🔒</span>
              </div>
              <h3 className="text-xl font-semibold mb-2">Secure Payment</h3>
              <p className="text-gray-600">
                Your payment information is safe and secure with us.
              </p>
            </div>
            <div className="text-center">
              <div className="bg-purple-100 rounded-full w-16 h-16 flex items-center justify-center mx-auto mb-4">
                <span className="text-2xl">↩️</span>
              </div>
              <h3 className="text-xl font-semibold mb-2">Easy Returns</h3>
              <p className="text-gray-600">
                30-day return policy. No questions asked.
              </p>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Home;
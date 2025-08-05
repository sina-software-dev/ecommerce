const Product = require('../models/Product');

// @desc    Get all products with filtering and pagination
// @route   GET /api/products
// @access  Public
const getProducts = async (req, res) => {
  try {
    const pageSize = Number(req.query.pageSize) || 12;
    const page = Number(req.query.pageNumber) || 1;
    
    const keyword = req.query.keyword ? {
      $or: [
        { name: { $regex: req.query.keyword, $options: 'i' } },
        { description: { $regex: req.query.keyword, $options: 'i' } },
        { brand: { $regex: req.query.keyword, $options: 'i' } },
        { tags: { $in: [new RegExp(req.query.keyword, 'i')] } }
      ]
    } : {};

    const category = req.query.category ? { category: req.query.category } : {};
    const brand = req.query.brand ? { brand: req.query.brand } : {};
    const priceRange = req.query.minPrice || req.query.maxPrice ? {
      price: {
        ...(req.query.minPrice && { $gte: Number(req.query.minPrice) }),
        ...(req.query.maxPrice && { $lte: Number(req.query.maxPrice) })
      }
    } : {};

    const filter = {
      isActive: true,
      ...keyword,
      ...category,
      ...brand,
      ...priceRange
    };

    // Sorting
    let sort = {};
    switch (req.query.sortBy) {
      case 'price_asc':
        sort = { price: 1 };
        break;
      case 'price_desc':
        sort = { price: -1 };
        break;
      case 'rating':
        sort = { rating: -1, numReviews: -1 };
        break;
      case 'newest':
        sort = { createdAt: -1 };
        break;
      case 'name':
        sort = { name: 1 };
        break;
      default:
        sort = { isFeatured: -1, createdAt: -1 };
    }

    const count = await Product.countDocuments(filter);
    const products = await Product.find(filter)
      .sort(sort)
      .limit(pageSize)
      .skip(pageSize * (page - 1))
      .select('-reviews');

    res.json({
      products,
      page,
      pages: Math.ceil(count / pageSize),
      total: count,
      hasMore: page < Math.ceil(count / pageSize)
    });
  } catch (error) {
    console.error('Get products error:', error);
    res.status(500).json({ message: 'Server error fetching products' });
  }
};

// @desc    Get single product
// @route   GET /api/products/:id
// @access  Public
const getProductById = async (req, res) => {
  try {
    const product = await Product.findById(req.params.id)
      .populate('reviews.user', 'name avatar');

    if (product) {
      res.json(product);
    } else {
      res.status(404).json({ message: 'Product not found' });
    }
  } catch (error) {
    console.error('Get product error:', error);
    res.status(500).json({ message: 'Server error fetching product' });
  }
};

// @desc    Get products by category
// @route   GET /api/products/category/:category
// @access  Public
const getProductsByCategory = async (req, res) => {
  try {
    const pageSize = Number(req.query.pageSize) || 12;
    const page = Number(req.query.pageNumber) || 1;

    const filter = {
      category: req.params.category,
      isActive: true
    };

    const count = await Product.countDocuments(filter);
    const products = await Product.find(filter)
      .sort({ isFeatured: -1, createdAt: -1 })
      .limit(pageSize)
      .skip(pageSize * (page - 1))
      .select('-reviews');

    res.json({
      products,
      page,
      pages: Math.ceil(count / pageSize),
      total: count,
      category: req.params.category
    });
  } catch (error) {
    console.error('Get products by category error:', error);
    res.status(500).json({ message: 'Server error fetching products by category' });
  }
};

// @desc    Search products
// @route   POST /api/products/search
// @access  Public
const searchProducts = async (req, res) => {
  try {
    const { query, filters, sort, page = 1, limit = 12 } = req.body;

    let searchFilter = { isActive: true };

    if (query) {
      searchFilter.$text = { $search: query };
    }

    if (filters) {
      if (filters.category) searchFilter.category = filters.category;
      if (filters.brand) searchFilter.brand = filters.brand;
      if (filters.minPrice || filters.maxPrice) {
        searchFilter.price = {
          ...(filters.minPrice && { $gte: filters.minPrice }),
          ...(filters.maxPrice && { $lte: filters.maxPrice })
        };
      }
      if (filters.rating) searchFilter.rating = { $gte: filters.rating };
    }

    let sortOption = { score: { $meta: 'textScore' } };
    if (sort) {
      switch (sort) {
        case 'price_asc':
          sortOption = { price: 1 };
          break;
        case 'price_desc':
          sortOption = { price: -1 };
          break;
        case 'rating':
          sortOption = { rating: -1 };
          break;
      }
    }

    const skip = (page - 1) * limit;
    const products = await Product.find(searchFilter)
      .sort(sortOption)
      .skip(skip)
      .limit(limit)
      .select('-reviews');

    const total = await Product.countDocuments(searchFilter);

    res.json({
      products,
      total,
      page,
      pages: Math.ceil(total / limit)
    });
  } catch (error) {
    console.error('Search products error:', error);
    res.status(500).json({ message: 'Server error searching products' });
  }
};

// @desc    Get featured products
// @route   GET /api/products/featured
// @access  Public
const getFeaturedProducts = async (req, res) => {
  try {
    const products = await Product.find({ 
      isFeatured: true, 
      isActive: true 
    })
    .sort({ createdAt: -1 })
    .limit(8)
    .select('-reviews');

    res.json(products);
  } catch (error) {
    console.error('Get featured products error:', error);
    res.status(500).json({ message: 'Server error fetching featured products' });
  }
};

// @desc    Get product categories
// @route   GET /api/products/categories
// @access  Public
const getCategories = async (req, res) => {
  try {
    const categories = await Product.distinct('category', { isActive: true });
    res.json(categories);
  } catch (error) {
    console.error('Get categories error:', error);
    res.status(500).json({ message: 'Server error fetching categories' });
  }
};

// @desc    Get product brands
// @route   GET /api/products/brands
// @access  Public
const getBrands = async (req, res) => {
  try {
    const brands = await Product.distinct('brand', { isActive: true });
    res.json(brands);
  } catch (error) {
    console.error('Get brands error:', error);
    res.status(500).json({ message: 'Server error fetching brands' });
  }
};

// @desc    Create a product review
// @route   POST /api/products/:id/reviews
// @access  Private
const createProductReview = async (req, res) => {
  try {
    const { rating, comment } = req.body;

    const product = await Product.findById(req.params.id);

    if (product) {
      const alreadyReviewed = product.reviews.find(
        (review) => review.user.toString() === req.user._id.toString()
      );

      if (alreadyReviewed) {
        return res.status(400).json({ message: 'Product already reviewed' });
      }

      const review = {
        name: req.user.name,
        rating: Number(rating),
        comment,
        user: req.user._id,
      };

      product.reviews.push(review);
      product.calculateAverageRating();

      await product.save();
      res.status(201).json({ message: 'Review added successfully' });
    } else {
      res.status(404).json({ message: 'Product not found' });
    }
  } catch (error) {
    console.error('Create review error:', error);
    res.status(500).json({ message: 'Server error creating review' });
  }
};

module.exports = {
  getProducts,
  getProductById,
  getProductsByCategory,
  searchProducts,
  getFeaturedProducts,
  getCategories,
  getBrands,
  createProductReview
};
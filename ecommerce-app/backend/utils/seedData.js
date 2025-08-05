const mongoose = require('mongoose');
const User = require('../models/User');
const Product = require('../models/Product');
require('dotenv').config();

// Sample products data
const sampleProducts = [
  {
    name: "iPhone 14 Pro",
    description: "The most Pro iPhone ever. Featuring the Dynamic Island, Always-On display, and the most advanced camera system ever on iPhone.",
    price: 999,
    originalPrice: 1099,
    discount: 9,
    category: "Electronics",
    subcategory: "Smartphones",
    brand: "Apple",
    images: [
      { url: "https://via.placeholder.com/400x300/007ACC/white?text=iPhone+14+Pro", alt: "iPhone 14 Pro" }
    ],
    stock: 50,
    sku: "IPHONE14PRO001",
    specifications: {
      "Display": "6.1-inch Super Retina XDR",
      "Chip": "A16 Bionic",
      "Camera": "48MP Main camera",
      "Storage": "128GB",
      "Color": "Deep Purple"
    },
    features: [
      "Dynamic Island",
      "Always-On display",
      "A16 Bionic chip",
      "Pro camera system",
      "Crash Detection"
    ],
    isActive: true,
    isFeatured: true,
    tags: ["smartphone", "apple", "flagship", "camera"],
    weight: 0.46,
    dimensions: { length: 14.75, width: 7.15, height: 0.79 },
    shippingInfo: { freeShipping: true, shippingCost: 0, estimatedDays: 2 }
  },
  {
    name: "MacBook Air M2",
    description: "Supercharged by M2 chip. The incredibly thin and light MacBook Air is now more powerful than ever.",
    price: 1199,
    originalPrice: 1299,
    discount: 8,
    category: "Electronics",
    subcategory: "Laptops",
    brand: "Apple",
    images: [
      { url: "https://via.placeholder.com/400x300/007ACC/white?text=MacBook+Air+M2", alt: "MacBook Air M2" }
    ],
    stock: 30,
    sku: "MACBOOKAIRM2001",
    specifications: {
      "Chip": "Apple M2",
      "Memory": "8GB unified memory",
      "Storage": "256GB SSD",
      "Display": "13.6-inch Liquid Retina",
      "Color": "Midnight"
    },
    features: [
      "M2 chip with 8-core CPU",
      "8-core GPU",
      "16-core Neural Engine",
      "13.6-inch Liquid Retina display",
      "1080p FaceTime HD camera"
    ],
    isActive: true,
    isFeatured: true,
    tags: ["laptop", "apple", "m2", "ultrabook"],
    weight: 1.24,
    dimensions: { length: 30.41, width: 21.5, height: 1.13 },
    shippingInfo: { freeShipping: true, shippingCost: 0, estimatedDays: 3 }
  },
  {
    name: "Nike Air Max 270",
    description: "The Nike Air Max 270 delivers visible Air Max cushioning and all-day comfort.",
    price: 150,
    originalPrice: 180,
    discount: 17,
    category: "Clothing",
    subcategory: "Shoes",
    brand: "Nike",
    images: [
      { url: "https://via.placeholder.com/400x300/FF6B6B/white?text=Nike+Air+Max+270", alt: "Nike Air Max 270" }
    ],
    stock: 75,
    sku: "NIKEAIRMAX270001",
    specifications: {
      "Size": "US 10",
      "Color": "Black/White",
      "Material": "Mesh and synthetic upper",
      "Sole": "Rubber"
    },
    features: [
      "Max Air unit in heel",
      "Mesh upper for breathability",
      "Foam midsole",
      "Rubber outsole"
    ],
    isActive: true,
    isFeatured: true,
    tags: ["shoes", "nike", "sports", "comfort"],
    weight: 0.65,
    dimensions: { length: 32, width: 12, height: 11 },
    shippingInfo: { freeShipping: true, shippingCost: 0, estimatedDays: 3 }
  },
  {
    name: "Samsung 55\" 4K Smart TV",
    description: "Crystal UHD 4K Smart TV with stunning picture quality and smart features.",
    price: 549,
    originalPrice: 699,
    discount: 21,
    category: "Electronics",
    subcategory: "TVs",
    brand: "Samsung",
    images: [
      { url: "https://via.placeholder.com/400x300/4A90E2/white?text=Samsung+55%22+4K+TV", alt: "Samsung 55 inch 4K TV" }
    ],
    stock: 25,
    sku: "SAMSUNG55TV001",
    specifications: {
      "Screen Size": "55 inches",
      "Resolution": "4K UHD (3840 x 2160)",
      "HDR": "HDR10+",
      "Smart Platform": "Tizen OS",
      "Connectivity": "3 HDMI, 2 USB"
    },
    features: [
      "Crystal UHD 4K resolution",
      "HDR10+ support",
      "Smart TV with apps",
      "Voice remote",
      "Multiple connectivity options"
    ],
    isActive: true,
    isFeatured: true,
    tags: ["tv", "samsung", "4k", "smart", "entertainment"],
    weight: 15.5,
    dimensions: { length: 123.1, width: 70.7, height: 5.9 },
    shippingInfo: { freeShipping: true, shippingCost: 0, estimatedDays: 5 }
  },
  {
    name: "The Psychology of Money",
    description: "Timeless lessons on wealth, greed, and happiness by Morgan Housel.",
    price: 16.99,
    originalPrice: 19.99,
    discount: 15,
    category: "Books",
    subcategory: "Finance",
    brand: "Harriman House",
    images: [
      { url: "https://via.placeholder.com/400x300/8E44AD/white?text=Psychology+of+Money", alt: "The Psychology of Money book" }
    ],
    stock: 100,
    sku: "PSYCHMONEY001",
    specifications: {
      "Author": "Morgan Housel",
      "Pages": "256",
      "Format": "Paperback",
      "Language": "English",
      "Publisher": "Harriman House"
    },
    features: [
      "New York Times bestseller",
      "Personal finance insights",
      "Behavioral psychology",
      "Easy to read format"
    ],
    isActive: true,
    isFeatured: false,
    tags: ["book", "finance", "psychology", "bestseller"],
    weight: 0.27,
    dimensions: { length: 19.8, width: 12.9, height: 1.8 },
    shippingInfo: { freeShipping: false, shippingCost: 4.99, estimatedDays: 5 }
  },
  {
    name: "Instant Pot Duo 7-in-1",
    description: "Electric pressure cooker, slow cooker, rice cooker, steamer, sauté, yogurt maker, and warmer.",
    price: 79.95,
    originalPrice: 99.95,
    discount: 20,
    category: "Home & Garden",
    subcategory: "Kitchen",
    brand: "Instant Pot",
    images: [
      { url: "https://via.placeholder.com/400x300/E67E22/white?text=Instant+Pot+Duo", alt: "Instant Pot Duo 7-in-1" }
    ],
    stock: 40,
    sku: "INSTANTPOTDUO001",
    specifications: {
      "Capacity": "6 Quart",
      "Functions": "7-in-1 Multi-Use",
      "Material": "Stainless Steel",
      "Power": "1000W",
      "Warranty": "1 Year"
    },
    features: [
      "7 appliances in 1",
      "14 smart programs",
      "Quick one-touch cooking",
      "70% faster cooking",
      "Safe and convenient"
    ],
    isActive: true,
    isFeatured: true,
    tags: ["kitchen", "cooking", "pressure cooker", "appliance"],
    weight: 5.7,
    dimensions: { length: 33, width: 31.5, height: 32.5 },
    shippingInfo: { freeShipping: true, shippingCost: 0, estimatedDays: 4 }
  }
];

// Demo user
const demoUser = {
  name: "Demo User",
  email: "demo@example.com",
  password: "demo123",
  role: "user",
  isVerified: true
};

const seedDatabase = async () => {
  try {
    // Connect to MongoDB
    await mongoose.connect(process.env.MONGODB_URI, {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    });
    console.log('Connected to MongoDB');

    // Clear existing data
    await User.deleteMany({});
    await Product.deleteMany({});
    console.log('Cleared existing data');

    // Create demo user
    const user = await User.create(demoUser);
    console.log('Created demo user:', user.email);

    // Create products
    const products = await Product.insertMany(sampleProducts);
    console.log(`Created ${products.length} sample products`);

    console.log('Database seeded successfully!');
    console.log('\nDemo credentials:');
    console.log('Email: demo@example.com');
    console.log('Password: demo123');
    
    process.exit(0);
  } catch (error) {
    console.error('Error seeding database:', error);
    process.exit(1);
  }
};

// Run the seed function
seedDatabase();
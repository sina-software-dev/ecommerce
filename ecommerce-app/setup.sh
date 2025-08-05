#!/bin/bash

echo "🚀 Setting up E-Commerce Application..."

# Check if Node.js is installed
if ! command -v node &> /dev/null; then
    echo "❌ Node.js is not installed. Please install Node.js (v16 or higher) and try again."
    exit 1
fi

# Check if MongoDB is running (optional - can use cloud MongoDB)
if ! command -v mongod &> /dev/null; then
    echo "⚠️  MongoDB is not installed locally. Make sure you have a MongoDB connection string in the .env file."
fi

echo "📦 Installing backend dependencies..."
cd backend
npm install

echo "📦 Installing frontend dependencies..."
cd ../frontend
npm install

echo "🌱 Setting up environment variables..."
cd ../backend
if [ ! -f .env ]; then
    cp .env.example .env
    echo "✅ Created .env file from .env.example"
    echo "⚠️  Please update the .env file with your MongoDB connection string and JWT secret"
else
    echo "✅ .env file already exists"
fi

echo "🎯 Setup complete!"
echo ""
echo "To start the application:"
echo "1. Start MongoDB (if running locally)"
echo "2. Backend: cd backend && npm run dev"
echo "3. Frontend: cd frontend && npm start"
echo "4. Seed database: cd backend && npm run seed"
echo ""
echo "Demo credentials:"
echo "Email: demo@example.com"
echo "Password: demo123"
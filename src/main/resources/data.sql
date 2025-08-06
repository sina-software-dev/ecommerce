-- Insert Permissions
INSERT INTO permissions (name, description, resource, action, is_active, created_at, updated_at) VALUES
('USER_CREATE', 'Create users', 'user', 'create', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('USER_READ', 'Read users', 'user', 'read', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('USER_UPDATE', 'Update users', 'user', 'update', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('USER_DELETE', 'Delete users', 'user', 'delete', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('PRODUCT_CREATE', 'Create products', 'product', 'create', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('PRODUCT_READ', 'Read products', 'product', 'read', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('PRODUCT_UPDATE', 'Update products', 'product', 'update', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('PRODUCT_DELETE', 'Delete products', 'product', 'delete', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INVENTORY_CREATE', 'Create inventory', 'inventory', 'create', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INVENTORY_READ', 'Read inventory', 'inventory', 'read', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INVENTORY_UPDATE', 'Update inventory', 'inventory', 'update', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INVENTORY_DELETE', 'Delete inventory', 'inventory', 'delete', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Roles
INSERT INTO roles (name, description, is_active, created_at, updated_at) VALUES
('ROLE_ADMIN', 'System Administrator', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ROLE_MANAGER', 'Manager', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ROLE_EMPLOYEE', 'Employee', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ROLE_VIEWER', 'Read-only User', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Assign permissions to roles
-- Admin gets all permissions
INSERT INTO role_permissions (role_id, permission_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4),
(1, 5), (1, 6), (1, 7), (1, 8),
(1, 9), (1, 10), (1, 11), (1, 12);

-- Manager gets create, read, update permissions
INSERT INTO role_permissions (role_id, permission_id) VALUES
(2, 1), (2, 2), (2, 3),
(2, 5), (2, 6), (2, 7),
(2, 9), (2, 10), (2, 11);

-- Employee gets create and read permissions
INSERT INTO role_permissions (role_id, permission_id) VALUES
(3, 2), (3, 6), (3, 10);

-- Viewer gets only read permissions
INSERT INTO role_permissions (role_id, permission_id) VALUES
(4, 2), (4, 6), (4, 10);

-- Insert default admin user (password: admin123)
INSERT INTO users (username, email, password, first_name, last_name, department, position, is_enabled, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_active, created_at, updated_at) VALUES
('admin', 'admin@erp.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRdvqoGOfGO8B3sJXBYu1.OqyX6', 'System', 'Administrator', 'IT', 'Administrator', true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Assign admin role to admin user
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);

-- Insert sample categories
INSERT INTO categories (name, description, code, is_active, created_at, updated_at) VALUES
('Electronics', 'Electronic products and devices', 'ELEC', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Office Supplies', 'Office supplies and stationery', 'OFFICE', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Furniture', 'Office and home furniture', 'FURN', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Software', 'Software licenses and applications', 'SOFT', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample products
INSERT INTO products (name, description, sku, barcode, category_id, unit_price, cost_price, unit_of_measure, reorder_level, max_stock_level, min_stock_level, tax_rate, is_active, created_at, updated_at) VALUES
('Laptop Computer', 'Business laptop computer', 'LAP001', '123456789001', 1, 899.99, 650.00, 'PIECE', 5, 50, 2, 0.08, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Office Chair', 'Ergonomic office chair', 'CHR001', '123456789002', 3, 199.99, 120.00, 'PIECE', 10, 100, 5, 0.08, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Printer Paper', 'A4 white printer paper', 'PAP001', '123456789003', 2, 29.99, 18.00, 'REAM', 20, 200, 10, 0.05, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Antivirus Software', 'Annual antivirus license', 'AV001', '123456789004', 4, 79.99, 45.00, 'LICENSE', 3, 25, 1, 0.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert stock data
INSERT INTO stock (product_id, quantity_on_hand, quantity_reserved, quantity_available, location, last_stock_count_date, last_movement_date, is_active, created_at, updated_at) VALUES
(1, 25, 2, 23, 'Warehouse A', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 45, 5, 40, 'Warehouse B', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 150, 10, 140, 'Storage Room', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 12, 1, 11, 'Digital Storage', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
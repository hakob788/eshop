package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();
    private CategoryManager categoryManager = new CategoryManager();

    public void saveProduct(Product product) {
        String sqlCommand = "INSERT INTO product(name,description,price,quantity,category_id) VALUES(?,?,?,?,?);";
        try (PreparedStatement ps = CONNECTION.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
            System.out.println("Product is added to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int id) {
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM  product WHERE id = " + id);
            if (resultSet.next()) {
                int productId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                int categoryId = resultSet.getInt("category_id");
                Category categoryById = categoryManager.getCategoryById(categoryId);
                return new Product(productId, name, description, price, quantity, categoryById);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void edit(Product product) {
        String sql = "UPDATE product SET name = ?, description = ? , price = ? , quantity = ?, category_id = ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getCategory().getId());
            preparedStatement.setInt(6, product.getId());
            preparedStatement.executeUpdate();
            System.out.println("Product is edited");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Product product) {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate("DELETE  FROM product WHERE id = " + product.getId());
            System.out.println("Product is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void countOfProducts() {
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT SUM(quantity) FROM product;");
            if (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void minPriceProduct() {
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MIN(price) FROM product;");
            if (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void maxPriceProduct() {
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(price) FROM product;");
            if (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void averagePrice() {
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT AVG(price) FROM product;");
            if (resultSet.next()) {
                System.out.println(resultSet.getDouble(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAll() {
        List<Product> categories = new ArrayList<>();
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product;");
            while (resultSet.next()) {
                int categoryId = resultSet.getInt(6);
                Category categoryById = categoryManager.getCategoryById(categoryId);
                Product product = new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getInt(5), categoryById);
                categories.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
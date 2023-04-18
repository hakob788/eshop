package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void saveCategory(Category category) {
        String sqlCommand = "INSERT INTO category(name) VALUES(?)";
        try (PreparedStatement ps = connection.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getInt(1));
            }
            System.out.println("Category is added to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Category category) {
        String sqlcommand = "UPDATE category Set name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlcommand)) {
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            statement.executeUpdate();
            System.out.println("category is edited");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getCategoryById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM  category WHERE id = " + id);
            if (resultSet.next()) {
                return new Category(resultSet.getInt("id"), resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCategory(Category category) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE  FROM category WHERE id = " + category.getId());
            System.out.println("Category is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category;");
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt(1), resultSet.getString(2));
                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}

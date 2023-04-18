import manager.CategoryManager;
import manager.ProductManager;
import model.Category;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class CategoryProductMain implements Commands {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final CategoryManager CATEGORY_MGR = new CategoryManager();
    public static final ProductManager PRODUCT_MGR = new ProductManager();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String command = SCANNER.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case EDIT_CATEGORY:
                    editCategory();
                    break;
                case DELETE_CATEGORY:
                    deleteCategory();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT:
                    editProduct();
                    break;
                case DELETE_PRODUCT:
                    deleteProduct();
                    break;
                case COUNT_OF_PRODUCTS:
                    PRODUCT_MGR.countOfProducts();
                    break;
                case MAX_PRICE:
                    PRODUCT_MGR.maxPriceProduct();
                    break;
                case MIN_PRICE:
                    PRODUCT_MGR.minPriceProduct();
                    break;
                case AVG:
                    PRODUCT_MGR.averagePrice();
                    break;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }

    public static void printCategories() {
        List<Category> all = CATEGORY_MGR.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
    }

    public static void printProducts() {
        List<Product> all = PRODUCT_MGR.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }

    public static void addCategory() {
        System.out.println("Please input category name");
        String nameStr = SCANNER.nextLine();
        Category category = new Category();
        category.setName(nameStr);
        CATEGORY_MGR.saveCategory(category);
    }

    public static void editCategory() {
        printCategories();
        System.out.println("Choose category id and new name");
        try {
            String[] data = SCANNER.nextLine().split(",");
            Category categoryById = CATEGORY_MGR.getCategoryById(Integer.parseInt(data[0]));
            if (categoryById == null) {
                System.out.println("Wrong id");
                return;
            }
            categoryById.setName(data[1]);
            CATEGORY_MGR.edit(categoryById);
        } catch (NumberFormatException e) {
            System.out.println("Please input id correctly");
        }
    }

    public static void deleteCategory() {
        printCategories();
        System.out.println("Choose category id ");
        try {
            int id = Integer.parseInt(SCANNER.nextLine());
            Category categoryById = CATEGORY_MGR.getCategoryById(id);
            if (categoryById == null) {
                System.out.println("Wrong id");
                return;
            }
            CATEGORY_MGR.deleteCategory(categoryById);
        } catch (NumberFormatException e) {
            System.out.println("Please input id correctly");
        }
    }

    public static void addProduct() {
        printCategories();
        System.out.println("Please input category id for product");
        try {
            int categoryId = Integer.parseInt(SCANNER.nextLine());
            Category categoryById = CATEGORY_MGR.getCategoryById(categoryId);
            if (categoryById == null) {
                System.out.println("Wrong category id");
                return;
            }
            System.out.println("Please input product name,description ,price,quantity");
            String[] dataStr = SCANNER.nextLine().split(",");
            Product product = new Product();
            product.setName(dataStr[0]);
            product.setDescription(dataStr[1]);
            product.setPrice(Integer.parseInt(dataStr[2]));
            product.setQuantity(Integer.parseInt(dataStr[3]));
            product.setCategory(categoryById);
            PRODUCT_MGR.saveProduct(product);
        } catch (NumberFormatException e) {
            System.out.println("Please input data  carefully ");
        }
    }

    public static void editProduct() {
        printProducts();
        System.out.println("Please input product id");
        int productId = Integer.parseInt(SCANNER.nextLine());
        Product product = PRODUCT_MGR.getProductById(productId);
        if (product == null) {
            System.out.println("Wrong product id");
            return;
        }
        System.out.println("Please input new name, description,price quantity");
        String[] dataStr = SCANNER.nextLine().split(",");
        System.out.println("Please choose new category id");
        printCategories();
        int categoryID = Integer.parseInt(SCANNER.nextLine());
        Category categoryById = CATEGORY_MGR.getCategoryById(categoryID);
        if (categoryById == null) {
            System.out.println("Wrong category id");
            return;
        }
        product.setName(dataStr[0]);
        product.setDescription(dataStr[1]);
        product.setPrice(Integer.parseInt(dataStr[2]));
        product.setQuantity(Integer.parseInt(dataStr[3]));
        product.setCategory(categoryById);
        PRODUCT_MGR.edit(product);
    }

    public static void deleteProduct() {
        printProducts();
        System.out.println("Please choose product's id");
        int id = Integer.parseInt(SCANNER.nextLine());
        Product product = PRODUCT_MGR.getProductById(id);
        if (product == null) {
            System.out.println("Wrong id");
            return;
        }
        PRODUCT_MGR.deleteProduct(product);
    }
}
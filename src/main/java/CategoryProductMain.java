import manager.CategoryManager;
import manager.ProductManager;
import model.Category;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class CategoryProductMain {

    private static Scanner scanner = new Scanner(System.in);

    private static final CategoryManager CATEGORY_MANAGER = new CategoryManager();
    private static final ProductManager PRODUCT_MANAGER = new ProductManager();

    public static void main(String[] args) {
        boolean isRun = true;

        while (isRun) {
            System.out.println("Please input 0 for Exit");
            System.out.println("Please input 1 for Add Category");
            System.out.println("Please input 2 for Edit Category By Id");
            System.out.println("Please input 3 for Delete Category By Id");
            System.out.println("Please input 4 for Add Product");
            System.out.println("Please input 5 for Edit Product By Id");
            System.out.println("Please input 6 for Delete Product By Id");
            System.out.println("Please input 7 for Print Sum of products");
            System.out.println("Please input 8 for Print Max of price product");
            System.out.println("Please input 9 for Print Min of price product");
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addCategory();
                    break;
                case "2":
                    editCategoryById();
                    break;
                case "3":
                    deleteCategoryById();
                    break;
                case "4":
                    addProduct();
                    break;
                case "5":
                    editProductById();
                    break;
                case "6":
                    deleteProductById();
                    break;
                case "7":
                    printSumOfProducts();
                    break;
                case "8":
                    printMaxOfPriceProduct();
                    break;
                case "9":
                    printMinOfPriceProduct();
                    break;
                case "10":
                    PrintAvgOfPriceProduct();
                    break;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }

    private static void PrintAvgOfPriceProduct() {
    }

    private static void printMinOfPriceProduct() {

    }

    private static void printMaxOfPriceProduct() {

    }

    private static void printSumOfProducts() {
        List<Product> all = PRODUCT_MANAGER.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }

    private static void deleteProductById() {
        List<Product> all = PRODUCT_MANAGER.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        System.out.println("please choose product id");
        int Id = Integer.parseInt(scanner.nextLine());
        PRODUCT_MANAGER.removeById(Id);
        System.out.println("product removed");
    }

    private static void editProductById() {

    }

    private static void addProduct() {
        List<Category> all = CATEGORY_MANAGER.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("Please choose category id");
        int id = Integer.parseInt(scanner.nextLine());
        Category category = CATEGORY_MANAGER.getById(id);
        if (category != null) {
            System.out.println("Please input product name,description,price,quantity,category");
            String productStr = scanner.nextLine();
            String[] productData = productStr.split(",");
            Product product = new Product();
            product.setCategory(category);
            product.setName(productData[0]);
            product.setDescription(productData[1]);
            product.setPrice(Double.valueOf(productData[2]));
            product.setQuantity(Integer.parseInt(productData[3]));
            PRODUCT_MANAGER.save(product);
        }
    }

    private static void deleteCategoryById() {
        List<Category> all = CATEGORY_MANAGER.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("Please choose category id");
        int id = Integer.parseInt(scanner.nextLine());
        CATEGORY_MANAGER.removeById(id);
        System.out.println("Category removed");
    }

    private static void editCategoryById() {
    }

    private static void addCategory() {
        System.out.println("Please input category name");
        String companyStr = scanner.nextLine();
        String[] companyData = companyStr.split(",");
        Category category = new Category();
        category.setName(companyData[0]);
        CATEGORY_MANAGER.save(category);
    }
}
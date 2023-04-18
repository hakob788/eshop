public interface Commands {
    String EXIT = "0";
    String ADD_CATEGORY = "1";
    String EDIT_CATEGORY = "2";
    String DELETE_CATEGORY = "3";
    String ADD_PRODUCT = "4";
    String EDIT_PRODUCT = "5";
    String DELETE_PRODUCT = "6";
    String COUNT_OF_PRODUCTS = "7";
    String MAX_PRICE = "8";
    String MIN_PRICE = "9";
    String AVG = "10";

    static void printCommands() {
        System.out.println("Please input " + EXIT + " for exit ");
        System.out.println("Please input " + ADD_CATEGORY + " for add category ");
        System.out.println("Please input " + EDIT_CATEGORY + " for edit category ");
        System.out.println("Please input " + DELETE_CATEGORY + " for delete category ");
        System.out.println("Please input " + ADD_PRODUCT + " for  add product ");
        System.out.println("Please input " + EDIT_PRODUCT + " for edit product ");
        System.out.println("Please input " + DELETE_PRODUCT + " for delete product ");
        System.out.println("Please input " + COUNT_OF_PRODUCTS + " for getting count of products");
        System.out.println("Please input " + MAX_PRICE + " to see maximum price of products ");
        System.out.println("Please input " + MIN_PRICE + " to see minimum price of products");
        System.out.println("Please input " + AVG + " to see average price ");

    }
}

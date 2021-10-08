import java.util.ArrayList;

public class Supermarket {
    //List of Users
    //List of products
    //List of products sold
    //totalmoneyearned
    //totalmonelspent
    private ArrayList<User> users;
    private ArrayList<Product> products;
    private ArrayList<Product> soldProducts;
    private static int totalMoneyEarned;
    private static int totalMoneySpent;

    public Supermarket() {
        this.users = new ArrayList<>();
        this.products = new ArrayList<>();
        this.soldProducts = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Product> getSoldProducts() {
        return soldProducts;
    }

    public static int getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public static int getTotalMoneySpent() {
        return totalMoneySpent;
    }

    public int getBalance() {
        return  totalMoneyEarned - totalMoneySpent;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        totalMoneySpent += product.getBuyingPrice() * product.getQuantity();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User findByEmail(String email) {
        for (User user: users) {
            if (user.getEmail().equals(email)){
                return user;
            }
        }

        return null;
    }

    public Product findProductByName(String productName) {
        for(Product product: products) {
            if(productName.equalsIgnoreCase(product.getProductName())) {
                return product;
            }
        }

        return null;
    }

    //Create a method that sells the product to the user in the supermarket
    public void sellProduct(Product product, User currentUser, int quantity) {
        //Check if Product available in supermarket
        if(product.getQuantity() != 0) {

            //check if quantity required is available for particular product
            if(quantity <= product.getQuantity()) {

                //Price to be  paid for product. quantity needed multiplied by price of product
                int priceToPay = product.getPrice() * quantity;

                //Check if user balance is up to price to be paid for item
                if(currentUser.getBalance() >= priceToPay) {
                    //code for selling product

                    //Debit user account
                    //User, amount
                    currentUser.payForProduct(priceToPay);

                    //Remove quantity sold from quantity available
                    //Quantity sold, quantity available, update quantity available
                    product.sellProduct(quantity);

                    //add sold product to list of sold product
                    //Variable that holds the product that was sold
                    Product soldProduct = new Product(product.getId(), product.getProductName(), priceToPay, product.getBuyingPrice(), quantity);
                    this.soldProducts.add(soldProduct);
                } else {
                    System.out.println("Not enough funds to buy product");
                }

            } else {
                System.out.println("Please select a lesser quantity");
            }

        } else {
            System.out.println("Product is sold out");
        }


    }

    public static void updateTotalMoneyEarned(int moneyEarned) {
        totalMoneyEarned += moneyEarned;
    }

    public static void updateTotalMoneySpent(int moneyEarned) {
        totalMoneyEarned += moneyEarned;
    }

}

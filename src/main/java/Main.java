import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    Scanner intScanner = new Scanner(System.in);
    Supermarket supermarket = new Supermarket();
    public static void main(String[] args) {
        Main main = new Main();

        main.addProductMenu();
        main.mainMenu();

    }

    void mainMenu() {
        String userType = "";

        do {
            System.out.println("Welcome to the supermarket, please choose your type of user: \n" +
                    "\t1. Buyer \n" +
                    "\t2. Sales Representative \n" +
                    "\t3. Owner \n" +
                    "Enter QUIT to exit program...");

            userType = scanner.nextLine();
            switch (userType){
                case "QUIT":
                    System.out.println("Exiting program...");
                    break;
                case "1":
                    buyerMenu();
                    break;
                case "2":
                    salesRepMenu();
                    break;
                case "3":
                    ownerMenu();
                    break;
                default:
                    System.out.println("Enter a valid input!!");
                    break;
            }
        } while(!userType.equals("QUIT"));
    }



    //Owner Menu to run for as long as user doesn't enter 0
    //Returns to main menu if user enters 0

    //Add product menu
    void addProductMenu() {
        System.out.println("Please add products to the supermarket before you begin.");

        addProduct();

        String userInput = "";

        do {
            System.out.println("Select '1' to add a new product or '2'  to start the program");
            userInput = scanner.nextLine();

            switch (userInput){
                case "1":
                    addProduct();
                    break;
                case "2":
                    System.out.println("Starting Application.");
                    break;
                default:
                    System.out.println("Please enter a valid input");
            }
        } while (!userInput.equals("2"));
    }

    void ownerMenu() {
        //Add buyers
        //Show all buyers
        //Get total earning
        //Get total spent
        //Get balance
        //Get sales history
        String ownerInput = "";

        do {
            System.out.println("Supermarket Admin Menu! What would like to do today? \n" +
                    "\t1. Add buyers\n" +
                    "\t2. Show all buyers\n" +
                    "\t3. Show Total Earning\n" +
                    "\t4. Show Total Spent\n" +
                    "\t5. Show Balance\n" +
                    "\t6. Show sales history\n" +
                    "Enter a number above or 0 to go back to previous menu."
            );

            ownerInput = scanner.nextLine();
            switch (ownerInput) {
                case "0":
                    System.out.println("Returning to previous menu.");
                    break;
                case "1":
                    registerBuyer();
                    break;
                case "2":
                    listBuyers();
                    break;
                case "3":
                    System.out.println("Total Money Earned By the Super Market: " + Supermarket.getTotalMoneyEarned());
                    break;
                case "4":
                    System.out.println("Total Money Spent By the Super Market: " + Supermarket.getTotalMoneySpent());
                    break;
                case "5":
                    System.out.println("Super Markets Balance is: " + supermarket.getBalance());
                    break;
                case "6":
                    showSalesHistory();
                    break;
                default:
                    System.out.println("Please enter a valid input. and Try Again!!");
                    break;
            }
        } while (!ownerInput.equals("0"));

    }

    void registerBuyer() {
        System.out.println("Register a new buyer...");

        System.out.println("Enter user id: ");
        int userId = intScanner.nextInt();

        System.out.println("Enter user name: ");
        String userName = scanner.nextLine();

        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        System.out.println("Enter Starting balance: ");
        int userBalance = intScanner.nextInt();

        User user = new User(userId, userName, password, email,userBalance);
        supermarket.addUser(user);
    }

    void listBuyers() {
        for (User user: supermarket.getUsers()) {
            System.out.println(user);
        }
    }

    void showSalesHistory() {
        System.out.println("History of sales for supermarket....");
        for (Product product: supermarket.getSoldProducts()) {
            System.out.println(product);
        }
    }

    void buyerMenu() {
        //Code that signs in a user

        User currentUser = signInUser();
        if (currentUser != null) {
            String buyerInput = "";

            do {
                System.out.println("Welcome to the the supermarket! Please choose operations: \n" +
                        "1. View all products.\n" +
                        "2. Buy Product." +
                        "3. Show user balance." +
                        "Enter 0 to go back to previous menu.");

                buyerInput = scanner.nextLine();
                switch (buyerInput) {
                    case "0":
                        System.out.println("Returning to previous menu...");
                        break;
                    case "1":
                        listProduct();
                        break;
                    case "2":
                        buyProduct(currentUser);
                        break;
                    case "3":
                        System.out.println("User balance is: " + currentUser.getBalance());
                        break;
                    default:
                        System.out.println("Please select a valid input.");
                        break;
                }
            } while (!buyerInput.equals("0"));
        } else {
            System.out.println("Invalid Email or Password!!");
            buyerMenu();
        }


    }

    User signInUser() {
        System.out.println("Enter your email");
        String email = scanner.nextLine();

        User currentUser = supermarket.findByEmail(email);

        System.out.println("Enter your password");
        String password = scanner.nextLine();

        if(currentUser != null) {
            if(currentUser.verifyPassword(password)) {
                return currentUser;
            }
        }

        return null;
    }

    void listProduct() {
        System.out.println("Products Available in the supermarket");
        for(Product product: supermarket.getProducts()) {
            System.out.println(product);
        }
    }

    //User, Product, quantity needed.
    void buyProduct(User currentUser){
        //Code for buying product
        listProduct();

        System.out.println("\nPlease enter the name of the product you would like to buy");
        String productName = scanner.nextLine();

        Product product = supermarket.findProductByName(productName);
        System.out.println("Please specify the quantity you would like:");
        int quantity = intScanner.nextInt();

        //handle purchase
        supermarket.sellProduct(product, currentUser, quantity);
        System.out.println(product.getProductName() + " sold to " + currentUser.getName());

    }

    void salesRepMenu() {


        String salesRepInput = "";

        do {
            System.out.println("Welcome to the sales representative menu. select an operation: " +
                    "1. Add Product to super market." +
                    "2. View All products." +
                    "Enter 0 to return to previous menu!");

            salesRepInput = scanner.nextLine();

            switch (salesRepInput){
                case "0":
                    System.out.println("Returning to previous menu.");
                    break;
                case "1":
                    addProduct();
                    break;
                case "2":
                    listProduct();
                    break;
                default:
                    System.out.println("Please enter a valid input.");
            }
        }while(!salesRepInput.equals("0"));

    }

    void addProduct() {
        System.out.println("Enter product Id: ");
        int productId = intScanner.nextInt();

        System.out.println("Enter Product Name: ");
        String productName = scanner.nextLine();

        System.out.println("Enter Product Price: ");
        int productPrice = intScanner.nextInt();

        System.out.println("Enter Product Buying Price: ");
        int productBuyingPrice = intScanner.nextInt();

        System.out.println("Enter Product Quantity: ");
        int productQuantity = intScanner.nextInt();

        Product product = new Product(productId, productName, productPrice, productBuyingPrice, productQuantity);

        supermarket.addProduct(product);
    }


}

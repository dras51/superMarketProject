public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private int balance;

    User (int id, String name, String password, String email, int balance){
        this.id = id;
        this.password = password;
        this.email= email;
        this.name= name;
        this.balance=balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    public void payForProduct(int amount) {
        this.balance -= amount;
        Supermarket.updateTotalMoneyEarned(amount);
    }

    public Boolean verifyPassword(String password) {
        if (password.equals(this.password)){
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "------User id: " + id + "-------\n" +
                "User Name: " + name +
                "\nEmail: " + email +
                "\nBalance: " + balance;
    }
}

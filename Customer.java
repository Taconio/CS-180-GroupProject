import java.util.ArrayList;

public class Customer {
    String userName;
    String password;
    ArrayList<Seller> listOfSellers;
    ArrayList<Customer> listOfCustomers;

    public ArrayList<ArrayList<String>> messages;
    public boolean hasContact;
    public Customer(String userName, String password) {
        this.userName = userName;
        this.password = password;
        listOfSellers = new ArrayList<>();

                String var10002;
                Customer var20;
                ArrayList var37;
                ArrayList var38;
                int var39;
                label510:
                do {
                    while(true) {
                        System.out.println("Welcome to the messaging System");
                        System.out.println("Are you a customer or seller? (1/2)");

                        int var7;
                        for(var7 = var1.nextInt(); var7 != 1 && var7 != 2; var7 = var1.nextInt()) {
                            System.out.println("Please enter a valid input");
                        }

                        String var11;
                        Iterator var12;
                        Iterator var14;
                        int var16;
                        String var18;
                        int var22;
                        String var23;
                        ArrayList var32;
                        if (var7 == 1) {
                            System.out.println("Do you already have an account? (Y/N)");
                            var1.nextLine();
                            var8 = var1.nextLine();
                            if (var8.equals("N") || var8.equals("n")) {
                                System.out.println("Please enter a username");
        listOfSellers.add(new Seller("Bob", "123"));
        listOfSellers.add(new Seller("William", "123"));
        listOfSellers.add(new Seller("Rachel", "123"));

        // needs this but program is unable to run with it. (has to do with initializing new customers in the customer constructor)
        /*listOfCustomers = new ArrayList<>();
        listOfCustomers.add(new Customer("Billy", "123"));
        listOfCustomers.add(new Customer("Max", "123"));
        listOfCustomers.add(new Customer("Noah", "123"));*/

        ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();
        // need to add this into messages arraylist for looping functionality (temporary fix)
        ArrayList<String> dummy = new ArrayList<>();
        dummy.add("13475857");
        messages.add(dummy);
        this.messages = messages;

        this.hasContact = false;

    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<Seller> getListOfSellers() {
        return listOfSellers;
    }
    public void viewListOfSellers() {
        for (int i = 0; i < listOfSellers.size(); i++) {
            System.out.println(i+1 + " " + listOfSellers.get(i).getName());
        }
    }
    public void setHasContact(boolean condition) {
        this.hasContact = condition;
    }

    public ArrayList<Customer> getListOfCustomers() {
        return listOfCustomers;
    }
    public void viewListOfCustomers() {
        for (int i = 0; i < listOfCustomers.size(); i++) {
            System.out.println(i+1 + " " + listOfCustomers.get(i).getUserName());
        }
    }
}

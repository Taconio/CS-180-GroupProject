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

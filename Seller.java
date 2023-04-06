import java.util.ArrayList;

public class Seller {
    String name;
    String password;

    ArrayList<Customer> listOfCustomers;
    ArrayList<Seller> listOfSellers;

    public boolean hasContact;

    public ArrayList<ArrayList<String>> messages;


    public Seller(String name, String password) {
        this.name = name;
        this.password = password;
        this.listOfCustomers = new ArrayList<>();
        this.listOfSellers = new ArrayList<>();

        // needs this but program is unable to run with it. (has to do with initializing new customers in the customer constructor)
        /*listOfCustomers.add(new Customer("Billy", "123"));
        listOfCustomers.add(new Customer("Max", "123"));
        listOfCustomers.add(new Customer("Noah", "123"));*/

        // same with this
        /*listOfSellers.add(new Seller("Bob", "123"));
        listOfSellers.add(new Seller("William", "123"));
        listOfSellers.add(new Seller("Rachel", "123"));*/

        this.hasContact = false;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
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

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // only creating a new customer option works (others options will run into issues that will be discussed soon)

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choiceOfAction = -1;
        boolean validSeller = false;
        boolean validCustomer = false;
        String userName;
        String password;

        while (true) {
            // starting display
            System.out.println("Welcome to the messaging System");
            System.out.println("Are you a customer or seller? (1/2)");
            int userChoice = scanner.nextInt();
            // checks for only a 1 or 2 response
            while (userChoice != 1 && userChoice != 2) {
                System.out.println("Please enter a valid input");
                userChoice = scanner.nextInt();
            }
            // =================== Customer ======================= //
            if (userChoice == 1) {
                System.out.println("Do you already have an account? (Y/N)" );
                scanner.nextLine();
                String haveAccount = scanner.nextLine();
                // ============ New Account ================== //
                if (haveAccount.equalsIgnoreCase("N")) {

                    System.out.println("Please enter a username");
                    userName = scanner.nextLine();
                    System.out.println("Please enter a password");
                    password = scanner.nextLine();
                    // creates new customer object with the given strings as parameters
                    Customer customer = new Customer(userName, password);
                    //customer.listOfCustomers.add(customer);


                    while (choiceOfAction != 2) {
                        System.out.println("Please enter your choice of action:");
                        System.out.println("1. Message sellers");
                        System.out.println("2. Log out ");
                        choiceOfAction = scanner.nextInt();
                        // ================ Message Functionality ============================ //
                        if (choiceOfAction == 1) {
                            System.out.println("Would you like to view list of sellers? (1) or search for sellers? (2)");
                            int messageType = scanner.nextInt();

                            // checks for only 1 or 2
                            while (messageType != 1 && messageType != 2) {
                                System.out.println("Please enter a valid number");
                                messageType = scanner.nextInt();
                            }
                            if (messageType == 1) {
                                System.out.println("Which seller would you like to message?");

                                // displays list of available seller.
                                customer.viewListOfSellers();
                                scanner.nextLine();
                                String choiceOfSeller = scanner.nextLine();

                                System.out.println(choiceOfSeller);
                                // loops through each available seller and makes sure the name matches a valid one
                                for (Seller seller: customer.getListOfSellers()) {
                                    if (seller.getName().equals(choiceOfSeller)) {
                                        validSeller = true;
                                    }
                                }
                                // if it does not match, prompts user to enter a valid one
                                while (!validSeller) {
                                    System.out.println("Please enter a valid seller name");
                                    choiceOfSeller = scanner.nextLine();
                                    for (Seller seller: customer.getListOfSellers())  {
                                        if (seller.getName().equals(choiceOfSeller)) {
                                            validSeller = true;
                                        }
                                    }
                                }
                                // sets to false for multiple loop uses
                                validSeller = false;


                                System.out.println("What is your choice of action with this seller?");
                                System.out.println("1. Send message");
                                System.out.println("2. Edit message");
                                System.out.println("3. Delete message");
                                System.out.println("4. Return back");
                                int choiceOfActionP2 = scanner.nextInt();

                                // ================ Send Message =========================== //
                                if (choiceOfActionP2 == 1) {
                                    // user input for message
                                    System.out.println("What would you like to send");
                                    scanner.nextLine();
                                    String messageToSend = scanner.nextLine();

                                    // goes through each available seller
                                    A : for (Seller seller: customer.getListOfSellers()) {
                                        // finds the correct seller that user wants to message
                                        if (seller.getName().equals(choiceOfSeller)) {
                                            // goes through the arraylist of arraylists
                                            for (int i = 0; i < customer.messages.size(); i++) {
                                                // finds the arraylist of messages which the first index matches the correct sellers name
                                                // assumes that there is already a text history between the users
                                                if (customer.messages.get(i).get(0).equals(seller.getName())) {
                                                    System.out.println("Second Round");
                                                    // adds the message into the array that has there text history already
                                                    customer.messages.get(i).add(customer.getUserName() + ": " + messageToSend);
                                                    // visual display on terminal (testing purposes)
                                                    System.out.println(customer.messages);
                                                    break A;
                                                }
                                                // if customer does not have text history and its we went through whole loop, create new message history with text and store it within the messages
                                                if (!customer.hasContact && i == customer.messages.size() - 1 ) {
                                                    System.out.println("first round!");
                                                    // where we store the written text
                                                    ArrayList<String> message = new ArrayList<>();
                                                    // first slot is the name of the recipient (sorting / finding purposes)
                                                    message.add(seller.getName());
                                                    message.add(customer.getUserName() + ": " + messageToSend);
                                                    // stores the string arraylist of text into an array list that stores  multiple arraylists of text (keeps history from each seller the user messgaes)
                                                    customer.messages.add(message);
                                                    // now customer has contact, no need to go through this initial condition
                                                    customer.setHasContact(true);
                                                    System.out.println(customer.messages);
                                                    break A;
                                                }
                                            }
                                        }
                                    }
                                    // sets it to false after sending message in case customer messages a new seller.
                                    customer.setHasContact(false);
                                    // ================ Send Message =========================== //

                                    // ================ Edit Message =========================== //
                                } else if (choiceOfActionP2 == 2) {
                                    // user input for message to edit
                                    System.out.println("Which message would you like to edit from this seller?");
                                    scanner.nextLine();
                                    String messageToEdit = scanner.nextLine();

                                    // goes through each seller
                                    for (Seller seller: customer.getListOfSellers()) {
                                        // finds correct seller that user wants to message
                                        if (seller.getName().equals(choiceOfSeller)) {
                                            // goes through each arraylist in the arraylist of arraylists
                                            for (int i = 0; i < customer.messages.size(); i++) {
                                                // finds the arraylist of messages which the first index matches the correct seller's name
                                                if (customer.messages.get(i).get(0).equals(seller.getName())) {
                                                    // loops through each text that was sent
                                                    for (int j = 1; j < customer.messages.get(i).size(); j++) {
                                                        // if target text was found, prompts user to type in replacement message
                                                        if (customer.messages.get(i).get(j).equals(customer.getUserName() + ": " + messageToEdit)) {
                                                            System.out.println("What would you like to change it too?");
                                                            String newMessage = scanner.nextLine();
                                                            // replaces the old String message with new one
                                                            customer.messages.get(i).set(customer.messages.get(i).indexOf(customer.getUserName() + ": " + messageToEdit),customer.getUserName() + ": "+ newMessage );
                                                            System.out.println("Edit Successfully");
                                                            // display the message data (test)
                                                            System.out.println(customer.messages.get(i));
                                                            // if no message is found and it comes to end of loop, display invalid message and breaks out of loop.
                                                        } else if (!(customer.messages.get(i).get(j).equals(customer.getUserName() + ": " + messageToEdit)) && j == customer.messages.get(i).size() - 1) {
                                                            System.out.println("Unable to find message");
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // ================ Edit Message =========================== //

                                    // ================ Delete Message =========================== //
                                } else if (choiceOfActionP2 == 3) {
                                    System.out.println("Which message would you like to delete from this seller?");
                                    scanner.nextLine();
                                    String messageToDelete = scanner.nextLine();

                                    // goes through each seller
                                    for (Seller seller: customer.getListOfSellers()) {
                                        // finds correct seller that user wants to message
                                        if (seller.getName().equals(choiceOfSeller)) {
                                            // goes through each arraylist in the arraylist of arraylists
                                            for (int i = 0; i < customer.messages.size(); i++) {
                                                // finds the arraylist of messages which the first index matches the correct seller's name
                                                if (customer.messages.get(i).get(0).equals(seller.getName())) {
                                                    // loops through each text that was sent
                                                    for (int j = 1; j < customer.messages.get(i).size(); j++) {
                                                        // if target text was found, removes that string from the specific arraylist of data
                                                        if (customer.messages.get(i).get(j).equals(customer.getUserName() + ": "+ messageToDelete)) {
                                                            customer.messages.get(i).remove(customer.getUserName() + ": "+ messageToDelete);
                                                            System.out.println("Delete Successfully");

                                                            System.out.println(customer.messages.get(i));

                                                            // if no message is found and it comes to end of loop, display invalid message and breaks out of loop.
                                                        } else if (!(customer.messages.get(i).get(j).equals(customer.getUserName() + ": "+ messageToDelete)) && j == customer.messages.get(i).size() - 1){
                                                            System.out.println("Unable to find message");
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                // ================ Delete Message =========================== //

                            }
                        }
                    }
                    // resets choice of action so it loops properly again
                    choiceOfAction = -1;
                }
                if (haveAccount.equalsIgnoreCase("Y"))) {
                    System.out.println("Please enter your username");
                    userName = scanner.nextLine();
                    System.out.println("Please enter your password");
                    password = scanner.nextLine();

                    Customer customer = new Customer("dummy", "123");
                    for (int ii = 0; ii < customer.getListOfCustomers().size(); ii++) {
                        if (customer.getListOfCustomers().get(ii).getUserName().equals(userName) && customer.getListOfCustomers().get(ii).getPassword().equals(password)) {
                            System.out.println("Login Success!");
                            customer = customer.getListOfCustomers().get(ii);

                            while (choiceOfAction != 2) {
                                System.out.println("Please enter your choice of action:");
                                System.out.println("1. Message sellers");
                                System.out.println("2. Log out ");
                                choiceOfAction = scanner.nextInt();
                                // ================ Message Functionality ============================ //
                                if (choiceOfAction == 1) {
                                    System.out.println("Would you like to view list of sellers? (1) or search for sellers? (2)");
                                    int messageType = scanner.nextInt();
                                    while (messageType != 1 && messageType != 2) {
                                        System.out.println("Please enter a valid number");
                                        messageType = scanner.nextInt();
                                    }
                                    if (messageType == 1) {
                                        System.out.println("Which seller would you like to message?");
                                        // viewListOfSellers() should print out a list of available sellers that customer and contact
                                        customer.viewListOfSellers();
                                        scanner.nextLine();
                                        String choiceOfSeller = scanner.nextLine();
                                        System.out.println(choiceOfSeller);
                                        for (Seller seller: customer.getListOfSellers()) {
                                            if (seller.getName().equals(choiceOfSeller)) {
                                                System.out.println("Matches");
                                                validSeller = true;
                                            }
                                        }
                                        while (!validSeller) {
                                            System.out.println("Please enter a valid seller name");
                                            choiceOfSeller = scanner.nextLine();
                                            for (Seller seller: customer.getListOfSellers())  {
                                                if (seller.getName().equals(choiceOfSeller)) {
                                                    validSeller = true;
                                                }
                                            }
                                        }
                                        validSeller = false;
                                        System.out.println("What is your choice of action with this customer?");
                                        System.out.println("1. Send message");
                                        System.out.println("2. Edit message");
                                        System.out.println("3. Delete message");
                                        System.out.println("4. Return back");
                                        int choiceOfActionP2 = scanner.nextInt();
                                        // ================ Send Message =========================== //
                                        if (choiceOfActionP2 == 1) {
                                            System.out.println("What would you like to send");
                                            scanner.nextLine();
                                            String messageToSend = scanner.nextLine();
                                            System.out.println("Messages : " + customer.messages);
                                            // getMessage should call an arrayList of Strings for adding purposes (data for storing text history)
                                            A : for (Seller seller: customer.getListOfSellers()) {
                                                if (seller.getName().equals(choiceOfSeller)) {
                                                    for (int i = 0; i < customer.messages.size(); i++) {

                                                        System.out.println("Seller: "+ seller.getName());
                                                        System.out.println("Me: "+ customer.messages.get(i).get(0));

                                                        if (customer.messages.get(i).get(0).equals(seller.getName())) {
                                                            System.out.println("Second Round");
                                                            customer.messages.get(i).add(customer.getUserName() + ": " + messageToSend);
                                                            System.out.println(customer.messages);
                                                            break A;
                                                        }

                                                        if (!customer.hasContact && i == customer.messages.size() - 1 ) {
                                                            System.out.println("first round!");
                                                            ArrayList<String> message = new ArrayList<>();
                                                            message.add(seller.getName());
                                                            message.add(customer.getUserName() + ": " + messageToSend);
                                                            customer.messages.add(message);
                                                            customer.setHasContact(true);
                                                            System.out.println(customer.messages);
                                                            break A;
                                                        }
                                                    }
                                                }
                                            }
                                            customer.setHasContact(false);
                                            // ================ Send Message =========================== //

                                            // ================ Edit Message =========================== //
                                        } else if (choiceOfActionP2 == 2) {
                                            System.out.println("Which message would you like to edit from this seller?");
                                            scanner.nextLine();
                                            String messageToEdit = scanner.nextLine();

                                            for (Seller seller: customer.getListOfSellers()) {
                                                if (seller.getName().equals(choiceOfSeller)) {
                                                    for (int i = 0; i < customer.messages.size(); i++) {
                                                        if (customer.messages.get(i).get(0).equals(seller.getName())) {
                                                            for (int j = 1; j < customer.messages.get(i).size(); j++) {
                                                                System.out.println(customer.messages.get(i).get(j));

                                                                if (customer.messages.get(i).get(j).equals(customer.getUserName() + ": " + messageToEdit)) {
                                                                    System.out.println("What would you like to change it too?");
                                                                    String newMessage = scanner.nextLine();
                                                                    customer.messages.get(i).set(customer.messages.get(i).indexOf(customer.getUserName() + ": " + messageToEdit),customer.getUserName() + ": "+ newMessage );
                                                                    System.out.println("Edit Successfully");
                                                                    System.out.println(customer.messages.get(i));
                                                                } else if (!(customer.messages.get(i).get(j).equals(customer.getUserName() + ": " + messageToEdit)) && j == customer.messages.get(i).size() - 1) {
                                                                    System.out.println("Unable to find message");
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            // ================ Edit Message =========================== //

                                            // ================ Delete Message =========================== //
                                        } else if (choiceOfActionP2 == 3) {
                                            System.out.println("Which message would you like to delete from this seller?");
                                            scanner.nextLine();
                                            String messageToDelete = scanner.nextLine();

                                            for (Seller seller: customer.getListOfSellers()) {
                                                if (seller.getName().equals(choiceOfSeller)) {
                                                    for (int i = 0; i < customer.messages.size(); i++) {
                                                        if (customer.messages.get(i).get(0).contains(seller.getName())) {
                                                            for (int j = 1; j < customer.messages.get(i).size(); j++) {
                                                                System.out.println(customer.messages.get(i).get(j));
                                                                if (customer.messages.get(i).get(j).equals(customer.getUserName() + ": "+ messageToDelete)) {
                                                                    customer.messages.get(i).remove(customer.getUserName() + ": "+ messageToDelete);

                                                                    System.out.println("Delete Successfully");
                                                                    for (int k = 1; j < customer.messages.get(i).size(); k++) {
                                                                        System.out.println(customer.messages.get(i).get(k));
                                                                    }
                                                                    System.out.println(customer.messages.get(i));
                                                                } else if (!(customer.messages.get(i).get(j).equals(customer.getUserName() + ": "+ messageToDelete)) && j == customer.messages.get(i).size() - 1){
                                                                    System.out.println("Unable to find message");
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        // ================ Delete Message =========================== //
                                    }
                                }
                            }
                        } else if (!(customer.getListOfCustomers().get(ii).getUserName().equals(userName) && customer.getListOfCustomers().get(ii).getPassword().equals(password)) && ii == customer.getListOfCustomers().size() - 1) {
                            System.out.println("Login failed");
                            break;
                        }
                    }
                }
                // =================== Seller ======================= //
            } else {
                System.out.println("Do you already have an account? (Y/N)" );
                scanner.nextLine();
                String newAccount = scanner.nextLine();
                if (newAccount.equalsIgnoreCase("N")) {

                    System.out.println("Please enter a username");
                    userName = scanner.nextLine();
                    System.out.println("Please enter a password");
                    password = scanner.nextLine();

                    Seller seller = new Seller(userName, password);
                    seller.listOfSellers.add(seller);

                    while (choiceOfAction != 2) {
                        System.out.println("Please enter your choice of action:");
                        System.out.println("1. Message customers");
                        System.out.println("2. Log out ");
                        choiceOfAction = scanner.nextInt();
                        // ================ Message Functionality ============================ //
                        if (choiceOfAction == 1) {
                            System.out.println("Would you like to view list of customers? (1) or search for customers? (2)");
                            int messageType = scanner.nextInt();

                            // checks for only 1 or 2
                            while (messageType != 1 && messageType != 2) {
                                System.out.println("Please enter a valid number");
                                messageType = scanner.nextInt();
                            }
                            if (messageType == 1) {
                                System.out.println("Which customer would you like to message?");

                                // displays list of available seller.
                                seller.viewListOfCustomers();
                                scanner.nextLine();
                                String choiceOfCustomer = scanner.nextLine();

                                System.out.println(choiceOfCustomer);
                                // loops through each available seller and makes sure the name matches a valid one
                                for (Customer customer: seller.getListOfCustomers()) {
                                    if (customer.getUserName().equals(choiceOfCustomer)) {
                                        validCustomer = true;
                                    }
                                }
                                // if it does not match, prompts user to enter a valid one
                                while (!validCustomer) {
                                    System.out.println("Please enter a valid customer name");
                                    choiceOfCustomer = scanner.nextLine();
                                    for (Customer customer: seller.getListOfCustomers()) {
                                        if (customer.getUserName().equals(choiceOfCustomer)) {
                                            validSeller = true;
                                        }
                                    }
                                }
                                // sets to false for multiple loop uses
                                validCustomer = false;


                                System.out.println("What is your choice of action with this customer?");
                                System.out.println("1. Send message");
                                System.out.println("2. Edit message");
                                System.out.println("3. Delete message");
                                System.out.println("4. Return back");
                                int choiceOfActionP2 = scanner.nextInt();

                                // ================ Send Message =========================== //
                                if (choiceOfActionP2 == 1) {
                                    // user input for message
                                    System.out.println("What would you like to send");
                                    scanner.nextLine();
                                    String messageToSend = scanner.nextLine();

                                    // goes through each available customer
                                    A : for (Customer customer: seller.getListOfCustomers()) {
                                        // finds the correct customer that user wants to message
                                        if (customer.getUserName().equals(choiceOfCustomer)) {
                                            // goes through the arraylist of arraylists
                                            for (int i = 0; i < seller.messages.size(); i++) {
                                                // finds the arraylist of messages which the first index matches the correct customers name
                                                // assumes that there is already a text history between the users
                                                if (seller.messages.get(i).get(0).equals(customer.getUserName())) {
                                                    System.out.println("Second Round");
                                                    // adds the message into the array that has there text history already
                                                    seller.messages.get(i).add(seller.getName() + ": " + messageToSend);
                                                    // visual display on terminal (testing purposes)
                                                    System.out.println(seller.messages);
                                                    break A;
                                                }
                                                // if seller does not have text history and its we went through whole loop, create new message history with text and store it within the messages
                                                if (!seller.hasContact && i == seller.messages.size() - 1 ) {
                                                    System.out.println("first round!");
                                                    // where we store the written text
                                                    ArrayList<String> message = new ArrayList<>();
                                                    // first slot is the name of the recipient (sorting / finding purposes)
                                                    message.add(customer.getUserName());
                                                    message.add(seller.getName() + ": " + messageToSend);
                                                    // stores the string arraylist of text into an array list that stores  multiple arraylists of text (keeps history from each seller the user messgaes)
                                                    seller.messages.add(message);
                                                    // now seller has contact, no need to go through this initial condition
                                                    seller.setHasContact(true);
                                                    System.out.println(seller.messages);
                                                    break A;
                                                }
                                            }
                                        }
                                    }
                                    // sets it to false after sending message in case customer messages a new seller.
                                    seller.setHasContact(false);
                                    // ================ Send Message =========================== //

                                    // ================ Edit Message =========================== //
                                } else if (choiceOfActionP2 == 2) {
                                    // user input for message to edit
                                    System.out.println("Which message would you like to edit from this customer?");
                                    scanner.nextLine();
                                    String messageToEdit = scanner.nextLine();

                                    // goes through each customer
                                    for (Customer customer: seller.getListOfCustomers()) {
                                        // finds correct customer that user wants to message
                                        if (customer.getUserName().equals(choiceOfCustomer)) {
                                            // goes through each arraylist in the arraylist of arraylists
                                            for (int i = 0; i < seller.messages.size(); i++) {
                                                // finds the arraylist of messages which the first index matches the correct customer's name
                                                if (seller.messages.get(i).get(0).equals(customer.getUserName())) {
                                                    // loops through each text that was sent
                                                    for (int j = 1; j < seller.messages.get(i).size(); j++) {
                                                        // if target text was found, prompts user to type in replacement message
                                                        if (seller.messages.get(i).get(j).equals(seller.getName() + ": " + messageToEdit)) {
                                                            System.out.println("What would you like to change it too?");
                                                            String newMessage = scanner.nextLine();
                                                            // replaces the old String message with new one
                                                            seller.messages.get(i).set(seller.messages.get(i).indexOf(seller.getName() + ": " + messageToEdit),seller.getName() + ": "+ newMessage );
                                                            System.out.println("Edit Successfully");
                                                            // display the message data (test)
                                                            System.out.println(seller.messages.get(i));
                                                            // if no message is found and it comes to end of loop, display invalid message and breaks out of loop.
                                                        } else if (!(seller.messages.get(i).get(j).equals(seller.getName() + ": " + messageToEdit)) && j == seller.messages.get(i).size() - 1) {
                                                            System.out.println("Unable to find message");
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // ================ Edit Message =========================== //

                                    // ================ Delete Message =========================== //
                                } else if (choiceOfActionP2 == 3) {
                                    System.out.println("Which message would you like to delete from this customer?");
                                    scanner.nextLine();
                                    String messageToDelete = scanner.nextLine();

                                    // goes through each customer
                                    for (Customer customer: seller.getListOfCustomers()) {
                                        // finds correct customer that user wants to message
                                        if (seller.getName().equals(choiceOfCustomer)) {
                                            // goes through each arraylist in the arraylist of arraylists
                                            for (int i = 0; i < seller.messages.size(); i++) {
                                                // finds the arraylist of messages which the first index matches the correct customer's name
                                                if (seller.messages.get(i).get(0).equals(customer.getUserName())) {
                                                    // loops through each text that was sent
                                                    for (int j = 1; j < seller.messages.get(i).size(); j++) {
                                                        // if target text was found, removes that string from the specific arraylist of data
                                                        if (seller.messages.get(i).get(j).equals(seller.getName() + ": "+ messageToDelete)) {
                                                            seller.messages.get(i).remove(seller.getName() + ": "+ messageToDelete);
                                                            System.out.println("Delete Successfully");

                                                            System.out.println(seller.messages.get(i));

                                                            // if no message is found and it comes to end of loop, display invalid message and breaks out of loop.
                                                        } else if (!(seller.messages.get(i).get(j).equals(seller.getName() + ": "+ messageToDelete)) && j == seller.messages.get(i).size() - 1){
                                                            System.out.println("Unable to find message");
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                // ================ Delete Message =========================== //

                            }
                        }
                    }
                    // resets choice of action so it loops properly again
                    choiceOfAction = -1;


                }
                if (newAccount.equalsIgnoreCase("Y")) {

                }
            }
        }
    }
}

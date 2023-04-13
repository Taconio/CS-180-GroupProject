import javax.swing.*;
import java.util.Scanner;

public class GUI {
    static int breakOut = -1;
    static int newAcc;
    Scanner scanner = new Scanner(System.in);
    static String currentUser = "";
    static int userChoice;

    boolean loggedIn = false;


    public static void main(String[] args) {
        while (true) {
            
            // welcomeDisplay
            JOptionPane.showMessageDialog(null, "Welcome to the messaging System", "Welcome",
                    JOptionPane.INFORMATION_MESSAGE);
            breakoutDisplay();
            if (breakOut == 2)
                break;
            else {
                customerOrUserQuestionDisplay();

                // ================= Customer Functionality ================= //
                if (userChoice == 1) {
                    haveAccountDisplay();
                    // login for customer
                    if (newAcc == 1) {
                        loginCustomerDisplay();
                    }
                    // new account
                    if (newAcc == 2) {
                        newCustomerDisplay();
                    }

                    // ================= Seller Functionality ================= //
                } else {
                    haveAccountDisplay();

                    // login for seller
                    if (newAcc == 1) {
                        loginSellerDisplay();
                    }
                    // new account
                    if (newAcc == 2) {
                        newSellerDisplay();
                    }
                }
            }
        }
    }
    public static void breakoutDisplay() {
        // breakoutDisplay
        do {
            breakOut = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to use this system? \n1. Yes\n2. No", "breakout", JOptionPane.QUESTION_MESSAGE));
        } while (breakOut != 1 && breakOut != 2);
    }
    public static void customerOrUserQuestionDisplay() {
        do {
            // customer / user display
            userChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Are you a customer or seller? \n1. Customer\n2. Seller",
                    "c or s", JOptionPane.QUESTION_MESSAGE));
        } while (userChoice != 2 && userChoice != 1);

    }
    public static void haveAccountDisplay() {
        do {
            // new account display
            newAcc = Integer.parseInt(JOptionPane.showInputDialog(null, "Do you already have an account? \n1. Yes\n2. No",
                    "newAccount", JOptionPane.QUESTION_MESSAGE));
        } while (newAcc != 2 && newAcc != 1);
    }
    public static void newCustomerDisplay() {
        // userName display
        currentUser = JOptionPane.showInputDialog(null, "Please enter a username",
                "cUserName", JOptionPane.QUESTION_MESSAGE);

        // password display
        String password  = JOptionPane.showInputDialog(null, "Please enter a password",
                "cPassword", JOptionPane.QUESTION_MESSAGE);

        // email display
        String email  = JOptionPane.showInputDialog(null, "Please enter an email",
                "cEmail", JOptionPane.QUESTION_MESSAGE);
    }
    public static void newSellerDisplay() {
        // userName display
        currentUser = JOptionPane.showInputDialog(null, "Please enter a username",
                "sUserName", JOptionPane.QUESTION_MESSAGE);

        // password display
        String password  = JOptionPane.showInputDialog(null, "Please enter a password",
                "sPassword", JOptionPane.QUESTION_MESSAGE);

        // email display
        String email  = JOptionPane.showInputDialog(null, "Please enter an email",
                "sEmail", JOptionPane.QUESTION_MESSAGE);

        String storeName  = JOptionPane.showInputDialog(null, "Please enter an store name",
                "sStore", JOptionPane.QUESTION_MESSAGE);

    }

    public static void loginCustomerDisplay() {
        String userName = JOptionPane.showInputDialog(null, "Please enter you username",
                "cUserName", JOptionPane.QUESTION_MESSAGE);

        String password = JOptionPane.showInputDialog(null, "Please enter you password",
                "cPassword", JOptionPane.QUESTION_MESSAGE);

    }
    public static void loginSellerDisplay() {
        String userName = JOptionPane.showInputDialog(null, "Please enter you username",
                "sUserName", JOptionPane.QUESTION_MESSAGE);

        String password = JOptionPane.showInputDialog(null, "Please enter you password",
                "sPassword", JOptionPane.QUESTION_MESSAGE);
    }
    public static void errorMessageDisplay() {
        JOptionPane.showInputDialog(null, "Error! Run the program again to try again!",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}

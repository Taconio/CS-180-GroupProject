import javax.swing.*;
import java.util.Scanner;

public class GUI {

    public static void main(String[] args) {

        int breakOut = -1;
        int newAcc;
        Scanner scanner = new Scanner(System.in);
        String currentUser = "";

        while (true) {
            // welcomeDisplay
            JOptionPane.showMessageDialog(null, "Welcome to the messaging System", "Welcome",
                    JOptionPane.INFORMATION_MESSAGE);

            // breakoutDisplay
            do {
                breakOut = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to use this system? \n1. Yes\n2. No",
                        "breakout", JOptionPane.QUESTION_MESSAGE));
            } while (breakOut != 1 && breakOut != 2);

            if (breakOut == 2)
                break;
            else {
                int userChoice;
                do {
                    // customer / user display
                    userChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Are you a customer or seller? \n1. Customer\n2. Seller",
                            "c or s", JOptionPane.QUESTION_MESSAGE));
                } while (userChoice != 2 && userChoice != 1);

                if (userChoice == 1) {
                    do {
                        // new account display
                        newAcc = Integer.parseInt(JOptionPane.showInputDialog(null, "Do you already have an account? \n1. Yes\n2. No",
                                "newAccount", JOptionPane.QUESTION_MESSAGE));
                    } while (newAcc != 2 && newAcc != 1);

                    // ================= Customer Functionality ================= //

                    if (newAcc == 2) {

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
                }
            }
        }
    }
}
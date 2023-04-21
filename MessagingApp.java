import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Paint Canvas to paint.
 *
 * @author Parth Thakre
 * @version 2023-04-12
 */


public class MessagingApp extends JComponent implements Runnable {
    MessagingApp messagingApp;
    boolean isCustomer;
    boolean isSeller;
    JFrame frame;
    JPanel welcomeScreen;
    JPanel sellerLogin;
    JPanel customerLogin;
    String currentUser;

    JTextArea username;
    JTextArea sUsername;
    JTextArea password;
    JTextArea sPassword;
    Customer customer;
    Seller seller;
    /* action listener for buttons */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            //System.out.println(actionCommand);
            if (actionCommand.equalsIgnoreCase("Customer")) {
                isCustomer = true;
                frame.setContentPane(customerLogin);
                frame.invalidate();
                frame.validate();
            }
            if (actionCommand.equalsIgnoreCase("Seller")) {
                isSeller = true;
                //loadSeller();
                frame.setContentPane(sellerLogin);
                frame.invalidate();
                frame.validate();
            }
            if(actionCommand.equalsIgnoreCase("Login")){
                String pw;
                if(isCustomer){
                    currentUser = username.getText();
                    pw = password.getText();
                }
                else{
                    currentUser = sUsername.getText();
                    pw = sPassword.getText();
                }
            }
        }
    };

    public MessagingApp() {
        currentUser = "";
        isCustomer = false;
        isSeller = false;
        welcomeScreen = new JPanel();
        sellerLogin = new JPanel();
        customerLogin = new JPanel();
        frame = new JFrame("Messaging App");
        username = new JTextArea("Username");
        password = new JTextArea("Password");
        sUsername = new JTextArea("Username");
        sPassword = new JTextArea("Password");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MessagingApp());
    }

    public void run() {
        /* set up JFrame */
        //messagingApp = new MessagingApp();
        frame.setLayout(null);
        //frame.add(messagingApp, BorderLayout.CENTER);
        //Container content = frame.getContentPane();
        //content.setLayout(new BorderLayout());
        //messagingApp = new MessagingApp();
        //content.add(messagingApp, BorderLayout.CENTER);

        frame.setSize(720, 576);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        welcomeScreen.setLayout(null);
        welcomeScreen.setBounds(0, 0, 720, 576);
        JLabel welcomeText = new JLabel("Welcome to the messaging app! Are you a Customer or a Seller?");
        welcomeText.setBounds(180, 140, 400, 50);
        JButton customerButton = new JButton("Customer");
        customerButton.addActionListener(actionListener);
        JButton sellerButton = new JButton("Seller");
        sellerButton.addActionListener(actionListener);
        customerButton.setBounds(280, 220, 150, 50);
        sellerButton.setBounds(280, 290, 150, 50);

        //welcomeScreen.setBounds(0,0,720,576);
        //welcomeScreen.add(welcomeText);
        welcomeScreen.add(welcomeText);
        welcomeScreen.add(customerButton);
        welcomeScreen.add(sellerButton);
        frame.setContentPane(welcomeScreen);
        //welcomeScreen.setVisible(true);

        JButton login = new JButton("Login");
        login.setBounds(310, 270, 100, 20);
        login.addActionListener(actionListener);

        customerLogin.setLayout(null);
        customerLogin.setBounds(0, 0, 720, 576);
        JLabel welcomeCustomer = new JLabel("Welcome Customer! Login or Create new account.");
        welcomeCustomer.setBounds(230, 140, 300, 50);

        username.setBounds(310, 210, 100,20);
        password.setBounds(310, 240, 100,20);
        customerLogin.add(welcomeCustomer);
        customerLogin.add(username);
        customerLogin.add(password);
        customerLogin.add(login);

        JButton sLogin = new JButton("Login");
        sLogin.setBounds(310, 270, 100, 20);
        sLogin.addActionListener(actionListener);


        sellerLogin.setLayout(null);
        sellerLogin.setBounds(0, 0, 720, 576);
        JLabel welcomeSeller = new JLabel("Welcome Seller! Login or Create new account.");
        welcomeSeller.setBounds(230, 140, 400, 50);

        sUsername.setBounds(310, 210, 100,20);
        sPassword.setBounds(310, 240, 100,20);
        sellerLogin.add(welcomeSeller);
        sellerLogin.add(sUsername);
        sellerLogin.add(sPassword);
        sellerLogin.add(sLogin);


    }
}
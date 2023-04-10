import java.util.*;
import java.io.*;

/**
 * Runs our project for CS180 Spring 2023
 *
 * @author Group #
 * @date 04-08-2023
 */

public class Seller {
    private String username;
    private String password;
    private String storeName;
    private String[] messagedCustomers;

     /*
        For new accounts
     */

    public Seller(String username, String password, String storeName) {
        this.username = username;
        this.password = password;
        this.storeName = storeName;
        messagedCustomers = null;
    }

    /*
        For prexisting accounts
     */

    public Seller(String username, String password, String storeName, String[] messagedCustomers) {
        this.username = username;
        this.password = password;
        this.messagedCustomers = messagedCustomers;
        this.storeName = storeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String[] getMessagedCustomers() {
        return messagedCustomers;
    }

    public void setMessagedCustomers(String[] messagedCustomers) {
        this.messagedCustomers = messagedCustomers;
    }

    /*
        Reads all the information from update info, adds users name to other users conversation lists
     */
    public void updateInfo() {
        File f = new File("UserInfo.txt");
        ArrayList<String> newLines = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            while (true) {
                String line = bfr.readLine(); // reads first line to check if the person is customer or seller
                if (line == null)
                    break;
                String messageList = bfr.readLine();
                String[] userInfo = line.split(",");
                if (messagedCustomers != null && userInfo[2].equals("Customer") && !messageList.contains(username)) {
                    for (int i = 0; i < messagedCustomers.length; i++) {
                        if (messagedCustomers[i].equals(userInfo[0])) {
                            if (messageList.equals("null"))
                                messageList = username;
                            else
                                messageList = messageList + "," + username;
                        }
                    }
                }
                if (!line.substring(0, line.indexOf(",")).equals(username)) {
                    newLines.add(line);
                    newLines.add(messageList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        String userInfo = username + "," + password + "," + "Seller" + "," + storeName;
        String messages = "";
        if (messagedCustomers == null)
            messages = "null";
        else {
            for (int i = 0; i < messagedCustomers.length - 1; i++) {
                messages = messages + messagedCustomers[i] + ",";
            }
            messages += messagedCustomers[messagedCustomers.length - 1];
        }
        try {
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            for (int i = 0; i < newLines.size(); i++)
                pw.println(newLines.get(i));
            pw.println(userInfo);
            pw.println(messages);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

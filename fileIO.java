import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class fileIO {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your username: (testing purposes only)");
        String customerUsername = scan.nextLine();
        System.out.println("Enter the username of the user you are importing the file: ");
        String sellerUsername = scan.nextLine();

        System.out.println("File selection implementation: ");
        try {
            boolean existing = false;
            StringBuilder messageContent = new StringBuilder();
            String messageLine;
            do {
                System.out.println("Please enter a file to import: ");
                String fileName = scan.nextLine();
                if (fileName.equals("")){
                    System.out.println("Enter a valid file!");
                } else
                    try {
                        File f = new File(fileName);
                        if (f.exists()) {
                            existing = true;
                            BufferedReader bfr2 =
                                    new BufferedReader(new FileReader(fileName));

                            while ((messageLine = bfr2.readLine()) != null) {
                                messageContent.append(messageLine);
                            }
                            bfr2.close();
                            System.out.println("File content sent successfully!");

                        } else {
                            System.out.println("This file does not exist! " +
                                    "Please enter a valid file name:");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }

            } while(!existing);

            File customerFile = new File("marceloseller" + ".txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(customerFile,
                    true));
            pw.println(customerUsername + " sent file text to " + sellerUsername + " @ " +
                    LocalDateTime.now() + ": " + messageContent);
            pw.close();            //This would store messages customer receives

            File conversation = new File(customerUsername + ";&;" + sellerUsername);
            if (!conversation.exists()) {
                PrintWriter pw1 = new PrintWriter(new FileOutputStream(conversation,
                        true));
                pw1.println(customerUsername + " sent file text to " + sellerUsername + " @ " +
                        LocalDateTime.now() + ": " + messageContent);
                pw1.close();

                File conversationLog = new File("conversationLog.txt");
                PrintWriter pw2 = new PrintWriter(new FileOutputStream(conversationLog,
                        true));
                pw2.println(conversation);
                pw2.close();
            } else {
                PrintWriter pw1 = new PrintWriter(new FileOutputStream(conversation,
                        true));
                pw1.println(customerUsername + " sent file text to " + sellerUsername + "@" +
                        LocalDateTime.now() + ": " + messageContent);
                pw1.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Blocking implementation!");

        System.out.println("\n1. Block User \n2. Invisible to user");
        int blockingSelection = scan.nextInt();
        scan.nextLine();

        if (blockingSelection == 1){
            System.out.println("What is the username of the user you want to block?:");
            String usernameBlocking = scan.nextLine();
            String line = customerUsername + ",blocks," + usernameBlocking;

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("blockingList.txt", true));
                writer.append(line);
                writer.newLine();
                writer.close();
                System.out.println("User " + "User1" + " has blocked user " + usernameBlocking);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }

        } else if (blockingSelection == 2) {
            System.out.println("What is the username of the user you want to become invisible to? :\n");
            String usernameInvisible = scan.nextLine();
            String line = usernameInvisible + ",invisible," + usernameInvisible;

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("blockingList.txt", true));
                writer.append(line);
                writer.newLine();
                writer.close();
                System.out.println("User " + "username" + " is now invisible to user " + usernameInvisible);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        }


    }
}

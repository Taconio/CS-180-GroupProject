import java.io.*;
import java.util.Scanner;

public class exportToCSV {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Filename to export:");
        String fileName = scanner.nextLine();
        csvExport(fileName);
    }

    /*
    csvExport takes the fileName of the conversation and
    transfers the information to a csv file in csv file format.
    The csv filename is the same as the input file name but with .csv at the end.

    How to include participants label?
    Is the file name enough - includes the name of both users
    -Can include one line in the beginning that says "Participants:Name,Name"
     */
    public static void csvExport (String conversationTextFile) throws IOException {
        File file = new File(conversationTextFile);
        FileReader fr = new FileReader(file);
        BufferedReader bfr = new BufferedReader(fr);
        File filecsv = new File(conversationTextFile.substring(0, conversationTextFile.length() - 4) + ".csv");
        FileWriter fw = new FileWriter(filecsv);
        String line = bfr.readLine();
        fw.write("\"Timestamp\",\"Message sender\",\"Contents\"\n");
        while(line != null) {
            String temp = "";
            line = line.replaceAll("\"","\"\"");
            temp += "\"" + line.substring(line.indexOf("(") + 1, line.indexOf(")")) + "\"" + ","; //Adds timestamp
            String t = (line.substring(line.indexOf(")") +2));
            temp += "\"" + t.substring(0, t.indexOf(":")) + "\"" + ","; //Name of sender
            temp += "\"" + line.substring(line.indexOf(": ") + 2) + "\"" + "\n"; //Contents of message
            fw.write(temp);
            line = bfr.readLine();
        }
        fw.close();
        bfr.close();
    }
}
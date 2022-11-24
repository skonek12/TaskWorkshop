package pl.coderslab;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String database = "src/main/java/pl/coderslab/database.txt";
        try {
            writeToFile(database);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeToFile(String database) throws IOException {

        Scanner scan = new Scanner(System.in);
        try (FileWriter writer = new FileWriter(database,true)) {
            while (true) {

                System.out.println("Please add task description");
                String task = scan.nextLine();
                System.out.println("Please add task due date [YYYY-MM-DD]");
                String date = scan.nextLine();
                System.out.println("Is this an important task? [true] or [false]");
                String isImportant = scan.nextLine();
                System.out.println("Do you want to add this entry to the database or try again? [Yes] or [No] or [Retry]");
                System.out.println("[ "+task+" ] [ "+date+" ] [ "+isImportant+" ]");
                String pushThrough = StringUtils.lowerCase(scan.nextLine());

                if (pushThrough.equals("yes")) {
                    writer.append(task+","+date+","+isImportant+"\n");
                    System.out.println("Would you like to add another entry? [Yes] or [No]");
                    String anotherEntry = StringUtils.lowerCase(scan.nextLine());
                    if(anotherEntry.equals("yes")) {
                        writeToFile(database);
                    }
                    break;
                } else if (pushThrough.equals("no")) {
                    break;
                } else if (pushThrough.equals("retry")) {

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
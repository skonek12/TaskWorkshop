package pl.coderslab;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String[] yesNoRetry = {"yes", "no", "retry"};
    static String[] yesNo = {"yes", "no"};
    static String[] menu = {"yes", "no"};

    public static void main(String[] args) throws IOException {
        String database = "src/main/java/pl/coderslab/database.txt";
        String menuList = wrongKeyLoop(menu);

        switch (menuList) {


            
        }



//        showList(database);

        try {
            writeToFile(database);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String wrongKeyLoop(String[] keys) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String input = scan.nextLine();
            String inputLowerCase = StringUtils.lowerCase(input);
            for (int i = 0; i < keys.length; i++) {
                String strCheck = keys[i];
                if (strCheck.equals(inputLowerCase)) {
                    return inputLowerCase;
                }
            }
            System.out.println("Try again");
        }
    }


    public static void showList(String database){
        File file = new File(database);

        try (Scanner scan = new Scanner(file)) {
            int counter = 0;
            while (scan.hasNextLine()) {
//                String[] stringArray = new String[3];
          //      System.out.println(scan.nextLine());
                String line = scan.nextLine();
                String[] sArr = line.split(",");

                System.out.print(counter+": ");
                System.out.printf("%-35.35s",sArr[0]);
                System.out.print(" ");
                System.out.printf("%-10.10s",sArr[1]);
                System.out.print(" ");
                System.out.printf("%-5.5s",sArr[2]);
                System.out.println();
                counter++;
            }
        } catch(FileNotFoundException e){
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
//                String pushThrough = StringUtils.lowerCase(scan.nextLine());

                String pushThrough = wrongKeyLoop(yesNoRetry);

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
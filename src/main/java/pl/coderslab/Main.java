package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String[] yesNoRetry = {"yes", "no", "retry"};
    static String[] yesNo = {"yes", "no"};
    static String[] menu = {"list", "add", "remove","exit"};

    public static void main(String[] args) throws IOException {
        String database = "src/main/java/pl/coderslab/database.txt";

        while (true) {
            showMenu(menu);
            String menuList = wrongKeyLoop(menu);
            boolean isDone = false;
            switch (menuList) {
                case "list":
                    showList(database);
                    break;
                case "add":
                    addEntry(database);
                    break;
                case "remove":
                    removeEntry(database);
                    break;
                case "exit":
                    isDone = true;
                    break;
            }
            if (isDone)
                break;
        }


//        showList(database);

//        try {
//            addEntry(database);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public static void showMenu(String[] menu) {
        System.out.println();
        System.out.println("Please select an option:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i]);
        }
        System.out.println();
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


    public static void showList(String database) {
        File file = new File(database);

        try (Scanner scan = new Scanner(file)) {
            int counter = 0;
            while (scan.hasNextLine()) {
//                String[] stringArray = new String[3];
                //      System.out.println(scan.nextLine());
                String line = scan.nextLine();
                String[] sArr = line.split(",");

                System.out.print(counter + ": ");
                System.out.printf("%-35.35s", sArr[0]);
                System.out.print(" ");
                System.out.printf("%-10.10s", sArr[1]);
                System.out.print(" ");
                System.out.printf("%-5.5s", sArr[2]);
                System.out.println();
                counter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addEntry(String database) throws IOException {

        Scanner scan = new Scanner(System.in);
        try (FileWriter writer = new FileWriter(database, true)) {
            while (true) {

                System.out.println("Please add task description");
                String task = scan.nextLine();
                System.out.println("Please add task due date [YYYY-MM-DD]");
                String date = scan.nextLine();
                System.out.println("Is this an important task? [true] or [false]");
                String isImportant = scan.nextLine();
                System.out.println("Do you want to add this entry to the database or try again? [Yes] or [No] or [Retry]");
                System.out.println("[ " + task + " ] [ " + date + " ] [ " + isImportant + " ]");


                String pushThrough = wrongKeyLoop(yesNoRetry);

                if (pushThrough.equals("yes")) {
                    writer.append(task + "," + date + "," + isImportant + "\n");
                    System.out.println("Would you like to add another entry? [Yes] or [No]");
                    String anotherEntry = StringUtils.lowerCase(scan.nextLine());
                    if (anotherEntry.equals("yes")) {
                        addEntry(database);
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

    public static void removeEntry(String database) {
        File file = new File(database);
           Scanner scanner = new Scanner(System.in);

             System.out.println("Which entry would you like to remove?");
           int indexToRemove = scanner.nextInt();

        int counter = 0;
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                scan.nextLine();
                counter++;
            }
            System.out.println(counter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            String[] lineArray = new String[counter];

            try (Scanner scan = new Scanner(file)) {
                for (int i = 0; i < counter; i++) {
                    lineArray[i] = scan.nextLine();
                }
                new FileOutputStream(database).close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            lineArray = ArrayUtils.remove(lineArray,indexToRemove);
        System.out.println(Arrays.toString(lineArray));
        try (FileWriter writer = new FileWriter(database, true)) {
            for (int i = 0; i < lineArray.length; i++) {

                writer.append(lineArray[i]+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }

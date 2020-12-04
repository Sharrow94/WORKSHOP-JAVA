package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    static Scanner scanner=new Scanner(System.in);
    final static String filename="tasks.csv";
    static ArrayList<String>tasks=new ArrayList<>();


    public static void main(String[] args) throws IOException {
        readFromFile();
        do {
            menu();
        }while (menu()!=0);
        scanner.close();
        saveToFile();
    }

    private static void showOptions(){
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option" + ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

    private static int menu(){
        showOptions();
        String decision=scanner.nextLine();
        switch (decision) {
            case "add" -> {
                addTask();
                return 1;
            }
            case "remove" -> {
                removeTask();
                return 1;
            }
            case "list" -> {
                showTasks();
                return 1;
            }
            case "exit" -> {
                System.out.println("Zakończyłem działanie programu");
                return 0;
            }
            default -> {
                System.out.println("Incorrect command.\nPlease try again.");
                return 1;
            }
        }
    }

    private static void readFromFile() throws FileNotFoundException {
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
           tasks.add(scan.nextLine());
        }
        scan.close();
    }

    private static void showTasks(){
        System.out.println("List to do: ");
        for (int i=0;i<tasks.size();i++){
            System.out.println(i+":"+tasks.get(i));
        }
    }

    private static void saveToFile() {
        FileWriter fw=null;
        try {
          fw = new FileWriter(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fw != null;
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            for (String s : tasks) {
                bw.write(s);
                System.out.println(s);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addTask(){
        StringBuilder newTask= new StringBuilder();
        for (int i=0;i<3;i++){
            if (i==0) {
                System.out.println("Description: ");
                newTask.append(scanner.nextLine()).append(", ");
            }
            else if (i==1){
                String date="";
                do {
                    System.out.println("Deadline example: YYYY-MM-DD");
                    date = scanner.nextLine();
                }while (date.matches("\\d{4}-\\d{2}-\\d{2}")!=true);
                newTask.append(date).append(", ");
            }
            else {
                System.out.println("Important? T/F");
                newTask.append(scanner.nextLine());
            }
        }
        tasks.add(newTask.toString());
        showTasks();
    }

    private static void removeTask() {
        int index = scanner.nextInt();
        scanner.nextLine();
        tasks.remove(index);
        showTasks();
    }
}

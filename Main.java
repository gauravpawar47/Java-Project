package com.microproject.jpr;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

class Account
{
    static Scanner input = new Scanner(System.in);
    private int i;
    private int iterator = 0;
    public static int total = 0;

    public void createAccount() throws IOException
    {
        System.out.print("Enter how many customers you want to add : ");
        i = input.nextInt();

        int age[] = new int[i];
        String name[] = new String[i];
        char gender[] = new char[i];

        while(iterator != i)
        {
            System.out.print("\nEnter the Name of customer : ");
            name[iterator] = input.next();

            System.out.print("Enter the Gender of customer : ");
            gender[iterator] = (char) System.in.read();

            System.out.print("Enter the Age of customer : ");
            age[iterator] = input.nextInt();
            iterator++;
        }

        FileWriter fileWriter = new FileWriter("Customer Details.txt");
        fileWriter.write(" \t\t\t             ----- Customer Details ----- \t\t \n\n"
                + "\t\t Customer Age   Customer Name   Customer Gender   Customer Bill\n\n");

        for (int j = 0; j < age.length; j++)
        {
            fileWriter.append("\t\t     "+ age[j] +"            " + name[j] +"             "+ gender[j] +"                "+ total  +"\n");
        }

        if(iterator == i)
            System.out.println(iterator +" customers has been added");
        else
            throw new IOException("Something wrong here ..");

        fileWriter.close();
        System.out.print("\n     Press any key to Continue : ");
        char ch = (char) System.in.read();
    }

    public void displayAll() throws IOException
    {
        File file = new File("E:\\student47\\Programming\\Java\\Microproject\\Customer Details.txt");

        try {
            input = new Scanner(file);
            while(input.hasNextLine())
            {
                String line = input.nextLine();
                if(line == null)
                    continue;
                System.out.println(line);
            }
            input.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        System.out.print("\n     Press any key to Continue : ");
        char ch = (char) System.in.read();
    }
}

class Food extends Account
{
    private Vector<String> foodName = new Vector<>(100);
    private Vector<Integer> foodPrice = new Vector<>(100);
    private static int id = 6;

    Food()
    {
        foodName.add(0, "Burger");
        foodName.add(1, "Chips ");
        foodName.add(2, "Pizza ");
        foodName.add(3, "Samosa");
        foodName.add(4, "Idli  ");

        foodPrice.add(0, 50);
        foodPrice.add(1, 10);
        foodPrice.add(2, 50);
        foodPrice.add(3, 10);
        foodPrice.add(4, 20);
    }

    public void Menu() throws IOException
    {
        System.out.println( "     -------------------------------------\n"+
                            "     Food Id     Food Name     Food Price\n"+
                            "     -------------------------------------\n");

        for (int i = 0; i < foodName.size(); i++) {
            System.out.format("        "+ (i+1) +"          "+ foodName.get(i) +"         "+ foodPrice.get(i)+ "\n");
        }

        System.out.print("\n     Press any key to Continue : ");
        char ch = (char) System.in.read();
    }

    public void addFood() throws IOException {
        String name;
        int price;

        System.out.print("\n     Enter the Name of Food  : ");
        name = input.nextLine();

        System.out.print("     Enter the Price of Food : ");
        price = input.nextInt();

        foodName.add(id - 1, name);
        foodPrice.add(id - 1, price);
        id++;
        System.out.println("\n     Updated Menu --> \n");
        Menu();

        System.out.print("\nPress any key to Continue : ");
        char ch = (char) System.in.read();
    }

    public void editFood() throws IOException
    {
        String name;
        int price;
        int id;

        System.out.print("     Enter the id of Food : ");
        id = input.nextByte();

        if(id <= foodName.size())
        {
            System.out.print("\n     Enter the Name of Food  : ");
            name = input.next();

            System.out.print("     Enter the Price of Food : ");
            price = input.nextByte();

            foodName.remove(id - 1);
            foodPrice.remove(id - 1);

            foodName.add(id - 1, name);
            foodPrice.add(id - 1, price);
        }
        else
            System.out.println("     Invalid Index");

        System.out.println("     Food has been Updated successfully !!");
        System.out.println("\n     Updated Menu --> \n");
        Menu();
    }

    public void buyFood() throws IOException
    {
        int id  = 1, quantity;
        boolean i = true;

        Menu();

        while (i)
        {
            System.out.print("\n     Enter the Food Id you want to purchase (Press 0 for Exit) : ");

            if (id <= foodName.size())
            {
                id = input.nextByte();

                if (id == 0)
                    break;

                System.out.print("     Enter the Quantity : ");
                quantity = input.nextByte();

                super.total += (foodPrice.get(id - 1) * quantity);
            }
            else
            {
                System.out.println("\n     Invalid Entry");
            }
        }
        System.out.println("\n     Your Bill is : "+ super.total);
        System.out.print("\n     Press any key to Continue : ");
        char ch = (char) System.in.read();
    }

    public void deleteFood() throws IOException {
        int id;

        Menu();

        System.out.print("\n     Enter the id of Food : ");
        char ch = (char) System.in.read();
        id = input.nextByte();;

        if (id <= foodName.size())
        {
            foodName.remove(id - 1);
            System.out.println("     Food Deleted Successfully");
        }
        else
            System.out.println("     Invalid Entry");

        System.out.print("\n     Press any key to Continue : ");
         ch = (char) System.in.read();
    }
}

public class Main
{
     static public void intro() throws IOException {
        System.out.println("\t\t********************************");
        System.out.println("\t\t*                              *");
        System.out.println("\t\t*    Group - 11 MicroProject   *");
        System.out.println("\t\t*                              *");
        System.out.println("\t\t********************************");

        System.out.print("\nPress any key to Continue : ");
        char ch = (char) System.in.read();

        System.out.println("\n\n\t\t===============================");
        System.out.println("\t\t  CANTEEN MANAGEMENT SYSTEM  ");
        System.out.println("\t\t===============================");
    }

    public static void main(String[] args) throws IOException
    {
        char ch;

        Account ac = new Account();
        Food food = new Food();
        Scanner input = new Scanner(System.in);

        intro();

        do
        {
            System.out.printf("\n\n\n\t MAIN MENU" +
                          "\n\n\t 01. NEW CUSTOMER" +
                          "\n\n\t 02. ALL FOOD" +
                          "\n\n\t 03. BUY FOOD" +
                          "\n\n\t 04. ADD NEW FOOD" +
                          "\n\n\t 05. EDIT FOOD" +
                          "\n\n\t 06. ALL CUSTOMERS LIST" +
                          "\n\n\t 07. DELETE FOOD" +
                          "\n\n\t 08. EXIT" +
                          "\n\n\t Select Your Option (1-8) : ");

            ch = input.next().charAt(0);
            System.out.println();

            switch (ch)
            {
                case '1' :
                    ac.createAccount();
                    break;
                case '2' :
                    food.Menu();
                    break;
                case '3' :
                    food.buyFood();
                    break;
                case '4' :
                    food.addFood();
                    break;
                case '5' :
                    food.editFood();
                    break;
                case '6' :
                    ac.displayAll();
                    break;
                case '7' :
                    food.deleteFood();
                    break;
                case '8' :
                    System.out.println("\n\n     Thanks For coming in our Canteen ...! \n");
                    break;
                default :
                    System.out.println("     Invalid Input");
                    break;
            }
        }while(ch != '8');
    }
}
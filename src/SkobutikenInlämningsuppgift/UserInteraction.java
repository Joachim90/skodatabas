package SkobutikenInlämningsuppgift;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    static Scanner sc = new Scanner(System.in);


    public static String readUserName() {
        System.out.println("Användarnamn:");
        String username = sc.nextLine();
        return username;
    }

    public static String readPassword() {
        System.out.println("Lösenord:");
        String password = sc.nextLine();
        return password;
    }


    //Skriver ut en lista av entities(shoeCategory/shoe) och läser in användarens svar
    public static <T extends Entity> int printListReadInput(List<T> list) {
        while (true) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i).toString());
            }
            System.out.print("Välj ett nummer: ");

            try {
                int input = sc.nextInt();
                sc.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Endast siffror är tillåtna. Försök igen.");
                sc.nextLine();
            }
        }
    }
}



package SkobutikenInlämningsuppgift;


import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Dashboard {
    Dashboard(){
        Scanner input = new Scanner(System.in);
        Repository r = new Repository();
        Shoe shoeToOrder = null;
        int inputInt = -1;
        int shoeIdChoice = 0;
        int categoryIdChoice = 0;
        int shoeQuantity = 0;
        String username = "";
        String password = "";
        Customer customer = null;
        List<ShoeCategory> categoryList = r.getShoeCategories();

        while (true) {
            if (customer == null) {
                System.out.println("Välkommen till skobutiken! Logga in för att beställa skor");
                username = UserInteraction.readUserName();
                password = UserInteraction.readPassword();
                customer = r.login(username, password);

                if (customer == null) {
                    System.out.println("Felaktigt användarnamn eller lösenord, försök igen");
                    continue;
                } else {
                    System.out.println(customer.getName() + " är inloggad.");
                }
            }


            System.out.println("Välj en skokategori att beställa från, använd siffran för att välja kategori.\nFör att avsluta programmet, ange siffran 99");



            while (true) {
                try {
                    inputInt = UserInteraction.printListReadInput(categoryList);
                    if (inputInt == 99){
                        break;
                    }
                    categoryIdChoice = categoryList.get(inputInt - 1).getShoeCategoryId();
                    break;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ogiltigt val, försök igen.");
                }
            }
            if (inputInt == 99){
                break;
            }


            // Skapa lista av skor som tillhör vald kategori och kolla av om listan är tom.
            List<Shoe> currentCategory = r.getShoesInThisCategory(categoryIdChoice);
            if (currentCategory.isEmpty()) {
                System.out.println("Samtliga skor i denna kategori är tyvärr slut.");
                continue;
            }

            System.out.println("Välj vilken sko du vill beställa genom att ange siffran. \nFör att avsluta programmet, ange siffran 99");

            while (true) {
                try {
                    inputInt = UserInteraction.printListReadInput(currentCategory);
                    if (inputInt == 99) break;
                    shoeToOrder = currentCategory.get(inputInt - 1);
                    shoeIdChoice = shoeToOrder.getShoeId();
                    break;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ogiltigt val, försök igen.");
                }
            }
            if (inputInt == 99){
                break;
            }

            while (true) {
                try {
                    System.out.println("Välj antal av denna sko som du vill beställa:");
                    inputInt = input.nextInt();
                    if (inputInt == 99) {
                        break;
                    }
                    if (inputInt > shoeToOrder.getStock()) {
                        System.out.println("I lager finns endast " + shoeToOrder.getStock() + "st par.");
                        throw new IllegalArgumentException();
                    }else if (inputInt < 1){
                        System.out.println("Hur tänkte du när du försökte beställa " + inputInt + " antal skor??");
                        throw new IllegalArgumentException();
                    }
                    shoeQuantity = inputInt;
                    r.addToCart(customer.getId(), shoeIdChoice, shoeQuantity, 1);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Endast siffror tillåtna");
                    input.nextLine();
                }catch (IllegalArgumentException e) {
                    System.out.println("Ogiltigt val, försök igen.");
                    input.nextLine();
                }
            }

            if (inputInt == 99){
                break;
            }

            System.out.println();
            System.out.println(shoeQuantity + " par " + shoeToOrder.getColor() +
                    ", "+ shoeToOrder.getBrand() + " i storlek " + shoeToOrder.getSize()+ " tillagt i varukorgen.");
        }


        System.out.println("Tack för att du besökte skobutiken. Hejdå!");

    }

    public static void main(String[] args) {
        Dashboard d = new Dashboard();
    }
}

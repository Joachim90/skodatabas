package SkobutikenInlämningsuppgift;



import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {
    Properties p = new Properties();


    public Repository() {
        try {
            p.load(new FileInputStream("src/SkobutikenInlämningsuppgift/Settings.properties"));
        } catch (Exception e) {
            System.out.println("Något gick fel vid anslutning till databasen.");
        }
    }


    // Metod som tar in användarnamn och lösenord och returnerar ett customer objekt
    public Customer login(String username, String password) {
        Customer cust = null;
        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("pass"));
             PreparedStatement statement = con.prepareStatement("SELECT id, namn from kund where användarnamn =? AND lösenord = ?");

        ) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                cust = new Customer(
                        rs.getInt("ID"),
                        rs.getString("Namn"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() +"("+e.getErrorCode()+")");;
        }
        return cust;
    }




    public List<ShoeCategory> getShoeCategories() {
        List<ShoeCategory> categories = new ArrayList<>();

        ShoeCategory cat = null;
        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("pass"));
             PreparedStatement statement = con.prepareStatement("SELECT id, namn from Kategori");

        ) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                cat = new ShoeCategory(
                        rs.getInt("ID"),
                        rs.getString("Namn"));
                categories.add(cat);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() +"("+e.getErrorCode()+")");;
        }

        return categories;
    }

    // Returnerar en lista av skor som tillhör den valda kategorin
    public List<Shoe> getShoesInThisCategory(int categoryId) {
        ArrayList<Shoe> shoes = new ArrayList<>();
        String query =  "SELECT Sko.id, Sko.märke, Sko.färg, Sko.storlek, Sko.pris, Sko.Antal_i_lager FROM sko " +
                        "INNER JOIN kategoritillhörighet kt ON Sko.id = kt.SkoID " +
                        "WHERE kt.KategoriID = ? AND Sko.Antal_i_lager > 0";
        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("pass"));
             PreparedStatement statement = con.prepareStatement(query);

        ) {
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Shoe shoe = new Shoe(
                        rs.getInt("id"),
                        rs.getInt("storlek"),
                        rs.getString("färg"),
                        rs.getString("märke"),
                        rs.getInt("pris"),
                        rs.getInt("Antal_i_lager"));
                shoes.add(shoe);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() +"("+e.getErrorCode()+")");
        }
        return shoes;
    }

    // Kör Stored procedure AddToCart
    public void addToCart(int customerId, int shoeId, int quantity, int orderId) {
        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("pass"));

             CallableStatement statement = con.prepareCall("CALL AddToCart(?, ?, ?, ?)")) {

            statement.setInt(1, customerId);
            statement.setInt(2, shoeId);
            statement.setInt(3, quantity);
            statement.setInt(4, orderId);

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage() +"("+e.getErrorCode()+")");
        }

    }

}

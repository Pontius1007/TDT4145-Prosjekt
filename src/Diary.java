import java.sql.*;
import java.util.*;
import java.util.Date;

public class Diary {

    private static final String DB_URL = "jdbc:sqlite:diary.db";
    Connection conn = null;
    Statement stmt = null;

    private void chooseCase() {
        try {
            System.out.println("Kobler til databasen43434343434343...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Databasen er tilkoblet...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println();
            System.out.println("Velg en handling med 1, 2 eller 3");
            System.out.println("1. Legg til en trening");
            System.out.println("2. Hent ukens beste trening");
            System.out.println("3. Treningsøkter fra denne måneden");

            System.out.print("> ");
            int choice = scanner.nextInt();

            System.out.println();

            if (choice == 1) {
                addSession();
                System.out.println("Du valgte 1");
            }
            else if (choice == 2) {
                //getBest()
                System.out.println("Du valgte 2");
            }
            else if (choice == 3) {
                System.out.println("Du valgte 3");
                getStats();
            }
            else if (choice < 1 || choice > 3) {
                System.out.println("YOU CHOSE POORLY");
            }

        }
    }

    private void addSession() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the entry id");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter the date");
        String dato = scanner.nextLine();
        System.out.println("Please enter the start time ");
        String Tidspunkt = scanner.nextLine();
        System.out.println("Please enter duration");
        Integer Varighet = scanner.nextInt();
        System.out.println("Please enter personal shape from 1-10");
        Integer PersonligForm = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter your personal prestasjon");
        String PersonligPrestasjon = scanner.nextLine();
        System.out.println("Hei" + dato + Tidspunkt + Varighet + PersonligForm + PersonligPrestasjon);
        String trening = String.format("INSERT INTO TRENINGSOKT VALUES(%d,'%s','%s','%d','%d','%s')",id,dato,Tidspunkt,Varighet,PersonligForm,PersonligPrestasjon);
        System.out.println(trening);
        executeQuery(trening);


    }

    private void executeQuery(String query){
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException e){
            System.out.println("Someting wong with query: " + query);
            System.out.println(e);
        }
    }

    private void getStats() {

        String q = "SELECT * FROM TRENINGSOKT";
        try {
            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(q);
            System.out.println("Henter treningsøkter for denne måneden...");
            java.util.Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            while(res.next()) {
                String dato = res.getString("Dato");
                String[] parts = dato.split("-");


                if(Integer.parseInt(parts[1]) == month && Integer.parseInt(parts[0]) == year) {

                    String tidspunkt = res.getString("Tidspunkt");
                    int varighet = res.getInt("Varighet");
                    int personligForm = res.getInt("PersonligForm");
                    String personligPrestasjon = res.getString("PersonligPrestasjon");
                    System.out.println(String.format(("Dato: %s, Tidspunkt: %s, Varighet: %d, Personlig form: %d/10, " +
                            "Personlig prestasjon: %s"), dato, tidspunkt, varighet, personligForm, personligPrestasjon));
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Setup su = new Setup();

        String sql = su.getSQL("sql.txt");
        su.connectToDB(DB_URL, sql);

        Diary diary = new Diary();
        diary.chooseCase();
    }
}

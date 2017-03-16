import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
            System.out.println("3. Statistikk fra denne måneden");

            System.out.print("> ");
            int choice = scanner.nextInt();

            System.out.println();

            if (choice == 1) {
                addSession();
                System.out.println("Du valgte 1");
            }
            else if (choice == 2) {
                getBest();
                System.out.println("Du valgte 2");
            }
            else if (choice == 3) {
                getStats();
                System.out.println("Du valgte 3");
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

    private void getBest() {
        // TODO: Not a lot that has to be done here really, just print out the data we want with some text.
    }

    private void getStats() {
        // TODO: Not a lot goes on here either, just a fancy query really.
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

    public static void main(String[] args) {

        Setup su = new Setup();

        String sql = su.getSQL("sql.txt");
        su.connectToDB(DB_URL, sql);

        Diary diary = new Diary();
        diary.chooseCase();
    }
}

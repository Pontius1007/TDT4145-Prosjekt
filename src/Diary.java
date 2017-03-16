import java.util.Scanner;
import java.sql.*;

public class Diary {

    private static final String DB_URL = "jdbc:sqlite:diary.db";
    Connection conn = null;
    Statement stmt = null;


    private void chooseCase() {
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
                System.out.println("Du valgte 1");
                addSession();
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
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        int i = scanner.nextInt();

        System.out.println("" + str + i);
    }

    private void getStats() {



        String q = "SELECT * FROM TRENINGSOKT";
        try {
            ResultSet res = stmt.executeQuery(q);
            System.out.println(res);
        }
        catch (NullPointerException e) {
            System.out.println("Could not find any stats");
        }
        catch (SQLException e){
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

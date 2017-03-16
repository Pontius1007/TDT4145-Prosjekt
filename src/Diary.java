import java.sql.*;
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
            } else if (choice == 2) {
                getBest();
                System.out.println("Du valgte 2");
            } else if (choice == 3) {
                getStats();
                System.out.println("Du valgte 3");
            } else if (choice < 1 || choice > 3) {
                System.out.println("YOU CHOSE POORLY");
            }

        }
    }

    private void addSession() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hva var datoen for treningen? (string)");
        String dato = scanner.nextLine();
        System.out.println("Når på dagen startet treningen? (string)");
        String Tidspunkt = scanner.nextLine();
        System.out.println("Hva var varigheten på treningsøkten? (heltall)");
        Integer Varighet = scanner.nextInt();
        System.out.println("Hvordan vil du plassere din egen form på en skala fra 1 til 10? (heltall)");
        Integer PersonligForm = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Beskriv din egen prestasjon");
        String PersonligPrestasjon = scanner.nextLine();
        System.out.println("Hei" + dato + Tidspunkt + Varighet + PersonligForm + PersonligPrestasjon);
        String trening = String.format("INSERT INTO TRENINGSOKT (Dato, Tidspunkt, Varighet, PersonligForm, PersonligPrestasjon) VALUES('%s','%s','%d','%d','%s')", dato, Tidspunkt, Varighet, PersonligForm, PersonligPrestasjon);
        System.out.println(trening);
        executeQuery(trening);

    }

    private void getBest() {


        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TRENINGSOKT");

            int best = 0;
            String bestStr = "";

            while (rs.next()) {
                int id = rs.getInt("T_ID");
                String date = rs.getString("Dato");
                String timestamp = rs.getString("Tidspunkt");
                int duration = rs.getInt("Varighet");
                int form = rs.getInt("PersonligForm");
                String performance = rs.getString("PersonligPrestasjon");

                int pot = duration * form;

                if (pot > best) {
                    bestStr = "ID: " + id;
                    bestStr += ", Dato: " + date;
                    bestStr += ", Tidspunkt: " + timestamp;
                    bestStr += ", Varighet: " + duration;
                    bestStr += ", Form: " + form;
                    bestStr += ", Prestasjon: " + performance;
                }
            }

            System.out.println("Din beste økt var som følgende!");
            System.out.println(bestStr);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getStats() {
        // TODO: Not a lot goes on here either, just a fancy query really.
    }

    private void executeQuery(String query) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Someting wong with query: " + query);
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        Setup su = new Setup();

        //String sql = su.getSQL("sql.txt");
        //su.connectToDB(DB_URL, sql);

        Diary diary = new Diary();
        diary.chooseCase();
    }
}

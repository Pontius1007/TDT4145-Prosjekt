import java.util.Scanner;

public class Diary {

    private static final String DB_URL = "jdbc:sqlite:diary.db";

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
        // TODO: Will fill in with a whole bunch of while loops and souts when the query is ready
    }

    private void getBest() {
        // TODO: Not a lot that has to be done here really, just print out the data we want with some text.
    }

    private void getStats() {
        // TODO: Not a lot goes on here either, just a fancy query really.
    }

    public static void main(String[] args) {

        Setup su = new Setup();

        String sql = su.getSQL("sql.txt");
        su.connectToDB(DB_URL, sql);

        Diary diary = new Diary();
        diary.chooseCase();
    }
}

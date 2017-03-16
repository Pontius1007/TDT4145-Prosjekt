import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class Setup {

    String getSQL(String path) {

        String sql = "";
        String line = null;

        try {
            FileReader fileReader = new FileReader(path);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                sql += line;
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sql;
    }

    void connectToDB(String DB_URL, String sql) {

        try {
            System.out.println("Kobler til databasen...");
            Connection conn = DriverManager.getConnection(DB_URL);
            System.out.println("Databasen er tilkoblet...");

            System.out.println("Lager tabeller...");
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);
            System.out.println("Tabeller er lagd...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

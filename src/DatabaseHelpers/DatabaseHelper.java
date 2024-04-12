/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseHelpers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author salma
 */
public class DatabaseHelper {
    //TODO: MAKE LOCATION DYNAMMIC 3SHAN YSTH5L 3ND EL NAS
    public static final String URL = "jdbc:sqlite:G:/college/year_1/Term_2/Java/CollegeProjectCode/SQLTest/DB/mydatabase.db";
    
    public static void createNewTable() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    // SQL statement for creating a new table
                    String sql = "CREATE TABLE IF NOT EXISTS notes (\n"
                            + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                            + " title text NOT NULL,\n"
                            + " content text,\n"
                            + " timestamp datetime DEFAULT CURRENT_TIMESTAMP\n"
                            + ");";
                    stmt.execute(sql);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("A new database has been created.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

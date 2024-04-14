/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseHelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import models.NoteModel;
import models.TaskModel;

/**
 *
 * @author salma
 */
public class DatabaseHelper {

    
    public static ArrayList<NoteModel> notes = new ArrayList<NoteModel>(); 
    public static ArrayList<TaskModel> tasks = new ArrayList<TaskModel>(); 
    
    //public static final String URL = "jdbc:sqlite:G:/college/year_1/Term_2/Java/CollegeProjectCode/SQLTest/DB/mydatabase.db";
    
    public DatabaseHelper(){}
        public static final String URL = "jdbc:sqlite:" + DatabaseHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath()  +  "userDatabase.db";
        
      public static ArrayList<NoteModel> getAllNotes() {
        ArrayList<NoteModel> notes = new ArrayList<NoteModel>();
        String sql = "SELECT * FROM notes";
        try (Connection conn = DriverManager.getConnection(DatabaseHelper.URL);
             Statement stmt  = conn.createStatement();
             var rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                NoteModel note = new NoteModel();
                note.setTitle(rs.getString("title"));
                note.setContent(rs.getString("content"));
                note.setId(rs.getInt("ID"));
                note.setLastEditedDate(rs.getString("lastEditedDate"));
                note.setCreatedDate(rs.getString("createdDate"));
                //Add more fields as necessary
                notes.add(note);   
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return notes;
    }
      
      public static ArrayList<TaskModel> getAllTasks() {
        ArrayList<TaskModel> taskss = new ArrayList<TaskModel>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = DriverManager.getConnection(DatabaseHelper.URL);
             Statement stmt  = conn.createStatement();
             var rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                TaskModel task = new TaskModel();
                task.setTask(rs.getString("title"));
                task.setID(rs.getInt("ID"));
                task.setChecked(rs.getBoolean("state"));
                //Add more fields as necessary
                taskss.add(task);   
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return taskss;
    }
      
    public static void setupDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    // SQL statement for creating a new table
                    String sql = "CREATE TABLE IF NOT EXISTS notes (\n"
                            + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
                            + " title text,\n"
                            + " content text,\n" 
                            + " lastEditedDate datetime,\n"
                            + " createdDate datetime DEFAULT CURRENT_TIMESTAMP\n"
                            + ");";
                    
                    stmt.execute(sql);
                    
                    sql = "CREATE TABLE IF NOT EXISTS tasks (\n"
                            + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
                            + " title text,\n"
                            + " state bool,\n" 
                            + " createdDate datetime DEFAULT CURRENT_TIMESTAMP\n"
                            + ");";
                    stmt.execute(sql);
                    notes = getAllNotes();
                    tasks = getAllTasks();
                    
//                    for(NoteModel note : notes)
//                    {
//                        System.out.println(note.toString());
//                    }
                
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



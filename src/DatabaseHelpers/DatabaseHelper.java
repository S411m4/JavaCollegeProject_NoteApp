/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseHelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.NoteModel;
import models.TaskModel;

/**
 *
 * @author salma
 */
public class DatabaseHelper {

    public static ArrayList<NoteModel> notes = new ArrayList<NoteModel>();
    public static ArrayList<TaskModel> tasks = new ArrayList<TaskModel>();
    public static ArrayList<String> tags = new ArrayList<String>();
    //public static final String URL = "jdbc:sqlite:G:/college/year_1/Term_2/Java/CollegeProjectCode/SQLTest/DB/mydatabase.db";

    public DatabaseHelper() {
    }
    public static final String URL = "jdbc:sqlite:" + DatabaseHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "userDatabase.db";

    public static ArrayList<NoteModel> getAllNotes() {
        ArrayList<NoteModel> notes = new ArrayList<NoteModel>();
        String sql = "SELECT * FROM notes";
        try (Connection conn = DriverManager.getConnection(DatabaseHelper.URL); Statement stmt = conn.createStatement(); var rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                NoteModel note = new NoteModel();
                note.setTitle(rs.getString("title"));
                note.setContent(rs.getString("content"));
                note.setId(rs.getInt("ID"));
                note.setLastEditedDate(rs.getString("lastEditedDate"));
                note.setCreatedDate(rs.getString("createdDate"));
                note.setTag(rs.getString("tag"));
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
        try (Connection conn = DriverManager.getConnection(DatabaseHelper.URL); Statement stmt = conn.createStatement(); var rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TaskModel task = new TaskModel();
                task.setTask(rs.getString("title"));
                task.setID(rs.getInt("ID"));
                task.setChecked(rs.getBoolean("state"));
                task.setDueDateTime(rs.getString("dueDateTime"));
                //Add more fields as necessary
                taskss.add(task);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return taskss;
    }

    public static ArrayList<String> getAllTags() {
        String sql = "SELECT tag FROM notes WHERE tag <> ?"; // SQL query

        try (Connection conn = DriverManager.getConnection(URL); var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "NULL"); // Set the value to skip 'NULL' tag

            try (var rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    String tag = rs.getString("tag");
                    if (!tags.contains(tag)) {
                        tags.add(tag);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching tags: " + e.getMessage());
        }
        return tags;
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
                            + " createdDate datetime DEFAULT CURRENT_TIMESTAMP,\n"
                            + " tag text\n" // Add this line for the tag
                            + ");";

                    stmt.execute(sql);

                    sql = "CREATE TABLE IF NOT EXISTS tasks (\n"
                            + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
                            + " title text,\n"
                            + " state bool,\n"
                            + " createdDate datetime DEFAULT CURRENT_TIMESTAMP,\n"
                            + " dueDateTime datetime,\n"
                            + " finishedDate datetime \n"
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

    public static Map<String, Integer> getNotesCountByTag() {
        Map<String, Integer> tagCounts = new HashMap<>();
        String sql = "SELECT tag, COUNT(*) AS note_count FROM notes GROUP BY tag";

        try (Connection conn = DriverManager.getConnection(URL); var pstmt = conn.prepareStatement(sql); var rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String tag = rs.getString("tag");
                int count = rs.getInt("note_count");
                tagCounts.put(tag, count);
            }
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }

        return tagCounts;
    }

    public static Map<String, Integer> getOverallTaskCounts() {
        Map<String, Integer> taskCounts = new HashMap<>();
        taskCounts.put("done", 0);
        taskCounts.put("undone", 0);

        String sql = "SELECT state, COUNT(*) as count FROM tasks GROUP BY state;";

        try (Connection conn = DriverManager.getConnection(URL); var pstmt = conn.prepareStatement(sql)) {
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                int state = rs.getInt("state");
                int count = rs.getInt("count");
                if (state == 1) {
                    taskCounts.put("done", count);
                } else if (state == 0) {
                    taskCounts.put("undone", count);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return taskCounts;
    }
}

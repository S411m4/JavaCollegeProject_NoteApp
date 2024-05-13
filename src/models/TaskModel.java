/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DatabaseHelpers.DatabaseHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author salma
 */
public class TaskModel {

    String task;
    String dueDateTime;
    LocalDateTime finishedDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public LocalDateTime getFinishedDate() {
        return finishedDate;
    }

    public String getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(String dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        if (checked) {
            //task done
            finishedDate = LocalDateTime.now();
        }
    }

    int ID = -1;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    boolean checked;

    public TaskModel() {

        if (ID == -1) {
            task = "Task...";
            Create();
        }

    }

  private boolean Create() {
    String sql = "INSERT INTO tasks(title, state, dueDateTime, finishedDate) VALUES(?, ?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(DatabaseHelper.URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, this.task);
        pstmt.setBoolean(2, this.checked);
        pstmt.setString(3,this.dueDateTime);
        pstmt.setString(4, this.finishedDate != null ? finishedDate.format(formatter) : null); // Handle null finishedDate
        pstmt.executeUpdate();

        String sqlGetId = "SELECT MAX(ID) AS MAX_ID FROM tasks";
        var rs = conn.prepareStatement(sqlGetId).executeQuery();
        if (rs.next()) {
            ID = rs.getInt("MAX_ID");
        }
        DatabaseHelper.tasks.add(this);
        return true;
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
    }
}

    public boolean Delete() {
        String sql = "DELETE FROM tasks WHERE ID=?";
        try (Connection conn = DriverManager.getConnection(DatabaseHelper.URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, this.ID);
            pstmt.executeUpdate();
            DatabaseHelper.tasks.remove(this);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

public boolean Save() {
    String sql = "UPDATE tasks SET title=?, state=?, dueDateTime=?, finishedDate=? WHERE ID=?";
    try (Connection conn = DriverManager.getConnection(DatabaseHelper.URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, this.task);
        pstmt.setBoolean(2, this.checked);
        pstmt.setString(3, dueDateTime);
        pstmt.setString(4, finishedDate != null ? finishedDate.format(formatter) : null); // Handle null finishedDate
        pstmt.setInt(5, this.ID);
        pstmt.executeUpdate();
        return true;
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
    }
    
}

    public String getText() {
        return task;
    }

    public boolean getState() {
        return checked;
    }

    public TaskModel(String task) {
        this.task = task;
        checked = false;
    }
}

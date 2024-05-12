package models;

import DatabaseHelpers.DatabaseHelper;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jdk.jfr.Timestamp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author salma
 */
public class NoteModel {

    private int id = -1;
    private String title = "Title";
    private String content;
    private String createdDate;
    private String lastEditedDate;
    private String tag;
    //private ArrayList<ToDoModel> todos = new ArrayList<ToDoModel>();

    public NoteModel(){
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                

        createdDate = currentDateTime.format(formatter);
        lastEditedDate = currentDateTime.format(formatter);
    
    }
    
    public String getTag(){return tag;}
    public void setTag(String tag){this.tag = tag;}
    
    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getLastEditedDate() {
        return lastEditedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastEditedDate(String lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean Save() {
        String sql = "UPDATE notes SET title=?, content=?, lastEditedDate=?, tag=? WHERE ID=?";
        LocalDateTime currentDateTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (Connection conn = DriverManager.getConnection(DatabaseHelper.URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.title);
            pstmt.setString(2, this.content);
            pstmt.setString(3, currentDateTime.format(formatter));
            pstmt.setString(4, this.tag);
            pstmt.setInt(5, this.id);
            
            pstmt.executeUpdate();
            lastEditedDate = currentDateTime.format(formatter);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean Delete(){
        String sql = "DELETE FROM notes WHERE ID=?";
        try (Connection conn = DriverManager.getConnection(DatabaseHelper.URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, this.id);
            pstmt.executeUpdate();
            DatabaseHelper.notes.remove(this);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean Create() {
        String sql = "INSERT INTO notes(title, content, createdDate) VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(DatabaseHelper.URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.title);
            pstmt.setString(2, this.content);
            pstmt.setString(3, this.createdDate);
            pstmt.executeUpdate();

            String sqlGetId = "SELECT MAX(ID) AS MAX_ID FROM notes";
            var rs = conn.prepareStatement(sqlGetId).executeQuery();
            id = rs.getInt("MAX_ID");
            DatabaseHelper.notes.add(this);
            System.out.println(id);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public String toString()
    {
        return "id: " + id + ", title: " + title + ", content: " + content + ", created date: " + createdDate + ", lastEditedDate: " + lastEditedDate; 
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalproject;

import DatabaseHelpers.DatabaseHelper;

/**
 *
 * @author salma
 */
public class FinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseHelper.setupDatabase();
       HomeFrame home = new HomeFrame();
       home.runClass();
    }
    
}

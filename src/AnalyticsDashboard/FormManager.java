/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyticsDashboard;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author Dell
 */
public class FormManager {
    static FormManager instance;
    private final JFrame frame;


    private final MainForm mainForm;

    public static void install(JFrame frame) {
        instance = new FormManager(frame);
    }

    private FormManager(JFrame frame) {
        this.frame = frame;
        mainForm = new MainForm();
        this.frame.getContentPane().add(mainForm);
    }

 

    public static void showForm(SimpleForm component) {
        instance.mainForm.setForm(component);
    }
    
    
    public void updateTempFormUI() {
            SwingUtilities.updateComponentTreeUI(mainForm);
        
    }
}

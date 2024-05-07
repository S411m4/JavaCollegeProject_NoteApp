/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyticsDashboard;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author Dell
 */
public class FormManager {
    private static FormManager instance;
    private final JFrame frame;


    private boolean menuShowing = true;
    private final PanelSlider panelSlider;
    private final MainForm mainForm;

    public static void install(JFrame frame) {
        instance = new FormManager(frame);
    }

    private FormManager(JFrame frame) {
        this.frame = frame;
        panelSlider = new PanelSlider();
        mainForm = new MainForm();
        this.frame.getContentPane().add(panelSlider);
    }

    public static void showMenu() {
        instance.menuShowing = true;
    }

    public static void showForm(SimpleForm component) {
        instance.mainForm.setForm(component);
        instance.panelSlider.addSlide(instance.mainForm);
    }
}

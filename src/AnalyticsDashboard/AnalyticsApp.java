/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyticsDashboard;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.UIManager;

import raven.popup.GlassPanePopup;
/**
 *
 * @author Dell
 */
public final class AnalyticsApp extends JFrame {

    public AnalyticsApp() {  

        init();
    }

    private void init() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(UIScale.scale(new Dimension(1366, 768)));
        setLocationRelativeTo(null);
        setContentPane(new Background());
        GlassPanePopup.install(this);
        FormManager.install(this);
        FormManager.showForm(new DashboardForm());
    }

    public static void setupTheme(){
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        UIManager.put("Panel.background", new Color(20, 18, 24)); // Using the hex color #141218
        FlatDarkLaf.setup();
    }
  
}
  

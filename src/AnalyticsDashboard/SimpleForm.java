/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyticsDashboard;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPanel;

/**
 *
 * @author Raven
 */
public class SimpleForm extends JPanel {

    public SimpleForm() {
        init();
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5;"
                + "background:null");
    }

    public void formInitAndOpen() {

    }

    public void formOpen() {

    }

    public void formRefresh() {

    }

    public boolean formClose() {
        return true;
    }
}
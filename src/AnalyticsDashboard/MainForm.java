    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package AnalyticsDashboard;

    import com.formdev.flatlaf.FlatClientProperties;
    import javax.swing.JPanel;
    import javax.swing.JScrollPane;
    import net.miginfocom.swing.MigLayout;


    /**
     *
     * @author Raven
     */
    public class MainForm extends JPanel {

        public MainForm() {
            init();
          }

        private void init() {
            putClientProperty(FlatClientProperties.STYLE, ""
                    + "border:5,5,5,5;"
                    + "arc:30");
            setLayout(new MigLayout("wrap,fillx", "[fill]", ""));
            JScrollPane scroll = new JScrollPane(null);
            scroll.putClientProperty(FlatClientProperties.STYLE, ""
                    + "border:0,0,0,0");
            scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                    + "trackArc:999;"
                    + "width:10");
            scroll.getVerticalScrollBar().setUnitIncrement(10);
            add(scroll);
        }

        public void setForm(SimpleForm form)
        {
            this.add(form);
        }

    }
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Cell extends JButton {

    private String day;
    private String month;
    private String year;
    private boolean title;
    private boolean isSelected = false;
    
    private String selectedColorCode = "#6131ED";
    private String unselectedColorCode = "#FFFFFF";
    
    
    public Cell() {
        setContentAreaFilled(false);
        setBorder(null);
        setHorizontalAlignment(JLabel.CENTER);
    }

    public void asTitle() {
        title = true;
    }
    
    public void setDay(String day){this.day = day;}
    public void setMonth(String month){this.month = month;}
    public void setYear(String year){this.year = year;}

    public String getDay(){return day;}
    public String getMonth(){return month;}
    public String getYear(){return year;}
    
    public boolean isTitle() {
        return title;
    }
  
    public void setSelected(boolean selected) {
        isSelected = selected;

        if (selected) {
            setForeground(Color.WHITE);            
        } else {
            setForeground(Color.BLACK);
        }
    }

    
    
    @Override
    protected void paintComponent(Graphics grphcs) {

        if (title) {
            grphcs.setColor(new Color(213, 213, 213));
            grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        }
        
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.decode(unselectedColorCode));

        if (isSelected) {
            g2.setColor(Color.decode(selectedColorCode));
            int x = getWidth() / 2 - 17;
            int y = getHeight() / 2 - 17;
            g2.fillRoundRect(x, y, 35, 35, 100, 100);
        }
      
        super.paintComponent(grphcs);
    }
}

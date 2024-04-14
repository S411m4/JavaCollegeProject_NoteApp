/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customSwingComponents;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author salma
 */

    public class StrikeThroughTextField extends javax.swing.JTextField {

    private Color lineColor = Color.BLACK;
    private Timer timer;
    private int line = 0;
    private int drawSpeed = 3;

    public int getDrawSpeed() {
        return drawSpeed;
    }

    public void setDrawSpeed(int drawSpeed) {
        this.drawSpeed = drawSpeed;
    }

 

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    private boolean drawLine = false;

    /**
     * Creates new form strikeThroughEffect
     */
    
    private int getTextWidth()
    {
        FontMetrics metrics = this.getFontMetrics(this.getFont());
        return metrics.stringWidth(this.getText());
    }
    
  public StrikeThroughTextField() {
        timer = new Timer(20, new ActionListener() {  // Use a delay of 20ms for smoother animation
            @Override
            public void actionPerformed(ActionEvent ae) {
                float drawSpeed = getDrawSpeed();
                if (drawLine) {
                    if (line < getTextWidth()) {
                        line += drawSpeed;
                        
                    } else {
                        timer.stop();
                        line = getTextWidth();
                    }
                } else {
                    if (line > 0) {
                        line -= drawSpeed;
                    } else {
                        timer.stop();
                        line = 0;
                    }
                }
                repaint();  // Ensure repaint is called after adjusting 'line'
            }
        });
        
    }

    public void drawStrikeThroughLine() {
        drawLine = true;
        timer.start();
    }

    public void unDrawStrikeThroughLine() {
        drawLine = false;
        timer.start();
    }

     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call to super should be first
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = getHeight() / 2;
        g2.setColor(getLineColor());
        g2.drawLine(0, y, line, y);  // Draw up to 'line', not the full width
    }
}

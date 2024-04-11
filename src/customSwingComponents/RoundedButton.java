/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.markbean.notes.customGUIComponents;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author salma
 */
public class RoundedButton extends JButton{

    public RoundedButton()
    {
        setColor(Color.WHITE);
        colorOver = Color.LIGHT_GRAY;
        colorClick = Color.GRAY;
        borderColor = Color.BLACK;
        
        setContentAreaFilled(false);
        
        //add mouse events
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent me)
            {
                setBackground(colorOver);
                over = true;
            }
            
            @Override
            public void mouseExited(MouseEvent me)
            {
                setBackground(color);
                over = false;
            }
            
            @Override
            public void mousePressed(MouseEvent me)
            {
                setBackground(colorClick);
            }
            
            @Override
            public void mouseReleased(MouseEvent me)
            {
                if(over)
                {
                    setBackground(colorOver);
                }
                else
                {
                    setBackground(color);
                }
            }
        });
    }

    public boolean isOver() {
        return over;
    }

    public Color getColor() {
        return color;
    }

    public Color getColorOver() {
        return colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;
    
    @Override
    protected void paintComponent(Graphics graphics)
    {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //paint border
        g2.setColor(borderColor);
        g2.fillRoundRect(0,0,getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        
        //border set 2 pix
        g2.fillRoundRect(2,2,getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(graphics);
        
    }
    
}

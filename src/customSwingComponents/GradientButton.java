/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customSwingComponents;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author salma
 */
public class GradientButton extends JButton {

    private Color gradientColor1 = Color.BLUE;
    private Color gradientColor2 = Color.CYAN;
    private final Timer timer;
    private final Timer timerPressed;
    private float alpha = 0.3f;
    private boolean mouseOver;
    private boolean pressed;
    private Point pressedLocation;
    private float pressedSize;
    private float sizeSpeed = 1f;
    private float alphaPressed = 0.5f;

    public GradientButton() {
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(10, 20, 10, 20));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                timer.start();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                    pressedSize = 0;
                    alphaPressed = 0.5f;
                    pressed = true;
                    pressedLocation = me.getPoint();
                    timerPressed.setDelay(0);
                    timerPressed.start();
            }
        });

        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mouseOver) {
                    if (alpha < 0.6) {

                        alpha += 0.05f;
                        repaint();
                    } else {
                        alpha = 0.6f;
                        timer.stop();
                        repaint();
                    }
                } else {
                    if (alpha > 0.3f) {
                        alpha -= 0.05f;
                        repaint();
                    } else {
                        alpha = 0.3f;
                        timer.stop();
                        repaint();
                    }

                }

            }
        });
        timerPressed = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pressedSize += sizeSpeed;
                if (alphaPressed <= 0) {
                    pressed = false;
                    timerPressed.stop();
                } else {
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //create gradient color
        GradientPaint gra = new GradientPaint(0, 0, getGradientColor1(), width, 0, getGradientColor2());
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, width, height, height, height);
        createStyle(g2);
        if(pressed){paintPressed(g2);}g2.dispose();
        graphics.drawImage(img, 0, 0, null);
        super.paintComponent(graphics);
    }

    private void createStyle(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        int width = getWidth();
        int height = getHeight();

        GradientPaint gra = new GradientPaint(0, 0, Color.WHITE, 0, height, new Color(255, 255, 255, 60));
        g2.setPaint(gra);
        Path2D.Float f = new Path2D.Float();
        f.moveTo(0, 0);
        int controll = height + height / 2;
        f.curveTo(0, 0, width / 2, controll, width, 0);
        g2.fill(f);

    }

    private void paintPressed(Graphics2D g2)
    {
        if(pressedLocation.x - (pressedSize / 2) < 0 && pressedLocation.x + (pressedSize / 2) > getWidth())
        {
            timerPressed.setDelay(20);
            alphaPressed -= 0.05f;
            if(alphaPressed < 0)
            {
                alphaPressed = 0;
            }
        }
        g2.setColor(Color.WHITE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alphaPressed));
        float x = pressedLocation.x - (pressedSize / 2);
        float y = pressedLocation.y - (pressedSize / 2);
        g2.fillOval((int) x, (int) y, (int)pressedSize, (int)pressedSize);
    }
    public Color getGradientColor1() {
        return gradientColor1;
    }

    public void setGradientColor1(Color gradientColor1) {
        this.gradientColor1 = gradientColor1;
    }

    public Color getGradientColor2() {
        return gradientColor2;
    }

    public void setGradientColor2(Color gradientColor2) {
        this.gradientColor2 = gradientColor2;
    }

}

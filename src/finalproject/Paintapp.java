package finalproject;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Paintapp extends JFrame {
    private JSlider s;
    private Color c = Color.BLACK;
    private DrawingPanel drawingPanel;

    public Paintapp() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel buttonsPanel = new JPanel();
        JButton colorchooser = new JButton("Choose a Color");
        JButton eraser = new JButton("Eraser");
        JButton screenshotButton = new JButton("Save Screenshot");

        colorchooser.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose a Color", c);
            if (newColor != null) c = newColor;
        });
        eraser.addActionListener(e -> c = Color.WHITE);
        screenshotButton.addActionListener(e -> saveScreenshot());

        s = new JSlider(JSlider.HORIZONTAL, 5, 25, 10);
        s.setMajorTickSpacing(5);
        s.setPaintTicks(true);
        s.setPaintLabels(true);

        buttonsPanel.add(colorchooser);
        buttonsPanel.add(eraser);
        buttonsPanel.add(screenshotButton);
        add(buttonsPanel, BorderLayout.NORTH);
        add(s, BorderLayout.SOUTH);

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void saveScreenshot() {
        BufferedImage image = new BufferedImage(drawingPanel.getWidth(), drawingPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        drawingPanel.paint(g2d);
        g2d.dispose();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                ImageIO.write(image, "PNG", new File(fileToSave.getAbsolutePath() + ".png"));
                JOptionPane.showMessageDialog(this, "Screenshot saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving screenshot: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class DrawingPanel extends JPanel {
        private BufferedImage canvas;
        private Graphics2D g2;

        public DrawingPanel() {
            setBackground(Color.WHITE);
            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    int z = s.getValue();
                    g2.setColor(c);
                    g2.fillOval(x - z / 2, y - z / 2, z, z);
                    repaint();
                }
            });
            addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    if (canvas == null) {
                        canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                        g2 = canvas.createGraphics();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Color.WHITE);
                        g2.fillRect(0, 0, getWidth(), getHeight());
                    } else {
                        BufferedImage newCanvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g = newCanvas.createGraphics();
                        g.setColor(Color.WHITE);
                        g.fillRect(0, 0, getWidth(), getHeight());
                        g.drawImage(canvas, 0, 0, null);
                        canvas = newCanvas;
                        g2 = canvas.createGraphics();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g.dispose();
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (canvas != null) {
                g.drawImage(canvas, 0, 0, this);
            }
        }
    }

    public static void main(String[] args) {
        new Paintapp();
    }
}

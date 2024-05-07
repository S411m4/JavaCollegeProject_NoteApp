/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyticsDashboard;
import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.VolatileImage;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dell
 */
public class PanelSlider extends JLayeredPane {

    private PanelSnapshot panelSnapshot;
    private Component component;
    private Component oldComponent;

    public PanelSlider() {
        init();
    }

    private void init() {
        setOpaque(true);
        panelSnapshot = new PanelSnapshot();
        setLayout(new CardLayout());
        setLayer(panelSnapshot, JLayeredPane.DRAG_LAYER);
        add(panelSnapshot);
        panelSnapshot.setVisible(false);
    }

    public void addSlide(Component component) {
        component.applyComponentOrientation(getComponentOrientation());
        if (this.component != null) {
            this.oldComponent = this.component;
        }
        this.component = component;
        if (oldComponent == null) {
            add(component);
            repaint();
            revalidate();
            component.setVisible(true);
        } else {
            add(component);
            
                component.setVisible(true);
                remove(oldComponent);
                revalidate();
                repaint();
            
        }
    }

    public Image createImage(Component component) {
        boolean check = false;
        for (Component com : getComponents()) {
            if (com == component) {
                check = true;
                break;
            }
        }
        if (!check) {
            add(component);
        }
        VolatileImage snapshot = component.createVolatileImage(getWidth(), getHeight());
        if (snapshot == null) {
            return null;
        }
        component.paint(snapshot.getGraphics());
        if (!check) {
            remove(component);
        }
        return snapshot;
    }

    public Image createOldImage() {
        if (oldComponent != null) {
            return createImage(oldComponent);
        }
        return null;
    }

    private class PanelSnapshot extends JComponent {

        @Override
        public void updateUI() {
            super.updateUI();
                if (oldComponent != null) {
                    SwingUtilities.updateComponentTreeUI(oldComponent);
                    oldImage = PanelSlider.this.createImage(oldComponent);
                
            }
        }

        private final Animator animator;
        private float animate;

        private Image oldImage;
        private Image newImage;

        public PanelSnapshot() {
            animator = new Animator(400, new Animator.TimingTarget() {
                @Override
                public void timingEvent(float v) {
                    animate = v;
                    repaint();
                }

                @Override
                public void end() {
                        setVisible(false);
                        oldImage.flush();
                        newImage.flush();
                    
                    component.setVisible(true);
                }
            });
            animator.setInterpolator(CubicBezierEasing.EASE);
        }

        protected void animate(Image oldImage, Image newImage) {
            if (animator.isRunning()) {
                animator.stop();
            }
            this.oldImage = oldImage;
            this.newImage = newImage;
            this.animate = 0f;
            repaint();
            setVisible(true);
            animator.start();
        }

        @Override
        public void paint(Graphics g) {
                int width = getWidth();
                int height = getHeight();
            }
        }
    }




package paneltags;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.SwingUtilities;

public class Item extends javax.swing.JLayeredPane {
    
    

    public boolean isInput() {
        return input;
    }

    private EventTags eventTags;
    private ActionListener eventRemove;
    private KeyAdapter eventKey;
    private EventInput event;
    private boolean input;
    private boolean over;
    private String oldData = "";
    
    public void addEventForInput(EventInput event) {
        this.event = event;
        input = true;
        txt.setFocusable(true);
        cmd.setIcon(null);
    }
    
    public void addEventRemove(ActionListener event) {
        this.eventRemove = event;
    }
    
    public void addEventKey(KeyAdapter event) {
        this.eventKey = event;
    }
    
    public void setEventTags(EventTags event) {
        this.eventTags = event;
    }
    
    private Color color;
    
    public Color getTagColor(){return color;}
    
    public Item(String text, Color tagColor) {
        initComponents();         
        this.color = tagColor;
        txt.setText(text);
        txt.setFocusable(false);

    }

    public void addEventMouse() {
        addMouseEvent(this);
        addMouseEvent(cmd);
        addMouseEvent(txt);
    }

    private void addMouseEvent(Component com) {
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
                repaint();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new Text();
        cmd = new javax.swing.JButton();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        txt.setBackground(new java.awt.Color(20, 18, 24));
        txt.setColumns(20);
        txt.setRows(5);
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFocusLost(evt);
            }
        });
        txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMouseClicked(evt);
            }
        });
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKeyTyped(evt);
            }
        });

        cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneltags/close.png"))); // NOI18N
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setFocusable(false);
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionPerformed(evt);
            }
        });

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(cmd, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionPerformed
        if (eventRemove != null) {
            eventRemove.actionPerformed(evt);
            System.out.println("Remove");
            //  Error when we click on icon
        }
    }//GEN-LAST:event_cmdActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
           
    }//GEN-LAST:event_formKeyTyped

    private void txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyTyped
        if (evt.getKeyChar() == 10) {
            addItem();
        }
    }//GEN-LAST:event_txtKeyTyped

    private void txtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)) {
            txt.setFocusable(true);
            txt.grabFocus();
            oldData = txt.getText().trim();
        }
    }//GEN-LAST:event_txtMouseClicked

    private void txtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFocusLost
        if (isInput()) {
            addItem();
        } else {
            if(txt.getText().trim().equals("")) {
                txt.setText(oldData);
                txt.setFocusable(false);
            } else {
                if(eventTags != null) {
                    if(eventTags.onEditAble(this, getText().trim())) {
                        txt.setFocusable(false);
                    } else {
                        txt.grabFocus();
                    }
                } else {
                    txt.setFocusable(false);
                }
            }
        }
    }//GEN-LAST:event_txtFocusLost

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased
        eventKey.keyReleased(evt);
    }//GEN-LAST:event_txtKeyReleased

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        txt.grabFocus();
    }//GEN-LAST:event_formFocusGained

    private void addItem() {
        String text = txt.getText().trim().replace("\n", "");
        if (input && eventTags != null) {
            if (!eventTags.isAddAble(this, text)) {
                txt.setText(text);
                return;
            }
        }
        if (input) {
            txt.setText("");
        }else {
            txt.setText(text);
            txt.setFocusable(false);
            if(eventTags != null) {
                if(!eventTags.isAddAble(this, text)) {
                    txt.setText(oldData);
                }
            }
        }
        if(!text.equals("")) {
            if (input) {
                event.addItem(text);
            }
        }       
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        if(!isInput()){
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            if (over) {
                g2.setColor(new Color(20, 18, 24));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(8, 62, 243));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                txt.setBackground(new Color(20,18,24));
                txt.setForeground(new Color(230, 224, 233));
                
            } else {
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                txt.setBackground(color);
                txt.setForeground(new Color(0,0,0));

            }
        }
        super.paintComponent(grphcs);
    }
    
    public String getText() {
        return txt.getText().trim();
    }

    public Color generateRandomColor()
    {
        Random random = new Random();
        //generate RGB values from 0 to 255
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        
        return new Color(red, green, blue);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmd;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables
}

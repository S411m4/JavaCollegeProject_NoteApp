package main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import customSwingComponents.PanelSlide;
import com.raven.swing.TimePicker;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JDialog;
import models.TaskModel;


public class CalenderCustom extends javax.swing.JPanel {

    private int month;
    private int year;
    private PanelDate panelDate = new PanelDate(2, 2024);
    private TimePicker timePicker = new TimePicker();
    private JDialog parentDialog;
    private String dueDateTimeString = null;
    private JButton DueDateBtn;
    private TaskModel task;
    
    public CalenderCustom(JDialog parentDialog, JButton DueDateBtn, TaskModel task) {
        this.parentDialog = parentDialog;
        this.DueDateBtn = DueDateBtn;
        this.task = task;
        initComponents();
        setTitleDate();
         panelDate = new PanelDate(month, year);
         panelDate.setVisible(true);
        slide.show(panelDate, PanelSlide.AnimateType.TO_LEFT);
        
        //to fix panel slide disappear problem, DON"T REMOVE
        slide.resize(100, 100);



        showMonthYear();
        panelDate.setMonthYear(month, year);
        
   
         timePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        String timeWithoutAMPM = timePicker.getSelectedTime().replaceAll("\\s*(AM|PM)$", "");

                dueDateTimeString += timeWithoutAMPM;
                DueDateBtn.setToolTipText(dueDateTimeString);
                task.setDueDateTime(dueDateTimeString);
                task.Save();
                
                System.out.println("due date: " + dueDateTimeString);
            }
        });
}
    
  


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layeredPane = new javax.swing.JLayeredPane();
        cmdBack = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        lbMonthYear = new javax.swing.JLabel();
        slide = new customSwingComponents.PanelSlide();
        okBtn = new com.markbean.notes.customGUIComponents.RoundedButton();

        setBackground(new java.awt.Color(51, 51, 51));

        cmdBack.setBackground(new java.awt.Color(51, 51, 51));
        cmdBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        cmdBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdBack.setContentAreaFilled(false);
        cmdBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt);
            }
        });

        cmdNext.setForeground(new java.awt.Color(51, 51, 51));
        cmdNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png"))); // NOI18N
        cmdNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdNext.setContentAreaFilled(false);
        cmdNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNextActionPerformed(evt);
            }
        });

        lbMonthYear.setBackground(new java.awt.Color(51, 51, 51));
        lbMonthYear.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        lbMonthYear.setForeground(new java.awt.Color(103, 80, 164));
        lbMonthYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMonthYear.setText("Month - Year");

        layeredPane.setLayer(cmdBack, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(cmdNext, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lbMonthYear, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layeredPaneLayout = new javax.swing.GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdBack, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMonthYear, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdNext, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layeredPaneLayout.setVerticalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdBack, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMonthYear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdNext, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        slide.setBackground(new java.awt.Color(137, 137, 137));

        okBtn.setBackground(new java.awt.Color(103, 80, 164));
        okBtn.setBorder(null);
        okBtn.setForeground(new java.awt.Color(255, 255, 255));
        okBtn.setText("OK");
        okBtn.setBorderColor(new java.awt.Color(103, 80, 164));
        okBtn.setBorderPainted(false);
        okBtn.setColor(new java.awt.Color(103, 80, 164));
        okBtn.setColorClick(new java.awt.Color(69, 55, 107));
        okBtn.setColorOver(new java.awt.Color(69, 55, 107));
        okBtn.setRadius(15);
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(layeredPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(slide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(okBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNextActionPerformed
        if (month == 12) {
            month = 1;
            year++;
        } else {
            month++;
        }
        panelDate = new PanelDate(month, year);
        slide.show(panelDate, PanelSlide.AnimateType.TO_LEFT);
        showMonthYear();
        panelDate.setMonthYear(month, year);

    }//GEN-LAST:event_cmdNextActionPerformed

    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
        panelDate = new PanelDate(month, year);
        slide.show(panelDate, PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
    }//GEN-LAST:event_cmdBackActionPerformed

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        
        if(panelDate.getSelectedDate() == null) return;
        dueDateTimeString = panelDate.getSelectedDate() + " " ;
        
        timePicker.showPopup(null, 10, 10);
        if(parentDialog != null)
        {
            parentDialog.dispose();
        }
    }//GEN-LAST:event_okBtnActionPerformed

    private void setTitleDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());   //  today
        month = calendar.get(Calendar.MONTH) +1 ;
        year = calendar.get(Calendar.YEAR);
        panelDate.setMonthYear(month, year);
    }
    
    private void showMonthYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DATE, 1);
        SimpleDateFormat df = new SimpleDateFormat("MMMM-yyyy");
        lbMonthYear.setText(df.format(calendar.getTime()));
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdNext;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JLabel lbMonthYear;
    private com.markbean.notes.customGUIComponents.RoundedButton okBtn;
    private customSwingComponents.PanelSlide slide;
    // End of variables declaration//GEN-END:variables
}

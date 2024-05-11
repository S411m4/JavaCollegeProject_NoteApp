/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package finalproject;

import DatabaseHelpers.DatabaseHelper;
import customSwingComponents.ScrollBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import models.NoteModel;

/**
 *
 * @author salma
 */
public class NotePreviewPanel extends javax.swing.JPanel{

    private NoteModel note;
    /**
     * Creates new form NotePanel
     */
    public void updateTags(List<String> newTags) {
        tagsComboBox.removeAllItems();//clear existing items
        for(String tag: newTags)
            tagsComboBox.addItem(tag);
    }
    public NotePreviewPanel(NoteModel note) {
        this.note = note;
        initComponents();
        noteContent.setBackground(Color.white);
        noteContent.setForeground(Color.black);
        noteContent.setFont(new Font("Segoe Print", Font.PLAIN, 12));
        noteTitle.setFont(new Font("Segoe Print", Font.BOLD, 48));

        noteTitle.setText(note.getTitle());
        noteContent.setText(note.getContent());
        
        String lastEditedDateString = note.getLastEditedDate() == null ? note.getCreatedDate() : note.getLastEditedDate();
        lastEdited.setText(lastEditedDateString);
        createdDate.setText(note.getCreatedDate());
        
           noteContent.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Check if Ctrl+S is pressed
                if (e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()) {
                    // Call your Create function here
                    //System.out.println("Save from shortcut");
                    save();
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //System.out.println("AutoSave");
                    save();
                }
            }
        });
    }
    
    
    private void save() {
        note.setTitle(noteTitle.getText());
        note.setContent(noteContent.getText());
        note.Save();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        panelBorder1 = new customSwingComponents.PanelBorder();
        lastEditedDatePanel = new javax.swing.JPanel();
        createdDateLabel = new javax.swing.JLabel();
        createdDate = new javax.swing.JLabel();
        createdDatePanel = new javax.swing.JPanel();
        lastEditedLabel = new javax.swing.JLabel();
        lastEdited = new javax.swing.JLabel();
        noteContent = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        editBtn = new customSwingComponents.GradientButton();
        noteTitle = new javax.swing.JLabel();
        deleteNoteBtn = new customSwingComponents.GradientButton();
        tagsComboBox = new javax.swing.JComboBox<>();

        jToggleButton1.setText("jToggleButton1");

        setOpaque(false);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder1.setRadius(30);

        lastEditedDatePanel.setOpaque(false);

        createdDateLabel.setBackground(new java.awt.Color(204, 204, 204));
        createdDateLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        createdDateLabel.setForeground(new java.awt.Color(153, 153, 153));
        createdDateLabel.setText("created:");

        createdDate.setBackground(new java.awt.Color(255, 255, 255));
        createdDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        createdDate.setForeground(new java.awt.Color(153, 153, 153));
        createdDate.setText("24/3/2024, 3:30 p.m.");

        javax.swing.GroupLayout lastEditedDatePanelLayout = new javax.swing.GroupLayout(lastEditedDatePanel);
        lastEditedDatePanel.setLayout(lastEditedDatePanelLayout);
        lastEditedDatePanelLayout.setHorizontalGroup(
            lastEditedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastEditedDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(createdDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createdDate, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lastEditedDatePanelLayout.setVerticalGroup(
            lastEditedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastEditedDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lastEditedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createdDateLabel)
                    .addComponent(createdDate))
                .addContainerGap())
        );

        createdDatePanel.setOpaque(false);

        lastEditedLabel.setBackground(new java.awt.Color(204, 204, 204));
        lastEditedLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lastEditedLabel.setForeground(new java.awt.Color(153, 153, 153));
        lastEditedLabel.setText("Last Edited:");

        lastEdited.setBackground(new java.awt.Color(255, 255, 255));
        lastEdited.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lastEdited.setForeground(new java.awt.Color(153, 153, 153));
        lastEdited.setText("24/3/2024, 3:30 p.m.");

        javax.swing.GroupLayout createdDatePanelLayout = new javax.swing.GroupLayout(createdDatePanel);
        createdDatePanel.setLayout(createdDatePanelLayout);
        createdDatePanelLayout.setHorizontalGroup(
            createdDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createdDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lastEditedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastEdited, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        createdDatePanelLayout.setVerticalGroup(
            createdDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createdDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createdDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastEditedLabel)
                    .addComponent(lastEdited))
                .addContainerGap())
        );

        noteContent.setColumns(20);
        noteContent.setRows(5);
        noteContent.setWrapStyleWord(true);
        noteContent.setBorder(null);

        jPanel1.setOpaque(false);

        editBtn.setBorder(null);
        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editIcon.png"))); // NOI18N
        editBtn.setToolTipText("home");
        editBtn.setGradientColor1(new java.awt.Color(43, 41, 48));
        editBtn.setGradientColor2(new java.awt.Color(43, 41, 48));
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        noteTitle.setFont(new java.awt.Font("Segoe Print", 1, 48)); // NOI18N
        noteTitle.setForeground(new java.awt.Color(204, 204, 204));
        noteTitle.setText("Title");

        deleteNoteBtn.setBorder(null);
        deleteNoteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tabler_trash.png"))); // NOI18N
        deleteNoteBtn.setToolTipText("home");
        deleteNoteBtn.setGradientColor1(new java.awt.Color(43, 41, 48));
        deleteNoteBtn.setGradientColor2(new java.awt.Color(43, 41, 48));
        deleteNoteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteNoteBtnActionPerformed(evt);
            }
        });

        tagsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noteTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tagsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteNoteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(noteTitle)
                        .addComponent(tagsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteNoteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(createdDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lastEditedDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(noteContent, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 85, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(createdDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lastEditedDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noteContent, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        //goto home
        HomeFrame.Instance.setPage(new NotePanel(this.note));
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteNoteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteNoteBtnActionPerformed
        
        String answer = JOptionPane.showInputDialog(this, "Enter delete to (delete) note: ");
        
        if(answer.toLowerCase().compareTo("delete") == 0)
        {
            note.Delete();
            NotesPreviewScrollPanel.Instance.loadNotes();   
        }
        
       
    }//GEN-LAST:event_deleteNoteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel createdDate;
    private javax.swing.JLabel createdDateLabel;
    private javax.swing.JPanel createdDatePanel;
    private customSwingComponents.GradientButton deleteNoteBtn;
    private customSwingComponents.GradientButton editBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lastEdited;
    private javax.swing.JPanel lastEditedDatePanel;
    private javax.swing.JLabel lastEditedLabel;
    private javax.swing.JTextArea noteContent;
    private javax.swing.JLabel noteTitle;
    private customSwingComponents.PanelBorder panelBorder1;
    private javax.swing.JComboBox<String> tagsComboBox;
    // End of variables declaration//GEN-END:variables

    
}


package paneltags;

import finalproject.NotesPreviewScrollPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PanelTags extends javax.swing.JPanel {

    private EventTags event;

    public static ArrayList<String> tags = new ArrayList<String>();
    private ArrayList<Color> tagsColor = new ArrayList<Color>(Arrays.asList(Color.gray, Color.yellow, Color.magenta, Color.cyan, Color.red, Color.green));

    public void addEventTags(EventTags event) {
        this.event = event;
        ((Item) getComponent(getComponentCount() - 1)).setEventTags(event);
    }

    public PanelTags() {
        initComponents();
        init();
    }

    private void init() {
        setBackground(Color.WHITE);
        setLayout(new WrapLayout(WrapLayout.LEFT));
        Item input = new Item("", Color.black);
        if (NotesPreviewScrollPanel.Instance != null) {
            NotesPreviewScrollPanel.Instance.loadNotes();
        }

        input.addEventKey(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                event.onKeyType(input, input.getText(), ke);
            }
        });
        input.addEventForInput(new EventInput() {
            @Override
            public void addItem(String text) {
                if (!tagsColor.isEmpty()) { // Ensure there is a color available
                    Color tagColor = tagsColor.removeLast();
                    createNewTag(text, tagColor);
                }
            }
        });
        add(input);
                



    }

    private void createNewTag(String text, Color color) {
        Item item = new Item(text, color);
        item.addEventMouse();

        item.setEventTags(event);
        item.addEventRemove(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (event.isRemoveAble(item, item.getText())) {
                    tagsColor.add(item.getTagColor());
                    remove(item);
                    tags.clear();
                    tags = getAllItem();

                    refresh();
                    //  event remove
                    event.onItemRemove(item, item.getText());
                    if (NotesPreviewScrollPanel.Instance != null) {
                        NotesPreviewScrollPanel.Instance.loadNotes();
                    }

                }
            }
        });
        item.addEventKey(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                //    event key
                event.onKeyType(item, item.getText(), ke);
            }
        });

        add(item, getComponentCount() - 1);
//       event.onAddItem(item, item.getText());
         if(!tags.contains(item.getText())) tags.add(item.getText());
        

        refresh();
        if (NotesPreviewScrollPanel.Instance != null) {
            NotesPreviewScrollPanel.Instance.loadNotes();
        }
    }

    public void loadTagsFromDB(ArrayList<String> tags) {
        this.tags = tags;
        for(int i = 0; i < tags.size(); i++){
            Color color = tagsColor.removeLast();
            createNewTag(tags.get(i), color);
        }
    }

    private boolean canAddItem(Item newItem) {
        Dimension totalSize = new Dimension(0, newItem.getPreferredSize().height);
        for (Component comp : getComponents()) {
            Dimension d = comp.getPreferredSize();
            totalSize.width += d.width;
            if (totalSize.width > getWidth()) {
                return false;
            }
        }
        return true;
    }

    private void refresh() {
        repaint();
        revalidate();
    }

    public ArrayList<String> getAllItem() {
        ArrayList<String> list = new ArrayList<>();
        for (Component com : getComponents()) {
            Item item = (Item) com;
            if (!item.isInput()) {
                list.add(item.getText());
            }
        }
        return list;
    }

    public List<String> getAllItemExit(Component exit) {
        List<String> list = new ArrayList<>();
        for (Component com : getComponents()) {
            Item item = (Item) com;
            if (!item.isInput() && com != exit) {
                list.add(item.getText());
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setForeground(new java.awt.Color(230, 224, 233));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        ((Item) getComponent(getComponentCount() - 1)).grabFocus();
    }//GEN-LAST:event_formMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

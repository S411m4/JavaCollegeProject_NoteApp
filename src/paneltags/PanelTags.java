package paneltags;

import DatabaseHelpers.DatabaseHelper;
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
    public static PanelTags instance;

    public static List<String> tags;

    private static ArrayList<Color> tagColors = new ArrayList<Color>(Arrays.asList(Color.red, Color.yellow, Color.green, Color.cyan, Color.pink, Color.magenta));

    public void addEventTags(EventTags event) {
        this.event = event;
        ((Item) getComponent(getComponentCount() - 1)).setEventTags(event);
    }

    public PanelTags() {
                instance = this;

        initComponents();
        init();
    }

    private void init() {
        setBackground(Color.WHITE);
        setLayout(new WrapLayout(WrapLayout.LEFT));
        Color tagColor = tagColors.removeLast();

        Item input = new Item("", tagColor);

        tags = getAllItem();

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
                if (!text.isEmpty() && tagColors.size() > 0) {
                    Color tagColor = tagColors.removeLast();
                    createTagItem(text, tagColor);
                }
            }
        });
        add(input);
    }

    
     public void createTagItem(String text, Color tagColor) {
        Item item = new Item(text, tagColor);
        item.addEventMouse();
        item.setEventTags(event);
        item.addEventRemove(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (event.isRemoveAble(item, item.getText())) {
                    tagColors.add(item.getTagColor()); // Re-add the color to the pool
                    remove(item);
                    refresh();
                }
            }
        });
        item.addEventKey(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                event.onKeyType(item, item.getText(), ke);
            }
        });
        add(item, getComponentCount() - 1); // Add before the input item
        refresh();
    }

    private void refresh() {
        revalidate();
        repaint();
    }

    public void loadTagsFromDB() {
        System.out.println("Loading tags: ");
        tags = DatabaseHelper.getAllTags();
        for (String tag : tags) {
            System.out.println(tag + ",");
            if (!tag.isEmpty() && tagColors.size() > 0) {
                Color tagColor = tagColors.removeLast();
                createTagItem(tag, tagColor);
                System.out.print("OK");
            }
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

    public List<String> getAllItem() {
        List<String> list = new ArrayList<>();
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

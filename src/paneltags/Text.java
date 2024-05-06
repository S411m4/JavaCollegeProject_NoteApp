
package paneltags;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;

public class Text extends JTextArea {

    public Text()
    {
        this.setForeground(Color.decode("#E6E0E9"));
    }
    @Override
    protected void processComponentKeyEvent(KeyEvent ke) {
        if (ke.getID() == KeyEvent.KEY_PRESSED && ke.getKeyCode() == KeyEvent.VK_TAB) {
            ke.consume();
            if (ke.isShiftDown()) {
                transferFocusBackward();
            } else {
                transferFocus();
            }
        } else {
            super.processComponentKeyEvent(ke);
        }
    }
    
    
}

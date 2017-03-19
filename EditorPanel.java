import javax.swing.*;
import java.awt.*;

/**
 * Created by dillonenge on 3/11/17.
 */
public class EditorPanel extends JScrollPane {
    static JTextArea jta = new JTextArea();
    public EditorPanel(){
        setPreferredSize(new Dimension((int)(SwingUI.unitWidth*2), (int)(SwingUI.unitHeight/3)));
        jta.setLineWrap(true);
        setViewportView(jta);
        createVerticalScrollBar();
    }
}

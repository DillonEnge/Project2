import javax.swing.*;
import java.awt.*;

/**
 * Created by dillonenge on 3/11/17.
 */
public class MessagesPanel extends JScrollPane {
    static JTextArea jta;
    public MessagesPanel(){
        setPreferredSize(new Dimension((int)(SwingUI.unitWidth*2), (int)(SwingUI.unitHeight/3)));
        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        setViewportView(jta);
        createVerticalScrollBar();
    }
}

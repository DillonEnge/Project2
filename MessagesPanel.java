import javax.swing.*;
import java.awt.*;

/**
 * Created by dillonenge on 3/11/17.
 */
public class MessagesPanel extends JScrollPane {
    public MessagesPanel(){
        setPreferredSize(new Dimension(SwingUI.unitWidth*2, SwingUI.unitHeight/3));
        JTextArea jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        setViewportView(jta);
        createVerticalScrollBar();
    }
}

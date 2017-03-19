import javax.swing.*;
import java.awt.*;

/**
 * Created by dillonenge on 3/11/17.
 */
public class DirectoryPanel extends JPanel{
    public DirectoryPanel(){

        JTreePanel.jPan.setBackground(Color.white);
        JTreePanel.jPan.setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(300, (int)SwingUI.unitHeight));
        JTreePanel.jPan.setPreferredSize(this.getPreferredSize());
        remove(JTreePanel.getJPanel());
        add(JTreePanel.getJPanel());
    }



}

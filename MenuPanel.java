import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by dillonenge on 3/11/17.
 */
public class MenuPanel extends JMenuBar {
    public MenuPanel(){
        setPreferredSize(new Dimension(SwingUI.unitWidth, 20));
        setPreferredSize(this.getPreferredSize());
        JMenu file = new JMenu("File");
        JMenuItem open = file.add("Open SQL script");
        JMenuItem run = file.add("Run SQL script");
        JMenuItem save = file.add("Save SQL script");
        JMenu edit = new JMenu("Edit");
        JMenuItem copy = edit.add("Copy");
        JMenuItem paste = edit.add("Paste");
        JMenuItem pref = edit.add("Preferences");
        JMenu help = new JMenu("Help");
        JMenuItem about = help.add("About");
        add(file);
        add(edit);
        add(help);

    }
}

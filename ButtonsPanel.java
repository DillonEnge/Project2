import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by dillonenge on 3/11/17.
 */
public class ButtonsPanel extends JPanel {
    public ButtonsPanel() {
        BufferedImage restoreIcon = null;
        BufferedImage minimizeIcon = null;
        BufferedImage closeIcon = null;
        try {
            restoreIcon = ImageIO.read(new File("Restore.png"));
            minimizeIcon = ImageIO.read(new File("Minimize.png"));
            closeIcon = ImageIO.read(new File("Error Icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton restore = new JButton(SwingUI.scale(new ImageIcon(restoreIcon)));
        JButton minimize = new JButton(SwingUI.scale(new ImageIcon(minimizeIcon)));
        JButton close = new JButton(SwingUI.scale(new ImageIcon(closeIcon)));
        minimize.addActionListener(e -> SwingUI.jf.setState(Frame.ICONIFIED));
        close.addActionListener(e -> System.exit(0));

        add(restore);
        add(minimize);
        add(close);

        setBackground(Color.black);
        setPreferredSize(new Dimension(SwingUI.unitWidth, 35));


    }
}

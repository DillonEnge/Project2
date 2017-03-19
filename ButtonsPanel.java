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
        restore.addActionListener(e -> {
            if(!SwingUI.fullscreen) {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double screenWidth = screenSize.getWidth();
                double screenHeight = screenSize.getHeight();
                SwingUI.unitHeight = screenHeight / 3 - 30;
                SwingUI.unitWidth = screenWidth / 3;
                SwingUI.fullscreen = true;
                SwingUI.jf.setExtendedState(JFrame.MAXIMIZED_BOTH);

            } else {
                SwingUI.unitHeight = 600;
                SwingUI.unitWidth = 300;
                SwingUI.fullscreen = false;
                SwingUI.jf.pack();
                SwingUI.jf.revalidate();
                SwingUI.jf.repaint();
            }
        });
        minimize.addActionListener(e -> SwingUI.jf.setState(Frame.ICONIFIED));
        close.addActionListener(e -> System.exit(0));

        add(restore);
        add(minimize);
        add(close);
        setLayout(new FlowLayout(FlowLayout.RIGHT)); //yeah ill show you real quick
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension((int)SwingUI.unitWidth, 35));


    }
}

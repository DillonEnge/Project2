import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by dillonenge on 3/11/17.
 */
public class IconsPanel extends JPanel {
    public static JButton allCommands;
    public IconsPanel() {
        BufferedImage oneCommandIcon = null;
        BufferedImage allCommandsIcon = null;
        BufferedImage sweepIcon = null;
        try {
            oneCommandIcon = ImageIO.read(new File("1 command.png"));
            allCommandsIcon = ImageIO.read(new File("AllCommands.png"));
            sweepIcon = ImageIO.read(new File("sweep.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton oneCommand = new JButton(SwingUI.scale(new ImageIcon(oneCommandIcon)));
        allCommands = new JButton(SwingUI.scale(new ImageIcon(allCommandsIcon)));
        JButton sweep = new JButton(SwingUI.scale(new ImageIcon(sweepIcon)));
        oneCommand.addActionListener(e -> {
            String action = EditorPanel.jta.getSelectedText();
            System.out.println(action);
            SwingUI.parseForCommand(action);
        });
        allCommands.addActionListener(e -> {
            int count = 0;

            Scanner sc = new Scanner(EditorPanel.jta.getText().trim());
            while (sc.hasNextLine()) {
                if (!sc.nextLine().equals("")) {
                    count++;
                }
            }
            Scanner sc2 = new Scanner(EditorPanel.jta.getText().trim());

            SwingUI.actions = new String[count];
            for (int i = 0; sc2.hasNextLine(); i++) {
                SwingUI.action = sc2.nextLine();
                if (!SwingUI.action.equals("")) {
                    SwingUI.actions[i] = SwingUI.action;
                } else {
                    i--;
                }
            }
            for (int i = 0; i < SwingUI.actions.length; i++) {
                System.out.println(SwingUI.actions[i]);
                SwingUI.parseForCommand(SwingUI.actions[i]);
            }
        });
        sweep.addActionListener(e -> EditorPanel.jta.setText(""));
        add(oneCommand);
        add(allCommands);
        add(sweep);
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension((int)SwingUI.unitWidth, 35));
    }
}


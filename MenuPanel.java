import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

/**
 * Created by dillonenge on 3/11/17.
 */
public class MenuPanel extends JMenuBar {

    public MenuPanel(){
        setPreferredSize(new Dimension(300, 20));
        setPreferredSize(this.getPreferredSize());
        JMenu file = new JMenu("File");
        JMenuItem open = file.add("Open SQL script");
        JMenuItem run = file.add("Run SQL script");
        JMenuItem save = file.add("Save SQL script");
        open.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog(SwingUI.jf, "Which script would you like to open?");
            File openFile = new File(fileName + ".txt");
            EditorPanel.jta.setText("");
            try {
                Scanner fileScanner = new Scanner(openFile);
                while (fileScanner.hasNextLine()){
                    EditorPanel.jta.append(fileScanner.nextLine() + "\n");
                }
            } catch(FileNotFoundException e1){
                e1.printStackTrace();
            }
        });
        run.addActionListener(e -> {
            IconsPanel.allCommands.doClick();
        });
        save.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog(SwingUI.jf, "What would you like to name your script?");
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
            File actionFile = new File(fileName + ".txt");
            try {
                FileWriter fw = new FileWriter(actionFile, true);
                for (int i = 0; i < SwingUI.actions.length; i++) {
                    fw.write(SwingUI.actions[i] + "\n");
                }
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        JMenu edit = new JMenu("Edit");
        JMenuItem copy = edit.add("Copy");
        JMenuItem paste = edit.add("Paste");
        JMenuItem pref = edit.add("Preferences");
        JMenu help = new JMenu("Help");
        JMenuItem about = help.add("About");
        copy.addActionListener(e -> {
            StringSelection string = new StringSelection(EditorPanel.jta.getSelectedText());
            Clipboard cB = Toolkit.getDefaultToolkit().getSystemClipboard();
            cB.setContents(string, null);
        });
        paste.addActionListener(e -> {
            Clipboard cB = Toolkit.getDefaultToolkit().getSystemClipboard();
            try {
                String string = (String) cB.getData(DataFlavor.stringFlavor);
                EditorPanel.jta.setText(string);
            } catch (UnsupportedFlavorException e1){
                e1.printStackTrace();
            } catch (IOException e1){
                e1.printStackTrace();
            }
        });
        about.addActionListener(e -> {
            JOptionPane.showMessageDialog(SwingUI.jf, "I'M MR. MEESEEKS LOOK AT MEEEEE!");
                }
        );
        add(file);
        add(edit);
        add(help);

    }
}

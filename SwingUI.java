import javax.swing.*;
import java.awt.*;

/**
 * Created by dillonenge on 3/11/17.
 */
public class SwingUI {
    public static void main(String[] args){
        JFrame jf = new JFrame("SQL Workbench");
        jf.setLayout(new GridBagLayout());
        JPanel AllPanels = new JPanel(new GridBagLayout());
        JPanel TopPanels = new JPanel();
        JPanel BottomPanels = new JPanel();

        TopPanels.setBackground(Color.blue);

        JPanel TextPanels = new JPanel();


        TextPanels.setBackground(Color.black);
        GridBagConstraints gbcTop = new GridBagConstraints();
        GridBagConstraints gbcSide = new GridBagConstraints();
        GridBagConstraints gbcText = new GridBagConstraints();
        GridBagConstraints gbcTotal = new GridBagConstraints();
        gbcTop.gridheight = 1;
        gbcTop.gridwidth = 3;


        gbcText.gridheight = 3;
        gbcText.gridwidth = 2;


        gbcTotal.gridheight = 4;
        gbcTotal.gridwidth = 3;


        TopPanels.add(new MenuPanel());
        TopPanels.add(new IconsPanel());
        TopPanels.add(new ButtonsPanel());

        TextPanels.add(new EditorPanel());
        TextPanels.add(new SelectPanel());
        TextPanels.add(new MessagesPanel());


        BottomPanels.add(new DirectoryPanel());
        BottomPanels.add(TextPanels);

        AllPanels.add(TopPanels, gbcTop);
        AllPanels.add(BottomPanels, gbcText);

        jf.add(AllPanels, gbcTotal);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.pack();
    }























}

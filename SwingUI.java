import javax.swing.*;
import java.awt.*;

/**
 * Created by dillonenge on 3/11/17.
 */
public class SwingUI {
    static JFrame jf = new JFrame("SQL Workbench");
    static JPanel AllPanels = new JPanel(new GridBagLayout());
    static JPanel TopPanels = new JPanel();
    static JPanel BottomPanels = new JPanel();
    static JPanel TextPanels = new JPanel();
    static int unitWidth = 300;
    static int unitHeight = 600;

    public static void main(String[] args){
        jf.setLayout(new GridBagLayout());



        TopPanels.setLayout(new BoxLayout(TopPanels ,BoxLayout.X_AXIS));

        TopPanels.add(new MenuPanel());
        TopPanels.add(new IconsPanel());
        TopPanels.add(new ButtonsPanel());

        TextPanels.setLayout(new BoxLayout(TextPanels, BoxLayout.Y_AXIS));

        TextPanels.add(new EditorPanel());
        TextPanels.add(new SelectPanel());
        TextPanels.add(new MessagesPanel());

        BottomPanels.setLayout(new BoxLayout(BottomPanels, BoxLayout.X_AXIS));

        BottomPanels.add(new DirectoryPanel());
        BottomPanels.add(TextPanels);

        AllPanels.setLayout(new BoxLayout(AllPanels, BoxLayout.Y_AXIS));

        AllPanels.add(TopPanels);
        AllPanels.add(BottomPanels);

        jf.add(AllPanels);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.pack();
        //jf.setState(Frame.ICONIFIED);

    }
    public static ImageIcon scale(ImageIcon picture){
        Image image = picture.getImage();
        Image transformedImage = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
        picture = new ImageIcon(transformedImage);
        return picture;
    }























}

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by dillonenge on 3/11/17.
 */
public class SwingUI {
    static JFrame jf = new JFrame("SQL Workbench");
    static JPanel AllPanels = new JPanel(new GridBagLayout());
    static JPanel TopPanels = new JPanel();
    static JPanel BottomPanels = new JPanel();
    static JPanel TextPanels = new JPanel();
    static double unitWidth = 300;
    static double unitHeight = 600;
    static String[] actions;
    static String action;
    static boolean fullscreen = false;

    public static void main(String[] args){
        subMain();
    }

    public static void createJTP(){
        JTreePanel.makeJTreePanel();
        JTreePanel.jPan.setBackground(Color.white);
        JTreePanel.jPan.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTreePanel.jPan.setPreferredSize(new Dimension(300, (int)SwingUI.unitHeight));
    }

    public static void subMain(){
        jf.setLayout(new GridBagLayout());
        


        TopPanels.setLayout(new GridBagLayout());

        TopPanels.add(new MenuPanel());
        TopPanels.add(new IconsPanel());
        TopPanels.add(new ButtonsPanel());

        TextPanels.setLayout(new BoxLayout(TextPanels, BoxLayout.Y_AXIS));

        TextPanels.add(new EditorPanel());
        TextPanels.add(new SelectPanel());
        TextPanels.add(new MessagesPanel());

        BottomPanels.setLayout(new GridBagLayout());
        createJTP();
        BottomPanels.add(JTreePanel.getJPanel());
        BottomPanels.add(TextPanels);

        AllPanels.setLayout(new BoxLayout(AllPanels, BoxLayout.Y_AXIS));

        AllPanels.add(TopPanels);
        AllPanels.add(BottomPanels);

        jf.add(AllPanels);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.pack();
    }
    public static ImageIcon scale(ImageIcon picture){
        Image image = picture.getImage();
        Image transformedImage = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
        picture = new ImageIcon(transformedImage);
        return picture;
    }

    public static String parseForCommand(String command){
        Scanner sc = new Scanner(command);
        String db = null;
        String firstCommand = sc.next();
        String secondCommand = sc.next();
        switch(firstCommand){
            case "CREATE":
                if(secondCommand.equals("DATABASE")){
                    db = sc.next();
                    DBCommands.createDatabase(db);
                    JTreePanel.addDirectory(db);
                    BottomPanels.revalidate();
                    BottomPanels.repaint();

                } else if(secondCommand.equals("TABLE")){
                    String table = sc.next();
                    DBCommands.createTable(table);
                    JTreePanel.addTable(db, table);
                    BottomPanels.revalidate();
                    BottomPanels.repaint();
                }
                break;
            case "DROP":
                if(secondCommand.equals("DATABASE")){
                    DBCommands.dropDatabase(sc.next());
                } else if(secondCommand.equals("TABLE")){
                    DBCommands.dropTable(sc.next());
                }
                break;
            case "INSERT":
                break;
            case "SELECT":
                break;
            case "DELETE":
                break;
            case "UPDATE":
                break;
            default:
                break;
        }

        return null;
    }























}

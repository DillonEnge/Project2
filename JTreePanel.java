import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
public class JTreePanel {

	private JPanel jPan = new JPanel();
	private JTree dir;
	private String[][] branches = {{"Thin Crust"},{"Cheese","Pep","Bacon"},{"Thick Crust"},{"Ham","Pineapple","Sausage"}}; //edit this String Array and then call the constructor to update 
																															// the directory
	// copy pasta to check another run of the loops-- ,{"Cheese Crust"},{"Ham","Pineapple","Sausage"}
	
	public JTreePanel(){
		super();// no idea if this is important or not
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
		
		for(int n = 0;n<branches.length;n=n+2){									//first for loop, creates the main branch(Directories) using the 0,2,4... spaces in the string array
			DefaultMutableTreeNode sub1 = new DefaultMutableTreeNode(
				new DefaultMutableTreeNode(branches[n][0]));
		
			
			//add an if statement to check if there are tables in the next part of the array
			for(int i=0;i<branches[1].length;i++){								//second loop, creates the second line(Tables) using the odd spaces in the array 1,3,5..
				sub1.add(new DefaultMutableTreeNode(branches[n+1][i]));
			}
			root.add(sub1);
		}
		
		
		dir = new JTree(root);
		dir.setRootVisible(false);
		dir.setShowsRootHandles(true);
		jPan.add(dir);
	}
	
	public JPanel getJPanel(){
		
		return jPan;
	}
}

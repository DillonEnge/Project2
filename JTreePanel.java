import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
public class JTreePanel {

	private static JPanel jPan = new JPanel();
	private static JTree dir;
	private static String[][] branches = new String[0][0]; //edit this String Array and then call the constructor to update 
																															// the directory
	// copy pasta to check another run of the loops-- ,{"Cheese Crust"},{"Ham","Pineapple","Sausage"}
	
	public static void makeJTreePanel(){
		//super();// no idea if this is important or not
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Database");
		
		for(int i = 0;i<branches.length;i++){									//first for loop, creates the main branch(Directories)
			DefaultMutableTreeNode sub1 = new DefaultMutableTreeNode(
				new DefaultMutableTreeNode(branches[i][0]));
		
										
				for(int n=1;n<branches[i].length;n++){								//second loop, creates the second line(Tables)
					sub1.add(new DefaultMutableTreeNode(branches[i][n]));
				}	
			root.add(sub1);
		}
		
		
		dir = new JTree(root);
		dir.setRootVisible(true);
		dir.setShowsRootHandles(true);
		jPan.add(dir);
	}
	
	public static JPanel getJPanel(){
		
		return jPan;
	}
	
	// Using these we assume it has gotten past the other Error checks and will work
	
	public static void addDirectory(String dirName){
		jPan.remove(dir);
		String[][] temp = new String[branches.length+1][];
		for(int i = 0; i<branches.length;i++){
			temp[i] = new String[branches[i].length];
			for(int n = 0;n<branches[i].length;n++){
				temp[i][n] = branches[i][n];
			}
		}
		temp[branches.length] = new String[1]; //+1
		temp[branches.length][0] = dirName;
		//temp[branches.length][1] = "temp"; // makes it so the node is a folder // if you uncomment this add 1 to temp length
		
		branches = temp;
		
		makeJTreePanel();
		
		
		
		
	}
	
	
	
	
	
	public static void addTable(String dirName, String tableName){
		jPan.remove(dir);
		int[] index = new int[2];
		for(int i = 0; i<branches.length;i++){
				if(branches[i][0].equals(dirName)){
					index[0]=i;
					index[1]=0;
			}
		}
		
		String[][] temp = new String[branches.length][];
		
		for(int i=0; i<index[0];i++){
			temp[i] = new String[branches[i].length];
			for(int n = 0; n<branches[i].length;n++){
				temp[i][n] = branches[i][n];
			}
		}
		
		temp[index[0]] = new String[branches[index[0]].length+1];
		for(int i = 0;i<branches[index[0]].length;i++){
			temp[index[0]][i]=branches[index[0]][i];
		}
		temp[index[0]][branches[index[0]].length]=tableName;
		
		for(int i = index[0]+1; i<branches.length;i++){
			temp[i] = new String[branches[i].length];
			for(int n = 0; n<branches[i].length;n++){
				temp[i][n] = branches[i][n];
			}
		}
		
		branches = temp;
		
		makeJTreePanel();
		
	}
	
	
	
	
	
	// for this to work the directory must be there
	public static void removeDirectory(String dirName){
		jPan.remove(dir);
		int[] index = new int[2];
		for(int i = 0; i<branches.length;i++){
				if(branches[i][0].equals(dirName)){
						index[0]=i;
						index[1]=0;
						break;
				}
		}
		String[][] temp = new String[branches.length-1][];
		
		for(int i=0; i<index[0];i++){
			temp[i] = new String[branches[i].length];
			for(int n = 0; n<branches[i].length;n++){
				temp[i][n] = branches[i][n];
			}
		}
		
		for(int i = index[0]; i<temp.length;i++){
			temp[i] = new String[branches[i+1].length];
			for(int n = 0; n<branches[i+1].length;n++){
				temp[i][n] = branches[i+1][n];
			}
		}
		branches = temp;
		makeJTreePanel();
		
	}





	public static void removeTable(String dirName, String tableName){
		jPan.remove(dir);
		int[] index = new int[2];
		for(int i = 0; i<branches.length;i++){
			if(branches[i][0].equals(dirName)){
				for(int n = 1;n<branches[i].length;n++){
					if(branches[i][n].equals(tableName)){
						index[0]=i;
						index[1]=n;
					}
				}
			}
		}
		String[][] temp = new String[branches.length][];
		
		for(int i=0; i<index[0];i++){
			temp[i] = new String[branches[i].length];
			for(int n = 0; n<branches[i].length;n++){
				temp[i][n] = branches[i][n];
			}
		}
		
		
		temp[index[0]] = new String[branches[index[0]].length-1];
		for(int n = 0;n<index[1];n++){
			temp[index[0]][n]=branches[index[0]][n];
		}
		for(int n = index[1];n<temp[index[0]].length;n++){
			temp[index[0]][n]=branches[index[0]][n+1];
		}
		
		
		for(int i = index[0]+1; i<branches.length;i++){
			temp[i] = new String[branches[i].length];
			for(int n = 0; n<branches[i].length;n++){
				temp[i][n] = branches[i][n];
			}
		}
		
		
		
		branches = temp;
		makeJTreePanel();
	}
}
/*
for(int i = 0; i<branches.length;i++){
	for(int n = 0;n<branches[i].length;n++){
		
	}
}
*/
import java.awt.BorderLayout;

import javax.swing.*;
public class SQLWorkbench {
	public static void main(String[] args)
	{
		JFrame mainWindow = new JFrame ("My SQL Workbench");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//3

		
		
		
		JTreePanel tree = new JTreePanel();
		
		mainWindow.add(tree.getJPanel());
		
		
		mainWindow.setVisible(true);
		mainWindow.pack();
		
		
		
		
	}
	
}

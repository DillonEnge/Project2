import java.awt.BorderLayout;

import javax.swing.*;
public class SQLWorkbench {
	public static void main(String[] args)
	{
		JFrame mainWindow = new JFrame ("My SQL Workbench");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//3

		
		
		JTreePanel.makeJTreePanel();
		JTreePanel.addDirectory("Thick");
		JTreePanel.addTable("Thick", "Cheese");
		JTreePanel.addTable("Thick", "Pep");
		JTreePanel.addTable("Thick", "Bacon");
		JTreePanel.addDirectory("Thin");
		JTreePanel.addTable("Thin", "Ham");
		JTreePanel.addTable("Thin", "Pine-apple");
		JTreePanel.addTable("Thin", "Sausage");
		JTreePanel.addDirectory("Cheese");
		JTreePanel.addDirectory("Hawian");
		JTreePanel.addDirectory("Bras");
		JTreePanel.addTable("Bras", "Strapless");
		JTreePanel.addTable("Bras", "Sports");
		JTreePanel.addTable("Bras", "Bralette");
		JTreePanel.addTable("Hawian", "Moana");
		JTreePanel.addTable("Hawian", "Maui");
		JTreePanel.addTable("Hawian", "Hay Hay");
		JTreePanel.removeDirectory("Cheese");
		JTreePanel.removeTable("Hawian", "Hay Hay");
		
		mainWindow.add(JTreePanel.getJPanel());
		
		
		mainWindow.setVisible(true);
		mainWindow.pack();
		
		
		
		
	}
	
}

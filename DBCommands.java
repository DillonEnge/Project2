import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DBCommands {

	public static void main(String[] args) {
		createDatabase("college");
		createTable("college", "students");
		insert("college", "students", "Dillon Enge");
		insert("college", "students", "Connor Enge");
		insert("college", "students", "Halle Enge");
		insert("college", "students", "Belinda Enge");
		insert("college", "students", "Trevor Enge");
		select("college", "students");
	}
	/******************************************
	  CREATE DATABASE method - mETHOD THAT creates a database by creating
	  a file in a directory.
	  Author: Dillon Enge
	  
	*******************************************/
	public static boolean createDatabase(String dbName) {
		File database = new File(dbName);

		database.mkdir();
		log("college", "status", ("Database " + dbName + " created." + "\n" + "(CREATE DATABASE " + dbName + ")\n"));
		return true;
	}
	/******************************************
	  CREATE DATABASE method - mETHOD THAT creates a database by creating
	  a file in a directory.
	  Author: Dillon Enge
	  
	*******************************************/
	public static boolean dropDatabase(String dbName) {
		File database = new File(dbName);

		database.delete();
		log("college", "status", ("Database " + dbName + " dropped." + "\n" + "(DROP DATABASE " + dbName + ")\n"));
		return true;
	}
	/******************************************
	  CREATE DATABASE method - mETHOD THAT creates a database by creating
	  a file in a directory.
	  Author:Dillon Enge
	  
	*******************************************/
	public static boolean createTable(String dbName, String tableName) {
		File table = new File(dbName + "/" + tableName + ".txt");
		try {
			table.createNewFile();
			log("college", "status",
					("Table " + tableName + " created." + "\n" + "(CREATE TABLE " + tableName + ")\n"));
			return true;
		} catch (IOException e) {
			log("college", "status", ("Table " + tableName + " could not be created.\n" + e.getMessage()));

			return false;
		}
	}
	/******************************************
	  CREATE DATABASE method - mETHOD THAT creates a database by creating
	  a file in a directory.
	  Author: Dillon Enge
	  
	*******************************************/
	public static boolean dropTable(String dbName, String tableName) {

		File table = new File(dbName + "/" + tableName + ".txt");
		table.delete();
		log("college", "status", ("Table " + tableName + " dropped." + "\n" + "(DROP TABLE " + tableName + ")\n"));
		return true;
	}
	/******************************************
	  CREATE DATABASE method - mETHOD THAT creates a database by creating
	  a file in a directory.
	  Author: Dillon Enge
	  
	     
	*******************************************/
	public static boolean insert(String dbName, String tableName, String input) {
		File table = new File(dbName + "/" + tableName + ".txt");
		try {
			FileWriter fw = new FileWriter(table, true);
			fw.write(input + "\n");
			fw.close();
			log("college", "status", ("Row " + input + " inserted into " + dbName + "." + tableName + " table" + "\n"
					+ "(INSERT \"" + input + "\" INTO " + dbName + "." + tableName + ")\n"));
			return true;
		} catch (IOException e) {
			log("college", "status", ("String " + input + " could not be inserted.\n" + e.getMessage()));

			return false;
		}
	}
	/******************************************
	  CREATE DATABASE method - mETHOD THAT creates a database by creating
	  a file in a directory.
	  Author: Dillon Enge
	  
	*******************************************/
	public static boolean select(String dbName, String tableName) {
		File table = new File(dbName + "/" + tableName + ".txt");
		try {
			Scanner tableScanner = new Scanner(table);
			log("college", "status", "Rows selected for " + dbName + "." + tableName);
			while (tableScanner.hasNextLine()) {

				log("college", "status", tableScanner.nextLine());

			}
			tableScanner.close();
			log("college", "status", "(SELECT " + "*" + " FROM " + dbName + "." + tableName + ")");
			return true;
		} catch (FileNotFoundException e) {
			log("college", "error", "File " + tableName + " not found." + e.getMessage());
			return false;
		}

	}
	/******************************************
	  CREATE DATABASE method - mETHOD THAT creates a database by creating
	  a file in a directory.
	  Author: Dillon Enge
	  
	  
	*******************************************/
	public static boolean selectWhere(String dbName, String tableName, String input) {
		File table = new File(dbName + "/" + tableName + ".txt");
		String line = "";
		try {
			Scanner tableScanner = new Scanner(table);
			log("college", "status", "Rows selected for " + dbName + "." + tableName);
			while (tableScanner.hasNextLine()) {

				line = tableScanner.nextLine();
				if (line.equals(input)) {
					log("college", "status", "Row FOUND for " + dbName + "." + tableName);
					break;
				}

			}
			log("college", "status", "(SELECT " + input + " FROM " + dbName + "." + tableName);
			tableScanner.close();
			return true;
		} catch (FileNotFoundException e) {
			log("college", "error", "File " + tableName + " not found." + e.getMessage());
			return false;
		}

	}
	/******************************************
	  CREATE DATABASE method - mETHOD THAT creates a database by creating
	  a file in a directory.
	  Author: Dillon Enge
	  Date: 
	  
	*******************************************/
	public static boolean delete(String dbName, String tableName) {
		dropTable(dbName, tableName);
		createTable(dbName, tableName);
		return true;
	}
	/******************************************
	  CREATE DATABASE method - mETHOD THAT creates a database by creating
	  a file in a directory.
	  Author: Dillon Enge
	  Date: 
	  
	*******************************************/
	public static void log(String dbName, String logName, String input) {
		File log = new File(dbName + "/" + logName + ".txt");
		try {
			FileWriter fw = new FileWriter(log, true);
			fw.write(input + "\n");
			fw.close();
		} catch (IOException e) {
			log("college", "error", e.toString());
			e.printStackTrace();
		}
	}
}

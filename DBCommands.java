import java.io.*;
import java.util.Scanner;

public class DBCommands {
	

	public static void Run(){
		
		File error = new File("Error_Log");
		File status = new File("Status_Log");
		String[] commandArray = null;
		String commandLine = "";
		String command = "";
		Scanner scan = null;
		
		try{
			if(!error.exists()){
				error.createNewFile();
			}
			if(!status.exists()){
				status.createNewFile();
			}
			commandArray = readTable("DBUSER.txt", "","");
			for(int i=0;i<commandArray.length;i++){
				//System.out.println(commandArray[i]);
				commandLine = commandArray[i];
				scan = new Scanner(commandLine);
				if(scan.hasNext()){
					command = scan.next();
					//System.out.print(command+"////");
	// Create Database and Table ----------------------------------------------------------------
					if(command.equalsIgnoreCase("Create")){
						if(scan.hasNext()){
							command = scan.next();
						//Create Database	---------------------------------------------------------------
							if(command.equalsIgnoreCase("Database")){
								if(scan.hasNext()){
									command = scan.next();
									if(createDatabase(command,commandArray[i]))
										sendStatus("Command "+commandArray[i]+" complete");
								}
								else{
									sendError("Could not parse",commandArray[i]);
								}
							}
						//create table	---------------------------------------------------------------	
							else if(command.equalsIgnoreCase("table")){
								if(scan.hasNext()){
									command = scan.next();
									String path = command.substring(0,command.indexOf("."));
									String tableName = command.substring(command.indexOf(".")+1);
									if(createTable(tableName,path,commandArray[i]))
										sendStatus("Command "+commandArray[i]+" complete");
								}
								else{
									sendError("Could not parse",commandArray[i]);
								}
							}
						// error	---------------------------------------------------------------	
							else{
								sendError("Could not parse",commandArray[i]);
							}
						}
					}
	// Drop Database and Table -------------------------------------------------------------------------				
					else if(command.equalsIgnoreCase("Drop")){
						if(scan.hasNext()){
							command = scan.next();
						//drop database	---------------------------------------------------------------
							if(command.equalsIgnoreCase("Database")){
								if(scan.hasNext()){
									command = scan.next();
									if(dropDatabase(command,commandArray[i]))
										sendStatus("Command "+commandArray[i]+" complete");
								}
								else{
									sendError("Could not parse",commandArray[i]);
								}
							}
						// drop table---------------------------------------------------------------
							else if(command.equalsIgnoreCase("table")){
								if(scan.hasNext()){
									command = scan.next();
									String path = command.substring(0,command.indexOf("."));
									String tableName = command.substring(command.indexOf(".")+1);
									if(dropTable(tableName,path,commandArray[i]))
										sendStatus("Command "+commandArray[i]+" complete");
								}
								else{
									sendError("Could not parse",commandArray[i]);
								}
							}
						// error---------------------------------------------------------------
							else{
								sendError("Could not parse",commandArray[i]);
							}
						}
						else{
							sendError("Could not parse",commandArray[i]);
						}
					}
	// Select select from -----------------------------------------------------------------------------				
					else if(command.equalsIgnoreCase("Select")){
						if(scan.hasNext()){
							scan.next();
							command = scan.next();
							if(command.equalsIgnoreCase("From")){
								if(scan.hasNext()){
									command = scan.next();
									String path = command.substring(0,command.indexOf("."));
									String tableName = command.substring(command.indexOf(".")+1);
						// Diverting from selecting all to selecting a specific line
									if(scan.hasNext()){
										scan.next(); scan.next(); scan.next();
										String selection = "";
										while(scan.hasNext()){
											selection = selection+" "+scan.next();
										}
										selection = selection.trim();
										if(select(tableName, path, selection,commandArray[i])){
											sendStatus(selection+" found in "+tableName);
										}
									}
						//Select all-------------------------------------------------------------------------------		
									else{
										String[] table = readTable(tableName, path,commandArray[i]);
										for(int n=0;n<table.length;n++){
											sendStatus(table[n]+" found in "+tableName+" "+commandArray[i]);
										}
									}
								}
								else
									sendError("Could not parse",commandArray[i]);
							}
							else
								sendError("Could not parse",commandArray[i]);
						}
						else
							sendError("Could not parse",commandArray[i]);
					}
	// delete delete from --------------------------------------------------------------------------				
					else if(command.equalsIgnoreCase("Delete")){
						if(scan.hasNext()){
							scan.hasNext();
							String selection = "";
							scan.next();
							command = scan.next();
							String path = command.substring(0,command.indexOf("."));
							String tableName = command.substring(command.indexOf(".")+1);
					//looking to see if its delete from	-------------------------------------------------
							if(scan.hasNext()){
								scan.next(); scan.next(); scan.next();
								selection = scan.next();
								while(scan.hasNext()){
									selection = selection+" "+scan.next();
									selection = selection.trim();
								}
								if(deleteFrom(tableName, path, selection, commandArray[i])){
									sendStatus(selection+" deleted from "+tableName);
								}
							}
							
					//regular delete-------------------------------------------------------------		
							else if(delete(tableName, path, commandArray[i])){
									sendStatus(tableName+" contents erased "+commandArray[i]);
								}
							else
								sendError("Could not parse",commandArray[i]);
						}
						else
							sendError("Could not parse",commandArray[i]);	
						
					}
	//Update ------------------------------------------------------------------------------------- 				
					else if(command.equalsIgnoreCase("Update")){
						if(scan.hasNext()){
							String insertion = "";
							String selection = "";
							command = scan.next();
							String path = command.substring(0,command.indexOf("."));
							String tableName = command.substring(command.indexOf(".")+1);
							scan.next(); scan.next(); scan.next();
							command = scan.next();
							while(!command.equalsIgnoreCase("Where")&&scan.hasNext()){
								insertion = insertion+" "+command;
								command = scan.next();
								insertion = insertion.trim();
							}
							scan.next(); scan.next(); 
							command = scan.next();
							selection = selection+" "+command;
							selection = selection.trim();
							while(scan.hasNext()){
								command = scan.next();
								selection = selection+" "+command;
								selection = selection.trim();
							}
							if(update(tableName, path, selection, insertion, commandArray[i])){
								sendStatus("Updated "+selection+" to "+insertion+" in "+tableName+""+commandArray[i]);
							}
							
						}
						else
							sendError("Could not parse",commandArray[i]);
					}
	//Insert------------------------------------------------------------------------------------------ 				
					else if(command.equalsIgnoreCase("Insert")){
						String insertion = "";
						if(scan.hasNext()){
							insertion = scan.next();
							command = scan.next();
							while(!command.equalsIgnoreCase("INTO")&&scan.hasNext()){
								insertion = insertion+" "+command;
								command = scan.next();
								insertion = insertion.trim();
							}
							command = scan.next();
							String path = command.substring(0,command.indexOf("."));
							String tableName = command.substring(command.indexOf(".")+1);
							if(insert(tableName, path, insertion, commandArray[i])){
								sendStatus(insertion+" inserted in "+tableName+" "+commandArray[i]);
							}
						}
						else
							sendError("Could not parse",commandArray[i]);
					}
	// error check ----------------------------------------------------------------------------------				
					else{
						sendError("Could not parse",commandArray[i]);
					}
					
				}
				else{
					sendError("Could not parse",commandArray[i]);
				}
			}
			
		}
		catch(Exception e){
			System.out.println("error"+e.getMessage());
			
		}

		
	}
	
	
	public static boolean createDatabase(String dbName,String command)
	{
	   try
	   {
	     // create File object for dbName
	     File dataBase = new File(dbName);
	     
	     if(dataBase.mkdirs()){
	    	 
	     }
	     else{	 
	    	 sendError("Database "+dbName+" already exists",command);
	    	 return false;
	     }
	     
	     
	     // return true; if it works
	     System.out.println("Database "+dbName+" created.");
	     return true;
	   }
	   catch (Exception e)   // catch error
	   {
	     // return true; if it works
	     sendError("Database "+dbName+" could not be created."+e.getMessage(),command);
	     return false;
	   } // end catch
	} // end createDatabase
	
	
	public static boolean createTable(String tableName,String dest,String command){
	
		
		
		File table = new File(dest+"/"+tableName);
		try{
			if(table.exists()){
				table.createNewFile();	
			}
			else{
				sendError("Table "+tableName+" already exists",command);
		    	return false;
			}
	
		return true;
		
		}catch(Exception e){
			
			sendError("Table "+tableName+" could not be created."+e.getMessage(),command);
			return false;	
		
		}
	}	

	
	public static boolean dropTable(String tableName, String path,String command){
		File table = new File(path+"/"+tableName);
		try{
			table.delete();
			if(table.exists()){
				sendError("delete unsuccsseful",command);
				return false;
			}
				
			return true;
		}catch(Exception e){
			sendError("table did not drop"+e.getMessage(),command);
		return false;
		}
	}

	
	public static boolean dropDatabase(String dbName,String command){
			File db = new File(dbName);
			File[] files = new File(dbName).listFiles();
		try{
			if(db.isDirectory()){
				for(int i=0;i<files.length;i++){
					db.delete();
				}
			}
			if(db.delete()){
				
			}
			else{
				sendError("something went wrong",command);
				return false;
			}
			
		return true;	
		}catch(Exception e){
			sendError("Drop failed"+e.getMessage(),command);
			return false;	
		}
		
	}

	
	public static boolean insert(String tableName, String path, String insertion,String command){
		BufferedWriter bWrite = null;
		FileWriter fWrite = null;
		insertion = insertion.substring(1,insertion.length()-1);
		try{
			if(!path.equals("")){
				fWrite = new FileWriter(path+"/"+tableName,true);
			}
			else{
				fWrite = new FileWriter(tableName,true);
			}
			bWrite = new BufferedWriter(fWrite);
			bWrite.write(insertion+"\n");
			
			return true;
		}catch(Exception e){
			sendError("failed to insert"+e.getMessage(),command);
			return false;
		}finally {

			try {

				if (bWrite != null)
					bWrite.close();

				if (fWrite != null)
					fWrite.close();

			} catch (Exception e) {

			System.out.println("failed to close"+e.getMessage());
			}
		}	
	}


	public static String[] readTable(String tableName, String path, String command){
		int i = 0;
		String[] read = null;
		String[] reRead = null;
		File book = null;
		if(!path.equals("")){
			book = new File(path+"/"+tableName);
		}
		else{
			book = new File(tableName);
		}
		
		
		Scanner reader = null;
		
		
		try{
			reader = new Scanner(book);
			while(reader.hasNextLine()){				//FL "i like pie"			//"pie is tasty"
				read = new String[i+1];					//read has 1 spot			//new read with 2 spots
				if(read.length==1){						//if read has one spot		//read doesnt have two spots
					read[i]=reader.nextLine();			//set read[0]="i like pie"	//
					reRead= new String[i+1];			//reRead has 1 spot			//
					reRead[i]=read[i];					//reRead[0]= "i like pie"	//
				}
				else{
					for(int n=0;n<i;n++){											//for all but the last lines of read input reRead
						read[n]=reRead[n];
					}
					read[i]=reader.nextLine();										//add pie is tasty to the array
					reRead=new String[i+1];											// reset reRead
					for(int n=0;n<read.length;n++){									//load read into reRead
						reRead[n]=read[n];
					}
				}
				
				i++;									//i=1						//i=2
			}
			reader.close();
			return read;
		}catch(Exception e){
			reader.close();
			sendError("could not read "+tableName,command);
			return read;
		}
		
		
		
		
		

	}


	public static boolean select(String tableName, String path, String selection, String command){
		selection = selection.substring(1,selection.length()-1);
		File file = new File(path+"/"+tableName);
		String[] list = null;
		int n = 0;
		String row = null;
		try{
			if(file.exists()){
				list = readTable(tableName, path,command);
				for(int i=0;i<list.length;i++){
					if(list[i].equals(selection)){
						n++;
						row = selection+" was found on row "+i;
					}
				}
				if(n<1){
					row = selection+" was not found in "+tableName;
					sendError(row,command);
					return false;
				}
			}
			else{
				row = tableName+" does not exist at /"+path;
				sendError(row,command);
				return false;
			}
			
			return true;
		}catch(Exception e){
			sendError("unable to select in file "+tableName+" "+e.getMessage(),command);
			return false;
		}
	}


	public static String find(String tableName, String path, String selection, String command){
		File file = new File(path+"/"+tableName);
		String[] list = null;
		String row = null;
		try{
			if(file.exists()){
				list = readTable(tableName, path, command);
				for(int i=0;i<list.length;i++){
					if(list[i].equals(selection)){
						row = selection+" was found on row "+i;
						return row;
					}
				}
				row = selection+" was not found in "+tableName;
				return row;
			}
			else{
				row = tableName+" does not exist at /"+path;
				return row;
			}
		}catch(Exception e){
			return "unable to select in file "+tableName+" "+e.getMessage();
		}
	}


	public static boolean delete(String tableName, String path,String command){
		String insertion = "";
		BufferedWriter bWrite = null;
		FileWriter fWrite = null;
		
		try{
			if(!path.equals("")){
				fWrite = new FileWriter(path+"/"+tableName);
			}
			else{
				fWrite = new FileWriter(tableName);
			}
			bWrite = new BufferedWriter(fWrite);
			bWrite.write(insertion+"\n");
			
			
			return true;
		}catch(Exception e){
			sendError("failed to insert"+e.getMessage(),command);
			return false;
		}finally {

			try {

				if (bWrite != null)
					bWrite.close();

				if (fWrite != null)
					fWrite.close();

			} catch (Exception e) {

			sendError("failed to close"+e.getMessage(),command);
			}
		}	
	}


	public static boolean deleteFrom(String tableName, String path, String selection, String command){
		selection = selection.substring(1,selection.length()-1);
		try{
			String[] list = readTable(tableName, path, command);
			String find = find(tableName, path, selection, command);
			Scanner scan = new Scanner(find);
			int row;
			row = scan.nextInt();
			String[] newList = new String[list.length-1];
			for(int i=0;i<row;i++){
				newList[i] = list[i];
			}
			for(int i=row+1;i<newList.length;i++){
				newList[i] = list[i];
			}
			dropTable(tableName, path, command);
			createTable(tableName, path, command);
			for(int i=0;i<newList.length;i++){
				insert(tableName, path, newList[i], command);
			}
			
			scan.close();
			return true;
		}catch(Exception e){
	
			sendError("unable to delete "+selection+" from "+tableName+" "+e.getMessage(), command);
			return false;
		}
	}	
		
	
	public static boolean update(String tableName, String path, String selection, String insertion, String command){
		insertion = insertion.substring(1,insertion.length()-1);
		selection = selection.substring(1,selection.length()-1);
		
		
		try{
			String[] list = readTable(tableName, path, command);
			String find = find(tableName, path, selection, command);
			Scanner scan = new Scanner(find);
			int row;
			row = scan.nextInt();
			list[row] = insertion;
			dropTable(tableName, path, command);
			createTable(tableName, path, command);
			for(int i=0;i<list.length;i++){
				insert(tableName, path, list[i], command);
			}
			scan.close();
			return true;
		}
		catch(Exception e){
			sendError("unable to update "+selection+" to "+insertion+" from "+tableName, command);
			return false;
		}
	}

	
	public static void sendError(String error,String command){
			insert("Error_Log","", " "+error+"("+command+")"+" ",command);
		}
	

	public static void sendStatus(String status){
			insert("Status_Log",""," "+status+" ",status);
		}








	}


















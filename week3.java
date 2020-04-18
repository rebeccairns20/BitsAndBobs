

//Import these items:
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;

//MAIN METHOD
public class CMD {
	public static void main(String[] args) { // **  completed  **
		Scanner input = new Scanner(System.in);
		String currentPath = System.getProperty("user.dir");
		String response = "";
		System.out.println("Tom's Operating System [ver1.1]\n(c) My Corporation. All rights reserved.\n\n");
		while (!response.equals("exit")) {
			System.out.print(currentPath + ">");
			response = input.nextLine();
			if (response.startsWith("help")) {
				help(response.substring(4).trim());
			} else if (response.toLowerCase().startsWith("exit")) {
				System.out.println("See ya!\nProgram terminated.");
				System.exit(0);
			} else if (response.toLowerCase().startsWith("dir")) {
				dir(currentPath);
			} else if (response.toLowerCase().startsWith("cd")) {
				currentPath = cd(response.substring(2).trim(), currentPath);
			} else if (response.toLowerCase().startsWith("md")) {
				md(response.substring(2).trim(), currentPath);
			} else if (response.toLowerCase().startsWith("copy")) {
				copy(response.substring(4).trim(), currentPath);
			} else if (response.toLowerCase().startsWith("del")) {
				del(response.substring(3).trim(), currentPath);
			} else if (response.toLowerCase().startsWith("rename")) {
				rename(response.substring(6).trim(), currentPath);
			} else if (response.toLowerCase().startsWith("rd")) {
				rd(response.substring(2).trim(), currentPath);
			} else {
				System.out.println("Error: What kind of a command is " + response + "???");
			}
		}
		System.out.println("Exiting Tom's Operating System [ver1.1]");
	}

	public static void help(String response) { //  ** complete help printouts  **
		switch (response) {
		case "": // just help
			System.out.println("For more information on a specific command, type HELP command-name\n"
					+ "CD             Displays the name of or changes the current directory.\n"
					+ "COPY           Copies one or more files to another location.\n"
					+ "DEL            Deletes one or more files.\n"
					+ "DIR            Displays a list of files and subdirectories in a directory.\n"
					+ "EXIT           Quits the CMD.EXE program (command interpreter)\n."
					+ "HELP           Provides Help information for Windows commands.\n"
					+ "MD             Creates a directory.\n" + "RD             Removes a directory.\n"
					+ "For more information on tools see the command-line reference in the online help.\n\n\n");
			break;
		case "dir": // help dir
			System.out.println("Displays a list of files and subdirectories in a directory.");
			break;
		case "exit": // help exit
			System.out.println("Quits the CMD.EXE program (command interpreter).");
			break;
		case "cd": // help cd
			System.out.println("Displays the name of or changes the current directory.");

			System.out.println("\n  ..   Specifies that you want to change to the parent directory.");
			System.out.println("  " + File.separator + "    Specifies that you want to change to the root directory.");
			break;
		case "copy": // help copy
			System.out.println("Write something about copy.");
			break;
		case "del": // help del
			System.out.println("Write something about del.");
			break;
		case "md": // help md
			System.out.println("Write something about md.");
			break;
		case "rd": // help rd
			System.out.println("Write something about rd.");
			break;
		default: // default error
			System.out.println("This command is not supported by the help utility.");
		}
	}

	public static void dir(String currentPath) { // **  I completed for you  **
		// create vars to store totalByteSize, fileCount, dirCount which need to be
		// displayed
		int totalByteSize = 0;
		int fileCount = 0;
		int dirCount = 0;
		String fileType = "";
		// You need to get info of current directory so create
		File dir = new File(currentPath);
		// You need to get root name and drive name to print out
		String driveLetter = currentPath.substring(0, 1);
		File rootDirectory = new File(Paths.get(dir.getPath()).getRoot().toString());
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String driveName = FileSystemView.getFileSystemView().getSystemDisplayName(rootDirectory);
		// use printf to print out equivalent to: Volume in drive E:\ is GRCC FLASH (E:)
		System.out.printf("Volume in drive %s:" + File.separator + " is %s\n", driveLetter, driveName);
		//get all the file and directory names and print them out
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
			totalByteSize += file.length();
			if (file.isDirectory()) {
				fileType = "<DIR>";
				dirCount += 1;
			} else {
				fileType = "<File>";
				fileCount += 1;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy  hh:mm a");
			System.out.printf("      %s    %6s          %s\n", sdf.format(file.lastModified()), fileType,
					file.getName());
		}
		//print something like     8 File(s)       2410691 bytes
		System.out.printf("   %3d File(s)    %10d bytes\n", fileCount, totalByteSize);
		//print something like     5 Dir(s)  6,716,030,976 bytes free
		System.out.printf("   %3d Dir(s)  %,13d bytes free\n", dirCount, dir.getFreeSpace());
	}

	public static String cd(String response, String currentPath) { // ** complete  **
		// cd\ means go to root (get first first letter of currentDirectory
		// cd\directory means to back to root and then the directory
		// cd.. means go up one directory (take currrentDirectory, parse up one
		// directory
		// cd directory means goto this directory in current path (add directory name to
		// current path + File.seperator  is necessary)
		//
		//System.out.println("ERROR: cd is under construction!");
		// if the first character in response = "\"
		if(response.substring(0,1).equals(File.separator)) {
			// if response is one character it must be "\"
			if(response == File.separator) {
				// then go to root  e.g. c:\
				currentPath = currentPath.substring(0,2);
			}
			else {
				//else response is > 1  e.g. \nextFolder
				String tempPath = currentPath.substring(0,2) + response;
				//if file exists path will = first two chars of path plus response
				if(Files.exists(Paths.get(tempPath))){
				//   e.g. resp
				//   resp = \xxx   then path = current drive plus \xxx
					currentPath = tempPath;
				}
				else {
					System.out.println(tempPath + " doesn't exist.");
				}

			}
		}
		else if(response.equals("..")){
		//elseif cd.. means up one directory
			if(currentPath.length() ==2) {
			//if you are already at root.... print error
				System.out.println("You are already at the root directory.");
			}
			else {
			// else move up one directory
				//find the last backslash in currentPath and remove chars after it
				int posOfBackslash = currentPath.indexOf(File.separator);
				if(posOfBackslash == -1) {
					currentPath = currentPath.substring(0,2);
				}
				else {
					currentPath = currentPath.substring(0, posOfBackslash);

				}
			}
		}
		else {
		// else  // e.g. cd thisfolder    current path + \ + response
			// if current path + \ + response exists go there
						//else print error
			String tempPath = currentPath.substring(0,2) + File.separator + response;
			if(Files.exists(Paths.get(tempPath))){
				currentPath = tempPath;
				}
			else {
				System.out.println(tempPath + " doesn't exist.");
			}

		}
		return currentPath;
	}

	public static void md(String response, String currentPath) { // complete
		//only rule make directory that doesn't exist!
		//this method will receive the name of the directory to create only
		//simple!
		//create a new instance of File using current path + \ + response
		// use File.mkdir()
		System.out.println("ERROR: md is under construction!");


	}

	public static void copy(String response, String currentPath) { // not completed
		// copy rules:
		// allow only files from current directory
		// allow wild cards on both search file and destination file
		// allow to copy file only within currentPath
		// syntax
		// copy text.txt ^ text.ttt note: ^ = <space>
		// copy *.txt ^ *.ttt

		System.out.println("ERROR: copy is under construction!");
		// parse response into fileNameSource, fileExtensionSource, fileNameDestination,
		//e.g. copy *.txt  *.text
		//fileNameSource = *
		//fileExtensionSource = txt
		//fileNameDestination = *
		//fileExttensionDestination = text
		// fileExtensionDestination

		// get a list of Files that match fileName using provided filesThatMatch()
		// File[] copyFileList = filesThatMatchThis(fileNameToBeCopied + fileExtensionToBeCopied, currentPath);
		// loop through the list of files using for
			//get the current file name and its extension
			//if the fileNameDestination = * then fileNameDestination = fileNameSource
			//if the FileExtensionDestination = * then fileExtensionDestination = fileExtensionSource
			//create a new file using fileNameDestination + . + fileExtensionDestination
			//check to see if resulting file exists and allow for escape
			//Files.copy(currentFile.toPath(), destinationFile.toPaht()
			//to overwrite a file you have to use additional option StandardCopyOption.REPLACE_EXISTING
			}

	public static void del(String response, String currentPath) { // not completed
		// del rules:
		// allow only files from current directory
		// allow wild cards
		// syntax del text.txt
		// del *.txt
		// del text.*

		System.out.println("ERROR: del is under construction!");


	}

	public static void rename(String response, String currentPath) { // not completed
		// syntax rename test.txt test2.txt
		// rename test.txt test2.*

		System.out.println("ERROR: rename is under construction!");


	}

	public static void rd(String response, String currentPath) { // not completed
		//syntax  rd folderName
		//Note: it is possible to have to remove folders within the folder and also their files
		//   there you have to call the following rdActual() method recursively.

		System.out.println("ERROR: rd is under construction!");

	}

	public static void rdActual(String response, String currentPath) { // not completed
		//repeat 2 lines from previous method to get dir to remove
		String dir = response;
		//create a new File    currentPath + File.separator + dir
		File delDir = new File(currentPath + File.separator + dir);
		int numOfDirs = countDirectories(delDir);
		if (numOfDirs ==0)   delDir.delete();
		//else, we got issues because dir has dirs in it!
		else{
		String files[] = delDir.list();
 	   for (String temp : files) {
 	      //    create new parameter strings for the recursive deletes
 	      File fileDelete = new File(delDir, temp);
 	      String tempFullPath = fileDelete.toString();
 	      String tempFileName = tempFullPath.substring(tempFullPath.lastIndexOf("\\")+1, tempFullPath.length());
 	      tempFullPath = tempFullPath.substring(0,tempFullPath.indexOf(tempFileName)-1);
 	      //recursive delete
 	      rdActual("rd " + tempFileName, tempFullPath);
 	   	}
 	   	//  check the directory again, if empty then delete it
 	   	if(delDir.list().length==0){
 	   	delDir.delete();
		System.out.println("Directory is deleted : " + delDir.getAbsolutePath());

		}
	}
	}

	public static File[] filesThatMatch(String strFileName, String strPath) { // completed
		// note: strFileName can be either file name or path + file name
		File copyPathFile = new File(strPath);
		// http://stackoverflow.com/questions/10520566/regular-expression-wildcard-matching
		StringBuilder sb = new StringBuilder(strFileName.length() + 25);
		// convert fileName to regex
		sb.append('^');
		for (int i = 0; i < strFileName.length(); ++i) {
			char c = strFileName.charAt(i);
			String regexString = File.separator + ".[]{}()+-^$|";
			if (c == '*')
				sb.append(".*");
			else if (c == '?')
				sb.append('.');

			else if (regexString.indexOf(c) >= 0) {
				sb.append(File.separator);
				sb.append(c);
			} else
				sb.append(c);
		}
		sb.append('$');
		final Pattern pattern = Pattern.compile(sb.toString()); // Caution: could also throw an exception!
		// get array of files that meet regex criteria
		File[] FileList = (copyPathFile).listFiles(new FileFilter() {
			public boolean accept(File file) {
				return pattern.matcher(file.getName()).matches();
			}
		});
		return FileList;
	}

	public static int countDirectories(File directory) { // completed
		int count = 0;
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				count++;
			}
			if (file.isDirectory()) {
				count += countDirectories(file);
			}
		}
		return count;
	}

	public static int countFiles(File directory) { // completed
		int count = 0;
		for (File file : directory.listFiles()) {
			if (file.isFile()) {
				count++;
			}
			if (file.isDirectory()) {
				count += countFiles(file);
			}
		}
		return count;
	}
}

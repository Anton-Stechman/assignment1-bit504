package bit504_as1;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class errorHandles 
{
	
	/*
	 * Course:		BIT504
	 * Assignment:	Assignment 1
	 * Student ID:	5000406
	 * Program By:	Anton Stechman		
	 */
	
	public static void userInputError(int input) //users inputs option that is out of bounds
	{
		System.out.println("ERROR: Selection Out Of Bounds! \nOption " + input +  " Does Not Exist"); //error message
		System.out.println("Press 'Enter' To try Again"); // prompt to try again
	}
	public static void inputError_dataType() //user inputs a data-type that isn't an integer 
	{
		System.out.println("ERROR: Incorrect Input, Please Input a Whole Number Only"); //error message
		System.out.println("Press 'Enter' To try Again"); // prompt to try again
	}
	
	public static void file_not_located_error(Path path) 
	{
		System.out.print("studentData.txt could not be found! \n Would you like to create file now? (Warning Selectin 'N'"
				+ "Will Close The Program!) \n [Y/N]...");
		
		Scanner scanner = new Scanner(System.in);
		
		try 
		{
			String input = scanner.next();
			
			switch(input) 
			{
				case "Y":
				{
					scanner.close();
					createNewFile(path.toString());
					break;
				}
				case "y":
				{
					scanner.close();
					createNewFile(path.toString());
					break;
				}
				case "N":
				{
					as1_frontend.isExit = true; // close program
				}
				case "n":
				{
					as1_frontend.isExit = true; // close program
				}
				default:
				{
					System.out.println("Invalid Selection...");
					file_not_located_error(path);
				}
			}
		
		}
		catch (Exception e) 
		{
			System.out.println("Invalid Selection...");
			file_not_located_error(path);
		}
		
	}
		static void createNewFile(String path) 
		{
		
		//Create List of All Default Data
		LinkedList<String> defaultData = new LinkedList<String>();
		defaultData.add("1,Frank,West,98,95,87,78,77,80");
		defaultData.add("2,Dianne,Greene,78,94,88,87,95,92");
		defaultData.add("3,Doug,Lei,78,94,88,87,95,92");
		defaultData.add("4,James,Hewlett,69,92,74,77,89,91");
		defaultData.add("5,Aroha,Wright,97,92,87,83,82,92");
		
		try 
		{
			PrintWriter newFile = new PrintWriter(path, "UTF-8"); // Create .txt file in given path
			
			for (String s : defaultData) 
			{
				newFile.println(s); // Write To file
			}
			
			newFile.close(); // Close File
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("studentData.txt Created Successfully!");
		
	}
}


package bit504_as1;

import java.util.LinkedList;

public class as1_frontend extends as1_backend
{
	/*
	 * Course:		BIT504
	 * Assignment:	Assignment 1
	 * Student ID:	5000406
	 * Program By:	Anton Stechman		
	 */
	
	//String Variables For Displaying Menu's
	static String menu_title;

	public static boolean isExit = false;
	public static LinkedList<Student> students;
	
	public static void main(String[] args) 
	{
		//TODO: Load studentData.txt File
		loadStudentData();
		mainMenu();
	}	

	public static void mainMenu() 
	{ 
		//Handle Menu Display
		menu_title = "Student Report System\n---------------------";
		
		System.out.println(Display_menu(menu_title, menuOptions(new LinkedList<String>()))); //Display Menu String
		
		
		while(!isExit) 
		{
			//Handle Menu Logic
			int input_result = userInput();
			
			switch(input_result) 
			{
				case 1: // view student marks
				{
					//System.out.println("Marks Selected"); > for testing purposes
					displayAllMarks();
					break;
				}
				case 2: // view student grades
				{
					//System.out.println("Grades Selected"); > for testing purposes
					displayAllGrades();
					break;
				}
				case 3: // add new student
				{
					//System.out.println("Add New Selected"); > for testing purposes
					addNewStudent();
					break;
				}
				case 4: // remove student
				{
					//System.out.println("Remove Selected"); > for testing purposes
					removeStudent_menu();
					break;
				}
				case 5: // exit program
				{
					//System.out.println("Exit Selected"); > for testing purposes
					scanner.close();
					isExit = true;
					break;
				}
				default: // input error
				{
					userInputError(input_result);
					scanner.next();
					mainMenu();
				}	
			}
		}
		
	}
	
	public static void displayAllGrades() 
	{
		System.out.print(Display_allStudentGrades());
		mainMenu();
	}
	
	public static void displayAllMarks() 
	{
		System.out.print(Display_allStudentMarks());
		
		mainMenu();
	}
	
	public static void addNewStudent() 
	{
		LinkedList <String> menuPrompt = new LinkedList<String>();
		//menuPrompt.add("Please Enter Student ID (Numeric Value): "); > Removed in favor of generating an id based off list index
		menuPrompt.add("Enter Students First Name: ");
		menuPrompt.add("Enter Students Last Name: ");
		menuPrompt.add("Enter Students Mark For Mathmatics (Assignment 1): ");
		menuPrompt.add("Enter Students Mark For Mathmatics (Assignment 2): ");
		menuPrompt.add("Enter Students Mark For Mathmatics (Assignment 3): ");
		menuPrompt.add("Enter Students Mark For English (Assignment 1): ");
		menuPrompt.add("Enter Students Mark For English (Assignment 2): ");
		menuPrompt.add("Enter Students Mark For English (Assignment 3): ");
		
		createNewStudent(menuPrompt);
		
		mainMenu();
	}
	
	public static void removeStudent_menu() 
	{
		
		System.out.print("Enter Student ID Of The Student You Want To Remove: ");
		int input 		= userInput() - 1; //Student ID Starts @ 1, students LinkedList starts at 0
		Student student = getStudentByID(input);
		
		if (student != null) 
		{
			System.out.print("Are you sure you want to remove " + student.getFullName() + " from the database? \n [Y/N]... ");
			
			String selection = scanner.next();
			
			switch (selection) 
			{
				case "Y":
				{
					removeStudent(student);
					System.out.println(student.getFullName() + " Removed From Database");
					break;
				}
				case "y":
				{
					removeStudent(student);
					System.out.println(student.getFullName() + " Removed From Database");
					break;
				}
				case "N":
				{
					System.out.println("Action Aborted. Returning To Main Menu...");
					mainMenu();
					break;
				}
				case "n":
				{
					System.out.println("Action Aborted. Returning To Main Menu...");
					mainMenu();
					break;
				}
				default:
				{
					System.out.println(selection + " is not a valid Option, Returning to Main Menu...");
					
				}
			}
		}
		else 
		{
			System.out.println("Sorry, Student By ID: " + input + " Does Not Exist. \n Press 1 to try again or 2 to exit to Menu...");
		}		
		
		mainMenu();
	}

	


}

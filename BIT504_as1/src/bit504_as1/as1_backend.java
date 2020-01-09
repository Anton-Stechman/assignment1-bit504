package bit504_as1;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class as1_backend extends errorHandles
{
	
	/*
	 * Course:		BIT504
	 * Assignment:	Assignment 1
	 * Student ID:	5000406
	 * Program By:	Anton Stechman		
	 */
	
	public static Scanner scanner 	= new Scanner(System.in);
	private static String filePath 	= "C:/BIT504_as1_5000406/"; //Change Path To Folder Here
	private static String fileName 	= "studentData.txt"; //Change File Name Here
	
	public static LinkedList<Student>students = new LinkedList<Student>();//List Of Students
	
	//Load studentData.txt File
	public static void loadStudentData() 
	{
		//File file = new File(filePath);
		
		Path path 	= Paths.get(filePath, fileName);
		Charset cs 	= Charset.forName("ISO-8859-1");
		File file 	= new File (path.toString());
		
		if (file.exists()) 
		{
			List<String> allData;
			
			try 
			{
				allData = Files.readAllLines(path,cs); // convert .txt file into list (line by line)
				
				for(String s : allData) //iterate through list of file lines
				{
					//System.out.println(s);
					
					String[] splitString = s.split(","); // split current string where ever there is a ',' 
					
					// Generic Info
					int id 		= Integer.parseInt(splitString[0]); 	//Student ID
					var fName 	= splitString[1]; 						//Student First Name
					var lName 	= splitString[2]; 						//Student Last Name
					
					// Math Course
					int m_as1 	= Integer.parseInt(splitString[3]); 	//Maths Assignment 1
					int m_as2 	= Integer.parseInt(splitString[4]); 	//Maths Assignment 2
					int m_as3 	= Integer.parseInt(splitString[5]); 	//Maths Assignment 3
					
					// English Course
					int e_as1 	= Integer.parseInt(splitString[6]); 	//English Assignment 1
					int e_as2 	= Integer.parseInt(splitString[7]); 	//English Assignment 2
					int e_as3 	= Integer.parseInt(splitString[8]); 	//English Assignment 3
					
					// Create New Student Object - Fill it with data
					Student newStudent = new Student(id, fName, lName); 
					
					// Create new Math Assignment Object - fill with data
					newStudent.math_marks = new AssignmentMarks("Mathmatics", m_as1, m_as2, m_as3);
					
					//Create new English Assignment Object - fill with data
					newStudent.engl_marks = new AssignmentMarks("English", e_as1, e_as2, e_as3); 
					
					//Add New Student To List Of Students
					addStudent(newStudent);
				}	
			} 
			catch (IOException e) 
			{
				System.out.println("studentData.txt could not be read, Please make sure the file is in the correct fomat: \n" + 
			"(ID,FirstName,LastName,course1Mark1,course1Mark2,course1Mark3,course2Mark1,course2Mark2,course2Mark3");
				e.printStackTrace();
			}
		}
		else 
		{
			file_not_located_error(path); // Create New File 
			loadStudentData(); //Re-Try Accessing File 
		}
	}
	
	//Write Student Data to File
	public static void writeStudentData() 
	{
		//Convert Data From Students LinkedList To String
		String DataToWrite = "";
		
		for (Student s : students) 
		{
			DataToWrite += s.id + "," + s.firstName + "," + s.lastName + "," + 
					s.math_marks.getAss_1() + "," + s.math_marks.getAss_2() + "," + 
					s.math_marks.getAss_3() + "," + s.engl_marks.getAss_1() + "," + 
					s.engl_marks.getAss_2() + "," + s.engl_marks.getAss_3() + "\n";
		}
		
		//Convert DataToWrite String Containing Student Data To Byte(Array) 
		byte[] lines = DataToWrite.getBytes();
		
		//Set Path For File To Write To
		Path path 	= Paths.get(filePath, fileName);
				
		try 
		{
			//Write New Data To File
			Files.write(path, lines);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	//Display All Student Marks To Console
	public static String Display_allStudentMarks() 
	{
		String c_as1 = ""; // course assignment 1
		String c_as2 = ""; // course assignment 2
		String c_as3 = ""; // course assignment 3
		String c_avg = ""; // course average

		String displayString = "";
		
		for (Student s : students) 
		{
			//Get Math Grades
			c_as1 = "" + s.math_marks.getAss_1();
			c_as2 = "" + s.math_marks.getAss_2();
			c_as3 = "" + s.math_marks.getAss_3();
			c_avg = "" + s.math_marks.getAverageMark();
			
			String header = "Name...................."+ s.math_marks.courseName + ".............." + s.engl_marks.courseName + "..............";
			displayString += header + "\n";
			
			//Format In Math Marks
			String data = s.getFullName() + "\t\t[" + c_as1 + 
					"][" + c_as2 + "][" + c_as3 + "][Avg:" + c_avg + "]";
			
			//Get English Marks
			c_as1 = "" + s.engl_marks.getAss_1();
			c_as2 = "" + s.engl_marks.getAss_2();
			c_as3 = "" + s.engl_marks.getAss_3();
			c_avg = "" + s.engl_marks.getAverageMark();
			
			//Format English Grades
			data += "\t[" + c_as1 + "][" + c_as2 + 
			"][" + c_as3 + "][Avg:" + c_avg + "]";
			
			//Display Final Formatted String
			displayString += data + "\n\n";
		}
		
		return displayString;
	}
	
	//Display All Grades to Console
	public static String Display_allStudentGrades() 
	{
		String c_as1 = ""; // course assignment 1
		String c_as2 = ""; // course assignment 2
		String c_as3 = ""; // course assignment 3
		String c_avg = ""; // course average

		
		String displayString = "";
		
		for (Student s : students) 
		{
			//Get Math Grades
			c_as1 = s.math_marks.getGrade(s.math_marks.getAss_1());
			c_as2 = s.math_marks.getGrade(s.math_marks.getAss_2());
			c_as3 = s.math_marks.getGrade(s.math_marks.getAss_3());
			c_avg = s.math_marks.getGrade(s.math_marks.getAverageMark());
			
			String header = "Name...................."+ s.math_marks.courseName + ".............." + s.engl_marks.courseName + "..............";
			displayString += header + "\n";
			
			//Format In Math Grades
			String data = s.getFullName() + "\t\t[" + c_as1 + 
					"][" + c_as2 + "][" + c_as3 + "][Avg:" + c_avg + "]";
			
			//Get English Grades
			c_as1 = s.engl_marks.getGrade(s.math_marks.getAss_1());
			c_as2 = s.engl_marks.getGrade(s.math_marks.getAss_2());
			c_as3 = s.engl_marks.getGrade(s.math_marks.getAss_3());
			c_avg = s.engl_marks.getGrade(s.math_marks.getAverageMark());
			
			//Format English Grades
			data += "\t[" + c_as1 + "][" + c_as2 + 
			"][" + c_as3 + "][Avg:" + c_avg + "]";
			
			//Display Final Formatted String
			displayString += data + "\n\n";
		}
		
		return displayString;
	}
	
	//Main Menu Options
	public static LinkedList<String> menuOptions(LinkedList<String> m_options) 
	{
		
		m_options.add("Display Student Marks");
		m_options.add("Display Student Grades");
		m_options.add("Add New Student");
		m_options.add("Remove Student");
		m_options.add("Exit Program");
		
		return m_options;
	}
	
	//Take Menu Display Parameters, Format Them Then Return A String To Be Printed To Console
	public static String Display_menu(String m_title, LinkedList<String> m_options) 
	{
		String displayString = "";
		int index = 1;
		
		displayString += m_title;
		
		for (String s : m_options) 
		{
			displayString += "\n" + index + ") " + s;
			index++;
		}
		return displayString;
	}
	
	//Take User Input For Selecting Options In Menu
	public static int userInput() 
	{
		var selection 	= scanner.next();
		int select_int;
		
		try 
		{
			select_int = Integer.parseInt(selection);
			return select_int;
		}
		catch(Exception e)
		{
			inputError_dataType();
			scanner.nextLine();
			return 0;
		}

	}
	
	//Create A new Student Class
	public static void createNewStudent(LinkedList<String> prompt) 
	{
		
		int id 			= students.get(students.size() - 1).id + 1; //Increments Id of Last Student in List by 1
		String fName 	= new String();
		String lName 	= new String();
		int m_as1 		= 0;
		int m_as2 		= 0;
		int m_as3 		= 0;
		int e_as1 		= 0;
		int e_as2 		= 0;
		int e_as3 		= 0;
		
		int index = 0;
		
		for (String s : prompt) 
		{
			System.out.print(s);

			switch (index) 
			{
				case 0: // first name
				{
					fName = scanner.next();
					break;
				}
				case 1: // last name
				{
					lName = scanner.next();
					break;
				}
				case 2: // math assignment 1 
				{
					m_as1 = userInput();
					break;
				}
				case 3: // math assignment 2
				{
					m_as2 = userInput();
					break;
				}
				case 4: // math assignment 3
				{
					m_as3 = userInput();
					break;
				}
				case 5: // english assignment 1
				{
					e_as1 = userInput();
					break;
				}
				case 6:  // english assignment 2
				{
					e_as2 = userInput();
					break;
				}
				case 7: // english assignment 3
				{
					e_as3 = userInput();
					break;
				}
			}
			
			index++;
		}
		
		List<Object> attributes = new ArrayList<Object>();		
		attributes.add("ID............" + id); // id
		attributes.add("First Name...." + fName); // first name
		attributes.add("Last Name....." + lName); // last name
		attributes.add("Math As1......" + m_as1); // math 1
		attributes.add("Math As2......" + m_as2); // math 2
		attributes.add("Math As3......" +m_as3); // math 3
		attributes.add("Engl As1......" +e_as1); // english 1
		attributes.add("Engl As2......" +e_as2); // english 2
		attributes.add("Engl As3......" +e_as3); // english 3
		
		Student newStudent 		= new Student(id, fName, lName);
		newStudent.math_marks 	= new AssignmentMarks("Mathmatics", m_as1, m_as2, m_as3);
		newStudent.engl_marks 	= new AssignmentMarks("English", e_as1, e_as2, e_as3);
		
		String confirmString = "";
		System.out.println("You have Entered: \n");
		
		for (Object o : attributes) 
		{
			confirmString += o.toString() + "\n";
		}
		
		confirmString += "Continue? [Y/N]....";
		
		System.out.print(confirmString);
		
		switch (scanner.next())
		{
			case "y":
			{
				addStudent(newStudent);
				System.out.print("New Student Successfully Added! \n");
				
				break;
			}
			case "Y" :
			{
				addStudent(newStudent);
				System.out.print("New Student Successfully Added! \n");
				
				break;
			}
			case "n":
			{
				System.out.println("Operation Aborted, Please Try Again! \n");
				createNewStudent(prompt);
				break;
			}
			case "N":
			{
				System.out.println("Operation Aborted, Please Try Again! \n");
				createNewStudent(prompt);
				break;
			}
			default:
			{
				System.out.println("Incorrect Selection! \n\n");
				as1_frontend.mainMenu();
			}
		}
		
		
		
	}
	
	//Add New Student Class To Student List > Write List To File
	public static void addStudent (Student newStudent)
	{
		students.add(newStudent);
		writeStudentData();
		
	}
	
	//Get A Student Class From List By Student ID
	public static Student getStudentByID(int s_id) 
	{
		if (students.get(s_id) != null) 
		{
			return students.get(s_id);
		}
		else 
		{
			System.out.println("Sorry, Student By ID: " + s_id + " Does Not Exist. \n Press 1 to try again or 2 to exit to Menu...");
			as1_frontend.mainMenu(); // return to main menu
			return null;
		}
	}
	
	//Remove Selected Student from List > Write To File
	public static void removeStudent(Student studentToRemove) 
	{

		students.remove(studentToRemove);
		
		for (int i = 0; i < students.size(); i ++) // set All student id value back into a unique order (1,2,3,4...)  
		{
			students.get(i).id = i +1;
		}
		
		writeStudentData();
	}

}

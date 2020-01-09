package bit504_as1;

public class Student 
{
	
	/*
	 * Course:		BIT504
	 * Assignment:	Assignment 1
	 * Student ID:	5000406
	 * Program By:	Anton Stechman		
	 */
	
	int id;
	String firstName;
	String lastName;
	
	public AssignmentMarks math_marks;
	public AssignmentMarks engl_marks;
	
	public Student(int ID, String fName, String lName) 
	{
		this.id 		= ID;
		this.firstName 	= fName;
		this.lastName 	= lName;
	}
	
	public String getFullName() 
	{
		return firstName + " " + lastName;
	}
}

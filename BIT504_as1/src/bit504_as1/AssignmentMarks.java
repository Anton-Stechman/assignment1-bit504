package bit504_as1;

/*
 *#NOTE:
 *I have not included a static String getAverageGrade() Method 
 *as I felt that was this was unnecessary when you can just call getGrade(int getAverageMark);  
 * */
 

public class AssignmentMarks 
{
	
	/*
	 * Course:		BIT504
	 * Assignment:	Assignment 1
	 * Student ID:	5000406
	 * Program By:	Anton Stechman		
	 */
	
	String courseName; //Course Name
	private int ass_1; //Assignment 1
	private int ass_2; //Assignment 2
	private int ass_3; //Assignment 3
	
	//Class Construct
	public AssignmentMarks(String cName,int as1, int as2, int as3) 
	{
		this.courseName = cName;
		this.setAss_1(as1);
		this.setAss_2(as2);
		this.setAss_3(as3);
	}
	
	//Get The Average Grade
	public int getAverageMark() 
	{
		int average = (ass_1 + ass_2 + ass_3) / 3;
		return average;
	}
	
	//Get The Grade Of A Mark
	public String getGrade(int mark) 
	{
		
		
		if (mark >= 95) //A+
		{
			return "A+";
		}
		else if (mark < 95 && mark >=90) //A
		{
			return "A";
		}
		else if(mark < 90 && mark >= 85) //A-
		{
			return "A-";
		}
		else if(mark < 85 && mark >= 80)//B+ 
		{
			return "B+";
		}
		else if (mark < 80 && mark >= 75)// B
		{
			return "B";
		}
		else if (mark<75 && mark >= 70) // B-
		{
		return "B-";	
		}
		else if (mark < 70 && mark >= 60) //C+
		{
			return "C+";
		}
		else if (mark < 60 && mark >= 50) //C
		{
			return "C";
		}
		else if(mark > 40 && mark < 50) //C- 
		{
			return "C-";
		}
		
		else 
		{
			return "D";
		}
	}
	
	
	//{Get,Set} Assignment Marks For This Instance of AssignmentMarks() Class 
	
	public int getAss_1() {
		return ass_1;
	}

	public void setAss_1(int ass_1) {
		this.ass_1 = ass_1;
	}

	public int getAss_2() {
		return ass_2;
	}

	public void setAss_2(int ass_2) {
		this.ass_2 = ass_2;
	}

	public int getAss_3() {
		return ass_3;
	}

	public void setAss_3(int ass_3) {
		this.ass_3 = ass_3;
	}
}

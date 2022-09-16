import java.util.List;
import java.util.Scanner;

import controller.StudentInfoHelper;
import model.StudentInfo;



/**
 * @author Joe Shilkaitis - jshilkaitis
 * CIS175 - Fall 2022
 * Sep 15, 2022
 */
public class StartProgram {
	
	static Scanner in = new Scanner(System.in);
	static StudentInfoHelper sih = new StudentInfoHelper();

	private static void addAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter student's first name: ");
		String fName = in.nextLine();
		System.out.print("Enter student's last name: ");
		String lName = in.nextLine();
		System.out.print("Enter student's age: ");
		String stringAge = in.nextLine();
		int age = Integer.parseInt(stringAge);
		System.out.print("Enter student's rank: ");
		String belt = in.nextLine();

		
		StudentInfo toAdd = new	StudentInfo(fName, lName, age, belt);
		sih.insertStudent(toAdd);
	}
	
	private static void deleteStudent() {
		// TODO Auto-generated method stub
		System.out.print("Enter student's first name to delete: ");
		String fName = in.nextLine();
		System.out.print("Enter student's last name delete: ");
		String lName = in.nextLine();
		System.out.print("Enter student's age to delete: ");
		String stringAge = in.nextLine();
		int age = Integer.parseInt(stringAge);
		System.out.print("Enter student's rank to delete: ");
		String belt = in.nextLine();
		StudentInfo toDelete =	new	StudentInfo(fName, lName, age, belt);
		sih.deleteStudent(toDelete);

	}
	
	private static void editStudent() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search student's last name");
		System.out.println("2 : Search by student's rank");
		int searchBy = in.nextInt();
		in.nextLine();
		List<StudentInfo> foundStudentInfo;
		if (searchBy == 1) {
			System.out.print("Enter student's last name: ");
			String lastName = in.nextLine();
			foundStudentInfo = sih.searchForStudentByLastName(lastName);
			
		} else {
			System.out.print("Enter student's rank: ");
			String rank = in.nextLine();
			foundStudentInfo = sih.searchForStudentByRank(rank);

		}

		if (!foundStudentInfo.isEmpty()) {
			System.out.println("Found Results.");
			for (StudentInfo l : foundStudentInfo) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			StudentInfo toEdit = sih.searchForStudentById(idToEdit);
			System.out.println("Retrieved " + toEdit.getFirstName() + " " + toEdit.getLastName() + ", " + toEdit.getAge() + ", " + toEdit.getRank());
			System.out.println("1 : Update first name");
			System.out.println("2 : Update last name");
			System.out.println("3 : Update age");
			System.out.println("4 : Update rank");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New first name: ");
				String newFirstName = in.nextLine();
				toEdit.setFirstName(newFirstName);
			} else if (update == 2) {
				System.out.print("New last name: ");
				String newLastName = in.nextLine();
				toEdit.setLastName(newLastName);
			}else if (update == 3) {
				System.out.print("New age: ");
				String newAgeString = in.nextLine();
				int newAgeInt = Integer.parseInt(newAgeString);
				toEdit.setAge(newAgeInt);
			}else if (update == 4) {
				System.out.print("New rank: ");
				String newRank = in.nextLine();
				toEdit.setRank(newRank);
			}

			sih.updateStudent(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}
	
	private static void viewTheList() {
		List<StudentInfo> allstudents = sih.showAllStudents();
		for (StudentInfo singlestudent : allstudents) {
			System.out.println(singlestudent.returnStudentInfo());
		}

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}
	
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("Student Information Database");
		while (goAgain) {
			System.out.println("*  Select an option:");
			System.out.println("*  1 -- Add student information");
			System.out.println("*  2 -- Edit student information");
			System.out.println("*  3 -- Delete student information");
			System.out.println("*  4 -- View student information");
			System.out.println("*  5 -- Exit");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editStudent();
			} else if (selection == 3) {
				deleteStudent();
			} else if (selection == 4) {
				viewTheList();
			} else {
				sih.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}
}

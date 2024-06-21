package components;
import java.util.LinkedList;

public class GradeBook {
	String nameGB;
	//Double finalGrade;
	//String letterGrade;
	LinkedList<Category> listAssignments;
	
	//Constructors
	public GradeBook(String nameGB) {
		this.nameGB = nameGB;
		//this.finalGrade = 0.0;
		//this.letterGrade = "";
		this.listAssignments = new LinkedList<>();
	}
	
	//Setter
	public void setNameGB(String nameGB){
		this.nameGB = nameGB;
	}
	
	//Getter 
	public String getGBName() {return this.nameGB;}
	
	
// Add new Catergory into GB	
	public boolean addCategory(Category newCategory) {
		if(listAssignments.contains(newCategory) || newCategory == null){
			return false;
		}
		
		return listAssignments.add(newCategory);
	}

// Remove Specific Category
	public boolean removeCategory(Category target){
		if(!listAssignments.contains(target) || target == null) {
			return false;
		}
		
		return listAssignments.remove(target);
	}
	
// Remove Category at specific index	
	public boolean removeCategoryAt(int index) {
		return listAssignments.remove(index) != null;
	}
	
	
// 
	public double getFinalGrade() {
		double sum = 0.0;
		for(Category c : listAssignments) {
			sum += c.getCatGrade();
		}
		
		return sum;
	}
	
	public String getLetterGrade() {
		double finalGrade = getFinalGrade();
		String letter = "";
		if(finalGrade < 60.0) {
			letter = "F";
		}
		
		else {
			if(finalGrade >= 90.0){
				letter = "A";
				if(finalGrade >= 97.0) {letter += "+";}
				else if (finalGrade <= 92.0) {letter += "-";}
			}
			else if (finalGrade >= 80.0) {
				letter = "B";
				if(finalGrade >= 87.0) {letter += "+";}
				else if (finalGrade <= 82.0) {letter += "-";}
			}
			
			else if(finalGrade >= 70.0) {
				letter = "C";
				if(finalGrade >= 77.0) {letter += "+";}
				else if (finalGrade <= 72.0) {letter += "-";}
				
			}
			else{
				letter = "D";
			}
		}
		
		return letter;
	}
	
	public LinkedList<Category> getListCategory(){
		return this.listAssignments;
	}
	
	
	
	public String toString() {
		if(listAssignments.isEmpty()) {
			return "GradeBook : " + this.nameGB + " is EMPTY!";
		}
		
		System.out.printf("GradeBook Name: %s\t\t Final Grade: %.02f\t\t Letter: %s\n", nameGB, getFinalGrade(), getLetterGrade() );
		for(Category c : listAssignments) {
			System.out.print(c);
		}
		
		return "";
	}
	
	public boolean equals(Object o) {
		if((o != null ) && (o instanceof GradeBook)){
	           return this.nameGB.equals(((GradeBook)o).getGBName());
		}
	    return false;
	}
	
	
	
	
	public static void main(String[] args) {
		LinkedList<GradeBook> test = new LinkedList<>();
		
		GradeBook gb1 = new GradeBook("CS123");
		GradeBook gb2 = new GradeBook("CS123");
		
		
		test.add(gb1);
		if(test.contains(gb2)) {
			System.out.print("Test already contain " + gb2.getGBName());
		}
		else {
			test.add(gb2);
		}
		
		System.out.println("Size of test = " + test.size());
		
		
		
		
		
	/*	
		GradeBook gb1 = new GradeBook("CS451");
		
		Category c1 = new Category("Math HW", 5.0);
		Category c2 = new Category("Quizzes", 5.0);
		Category c3 = new Category("Project1", 15.0);
		Category c4 = new Category("Project2", 15.0);
		Category c5 = new Category("Project3", 15.0);
		Category c6 = new Category("Project4", 15.0);
		Category c7 = new Category("Midterm", 15.0);
		Category c8 = new Category("Final", 15.0);
		
		System.out.println(gb1);	// Empty 
		
		Item i1 = new Item("Math HW", "5/5");
		c1.addItem(i1);
		
		Item i2 = new Item("Quiz1", "0.5/1");
		Item i3 = new Item("Quiz2", "1/1");
		Item i4 = new Item("Quiz3", "1/1");
		Item i5 = new Item("Quiz4", "1/1");
		Item i6 = new Item("Quiz5", "1/1");
		c2.addItem(i2);
		c2.addItem(i3);
		c2.addItem(i4);
		c2.addItem(i5);
		c2.addItem(i6);
		
		Item i7 = new Item("P1", "11/15");
		c3.addItem(i7);
		Item i8 = new Item("P2", "13/15");
		c4.addItem(i8);
		Item i9 = new Item("P3", "15/15");
		c5.addItem(i9);
		Item i10 = new Item("P4", "10/15");
		c6.addItem(i10);
		
		Item i11 = new Item("Midterm", "69/100");
		c7.addItem(i11);
		Item i12 = new Item("Midterm", "54/100");
		c8.addItem(i12);



		
		gb1.addCategory(c1);
		gb1.addCategory(c2);
		gb1.addCategory(c3);
		gb1.addCategory(c4);
		gb1.addCategory(c5);
		gb1.addCategory(c6);
		gb1.addCategory(c7);
		gb1.addCategory(c8);
		

		System.out.println(gb1);
	 */




		

		
		

	}

}

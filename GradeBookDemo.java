package components;

import java.util.Scanner;
import java.util.LinkedList;

public class GradeBookDemo {
	private static LinkedList<GradeBook> listGB = new LinkedList<>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String user = "";
		boolean repeatMainMenu = true;
		
		
		do{
			showMainMenu();
			
			try {
				System.out.print("-> ");
				user = scan.nextLine();
				switch(user.charAt(0)) {
				
					/* Add new GB to the listGB */
					case '1':
						do {
							System.out.print("Gradebook name: ");
							user = scan.nextLine();
							if(user.length() > 0) {
								break;
							}
							System.out.println("Name is empty, try again!\n");
						}while(true);
						if(listGB.contains(new GradeBook(user))) {
							System.out.println("The name above already taken, please choose another one!");
						}
						else {
							listGB.add(new GradeBook(user));
						}
						break;
					
					/*Edit on an existed GB*/	
					case '2':
						boolean repeatGBMenu = true;
						boolean repeatEditGB = true;
						int curGB = -1;
						
						if(listGB.isEmpty()) {
							System.out.println("There is no gradebook to display. Please add one!");
							break;
						}
						
						
						do {	//continue run GB-Menu
							try {
								
								showListGB();
								System.out.print("->");
								user = scan.nextLine();
								
								if(user.equalsIgnoreCase("r")) {				//Return to MAIN menu
									break;
								}
								
								curGB = Integer.parseInt(user);					// Current Gradebook selected to edit
								System.out.println(listGB.get(curGB));			// Print all info of the current GB
								
								do { // Continue run editGB-Menu
									try {
										editGBMenu();
										System.out.print("->");
										user = scan.nextLine();
										
										switch(user.charAt(0)) {
											
										/* Add new category to the GB */
											case '1':
												System.out.print("Category name: ");
												String catName = scan.nextLine();
												System.out.print("Weight(in %): ");
												double catWeight = Double.parseDouble(scan.nextLine());
												
												listGB.get(curGB).addCategory(new Category(catName, catWeight));
												break;
												
												
											/*Delete an existed category from the GB */	
											case '2': 
												if(showListCategory(curGB)) {
													System.out.println("Select category want to DELETE");
													System.out.print("-> ");
													user = scan.nextLine();
													
													if(listGB.get(curGB).removeCategoryAt(Integer.parseInt(user))) {
														System.out.println("Successfull deleted category!");
														System.out.println(listGB.get(curGB));
													}
												}
												break;
											
											
											/* Change GB's name */	
											case '3':
												System.out.print("New name: ");
												user = scan.nextLine();
												listGB.get(curGB).setNameGB(user);
												System.out.println("Succesfully update gradebook's name!");
												System.out.println("=> " + listGB.get(curGB));
												
											
											/* Edit an existed category*/
											case '4':
												int curCat = -1;
												do {
													try {
														showListCategory(curGB);
														System.out.println("Select a category above to continue:");
														curCat = Integer.parseInt(getUserInput());
														
														//check if the curCat is valid
														if(listGB.get(curGB).getListCategory().contains(listGB.get(curGB).getListCategory().get(curCat))) {
															break;
														}
													}catch(Exception eInput) {
														eInput.getStackTrace();
														System.out.println("Invalid category! Try again");
													}
												}while(true); // repeat show list category 

												
												do {
													try {
														showListItem(curGB, curCat);
														editCategoryMenu();
														user = getUserInput();
														
														switch(user) {
															/*Add new item to current category*/
															case "1":
																addItemToCat(curGB, curCat);
																break;
																
															/*Delete an item in curCat*/
															case "2":
																deleteItemFromCat(curGB, curCat);
																break;
															
															/*Change current Category's name*/
															case "3":
																//type 1 = category's name
																changeCatOrWeight(curGB, curCat, 1);
																break;
																
															/*Change current Category's weight*/
															case "4":
																changeCatOrWeight(curGB, curCat, 0);
																break;
																
															/*Return to previous menu*/
															case "r":
															case "R":
																break;
																
															/*if choose none above -> Invalid options*/	
															default:
																throw new Exception();
																
														}//End of switch category's edit menu
														
														break; //exit the this do-while loop
														
													}catch(Exception eInput){
														eInput.printStackTrace();
														System.out.println("Invalid edit option! try again");
													}
													
												}while(true);
												
												break;
												
											/* Return to GB menu*/
											case 'r':
											case 'R':	
												repeatEditGB = false;
												break;
											
											default:
												throw new Exception();
												
												
										}// End of switch-edit options
										
										
									}catch(Exception e1){
										e1.printStackTrace();
										System.out.println("Invalid edit option, try again!");
									}
									
								}while(repeatEditGB);
								
							}catch(Exception e1){
								e1.printStackTrace();
								System.out.println("Invalid gradebook, please try again!");
							}
							
							
							
						}while(repeatGBMenu);
						
						break;
						
					//Showing GB information  
					case '3':
						if(listGB.isEmpty()) {
							System.out.println("There is no gradebook to display. Please add one!");
							break;
						}
						
						showListGB();
						System.out.println("\tall : to show all gradebook");
						System.out.print("->");
						user = scan.nextLine();
						
						//Show ALL gradebooks
						if(user.equalsIgnoreCase("all")) {
							for (GradeBook temp : listGB) {
								System.out.println(temp);
							}
						}
						else if(user.equalsIgnoreCase("r")) {
							continue;
						}
						
						//Show specific GB
						else {
							System.out.println(listGB.get(Integer.parseInt(user)));
						}
						break;
						
					//End the program	
					case 'q':
					case 'Q':
						System.out.println("Exiting program, bye !");
						repeatMainMenu = false;
						break;
						
					default:
						throw new Exception();
				} // End switch of MAIN menu
			
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Error ! Invalid option, try again!");
				continue;
			}
		}while(repeatMainMenu);
		
	}
	
/* Get the user input using Scanner*/	
	public static String getUserInput() {
		String userInput = "";
		Scanner input = new Scanner(System.in);
		System.out.print("-> ");
		userInput = input.nextLine();
		return userInput;
	}
	
	
	public static void showMainMenu() {
		System.out.println("------------------------ MAIN MENU ---------------------------");
		System.out.println("Choose to number corresponding to options:");
		System.out.println("\t1. Create NEW gradebook");
		System.out.println("\t2. Open existed gradebook");
		System.out.println("\t3. Show gradebook");
		System.out.println("\tPress Q/q to quit program");

	}
	
	public static void showListGB() {
		System.out.println("\nSelect the number according GB want to open");
		for(GradeBook temp : listGB) {
			System.out.println("\t" + listGB.indexOf(temp)+ " : " + temp.getGBName());
		}	
		System.out.println("\tR or r : to return the MAIN menu");
	}

	
	public static Boolean showListCategory(int indexGB) {
		if(listGB.get(indexGB).getListCategory().isEmpty()){
			System.out.println("Nothing to display! The category list is EMPTY.");
			return false;
		}
		System.out.println("\nList of Category in Gradebook_" + listGB.get(indexGB).getGBName() + ":");	
		for(Category tempCat : listGB.get(indexGB).getListCategory()) {
			System.out.printf("==> Category %d: %s \t\t Weight: %.01f\n", listGB.get(indexGB).getListCategory().indexOf(tempCat), tempCat.getCatName(), tempCat.getWeight());
			System.out.println(tempCat);
		}
		System.out.println();
		return true;
	}
	
	
	
	public static void editGBMenu() {
		System.out.println("\nWhat do you want to edit?");
		System.out.println("\t 1 : Add NEW category");
		System.out.println("\t 2 : Delete cateory");
		System.out.println("\t 3 : Change GB name");
		System.out.println("\t 4 : Edit category");
		System.out.println("\t r : Return to gradebook's menu");
	}
	
	public static void editCategoryMenu() {
		System.out.println("\nChoose one option below for current category:");
		System.out.println("\t 1 : Add NEW item");
		System.out.println("\t 2 : Delete item");
		System.out.println("\t 3 : Change category's name");
		System.out.println("\t 4 : Change weight");
		System.out.println("\t r : Return to Category's menu");
	}
	
	public static void addItemToCat(int targetGB, int targetCat) {
		String name = "";
		String grade = "";
		LinkedList<Category> catList = listGB.get(targetGB).getListCategory();
		
		System.out.print("Item name ");
		name = getUserInput();
		
		System.out.print("Grade ");
		grade = getUserInput();
		
		// if the Category already have the item, print error msg
		if (!catList.get(targetCat).addItem(new Item(name, grade))) {
			System.out.println("Item already existed! Try again");
			return;
		}
		System.out.println("Item added!");
	}
	
	// Done but not TEST
	public static void deleteItemFromCat(int indexGB, int indexCat) {
		LinkedList<Category> catList = listGB.get(indexGB).getListCategory();
		do {
			try {
				String targetItem = "";
				showListItem(indexGB, indexCat);
				System.out.println("Select item want to delete from list above ");
				System.out.println("OR C/c to cancel the action ");
				targetItem = getUserInput();
				
				if(targetItem.equalsIgnoreCase("c")) {
					break;
				}
				
				else {
					//If error happen, it will be catched in the catch block and continue ask for the input
					System.out.printf("Item %s removed", listGB.get(indexGB).getListCategory().get(indexCat).deleteItemAt(Integer.parseInt(targetItem)).getItemName());
					break;
					
				}
				
			}catch(Exception eInput){
				eInput.printStackTrace();
			}
			
		}while(true);
		
	}
	
	
	public static boolean showListItem(int indexGB , int indexCat){
		if(listGB.get(indexGB).getListCategory().get(indexCat).getListItems().isEmpty()) {
			System.out.println("This category is EMPTY, please add new item");
			return false;
		}
		System.out.println("\nList of item(s) in " + listGB.get(indexGB).getListCategory().get(indexCat).getCatName());	
		for(Item tempItem : listGB.get(indexGB).getListCategory().get(indexCat).getListItems()) {
			System.out.printf("\tItem %d: %s \t\t Grade: %s\n", listGB.get(indexGB).getListCategory().get(indexCat).getListItems().indexOf(tempItem)
					, tempItem.getItemName(), tempItem.getItemGrade());

		}
		return true;
	}
	
	
	public static void changeCatOrWeight(int indexGB, int indexCat, int type) {
		Scanner input = new Scanner(System.in);
		LinkedList<Category> curListCat = listGB.get(indexGB).getListCategory();
		
		if((listGB.contains(listGB.get(indexGB))) && (curListCat.contains(curListCat.get(indexCat)))) {
			if(type == 1) {
				System.out.print("Please enter NEW Category's name: ");
				String user = input.nextLine();
				curListCat.get(indexCat).setCatName(user);
				System.out.println("Category's name updated sucessfully!");
			}
			else {
				System.out.print("Please enter NEW Category's weight: ");
				String user = input.nextLine();
				curListCat.get(indexCat).setWeight(Double.parseDouble(user));
				System.out.println("Category's weight updated sucessfully!");
			}
			input.close();
			return;
		}
		
		System.out.println("Error! The category is invalid");
		return;
		
		
	}
	
	
}

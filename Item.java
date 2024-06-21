package components;

import java.util.LinkedList;

public class Item {
	String itemName;
	String itemGrade;
	
	//Constructor(s)
	
	public Item(String itemName) {
		this.itemName = itemName;
		this.itemGrade = "0/0";
	}
	
	public Item(String itemName, String curGrade) {
		this.itemName = itemName;
		this.itemGrade = curGrade;
	}
	
	//Setters
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public void setItemGrade(String itemGrade) {
		this.itemGrade = itemGrade;
	}
	
	
	//Getters
	public String getItemName() {
		return this.itemName;
	}
	
	public String getItemGrade() {
		return this.itemGrade;
	}
	
	public Double getItemPercent(){
		String[] temp = itemGrade.split("/");
		Double n1 = Double.parseDouble(temp[0]);
		Double n2 = Double.parseDouble(temp[1]);
		return (n1/n2)* 100.0;
		
	}
	
	
	public boolean equals(Object o){
        if((o != null ) && (o instanceof Item)){
            return this.getItemName().equals(((Item)o).getItemName());
        }
        return false;
    }
	
/**************************************************************/
/*
	public static void main(String[] args) {
		Item test1 = new Item("Test1", 90.0);
		Item test2 = new Item("Test1", 90.0);
		
		Item test3 = new Item("Test2");
		System.out.println("(Test1, 90.0) vs. (Test1, 90.0) Result: " + test1.equals(test2));
		System.out.println("(Test1, 90.0) vs. (Test2, 0.0) Result: " + test1.equals(test3));

	}
*/
	 
}

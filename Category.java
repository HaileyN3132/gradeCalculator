package components;

import java.util.LinkedList;




public class Category {
	private String catName;
	private Double weight; 
	//private Double catGrade;
	private LinkedList<Item> listItems;
	
	
//Constructor
	public Category(String catName, Double weight) {
		this.catName = catName;
		this.weight = weight;
		//this.catGrade = 0.0;
		this.listItems = new LinkedList<>();
	}
	
//Setter(s)
	public void setCatName(String catName) { 
		this.catName = catName;
	}
	
	public void setWeight(Double weight) { 
		this.weight = weight;
	}
	
//Getter(s)
	public String getCatName() {
		return this.catName;
	}
	
	public Double getWeight() {
		return this.weight;
	}
	
	public Item getItemAt(int index){
    	return listItems.get(index);
    }

/*Get list of items for a category*/	
	public LinkedList<Item> getListItems(){
		return this.listItems;
	}
		
	
//Add item into list
	public boolean addItem(Item newItem) {
		// Invalid add, not allow duplicate item
        if((listItems.contains(newItem) || (newItem == null))){
            return false;
        }
        
        listItems.add(newItem);
        return true;
	}
	
//Remove item from list by name
	public boolean deleteItem(Item target) {
		// Remove invalid item 
		if((!listItems.contains(target) || (target == null))){
			 return false;
	    }
		 
		 listItems.remove(target);
		 return true;
	}
	
//Remove item at specific index
	public Item deleteItemAt(int index) {
		return listItems.remove(index);
	}
	
//Check number item in list
	public int getNumItem() {
		return listItems.size();
	}
	

//Calculate average in the list
	public double listItemsAvg(){
		double sum = 0;
		if(listItems.isEmpty()) {
			return 0.0;
		}
		
		for(Item temp : listItems) {
			sum += temp.getItemPercent();
		}
		
		return sum/listItems.size();
	}

	
//Calculate the grade based on items
	public Double getCatGrade() {
		return (listItemsAvg() * this.weight)/ 100.0;
	}	

//Override the equals()	
	public boolean equals(Object o){
        if((o != null ) && (o instanceof Category)){
            return this.getCatName().equals(((Category)o).getCatName());
        }
        return false;
    }

// Print Category information
	public String toString() {
        System.out.println("\n\tCategory: " + this.getCatName() + "\tWeight " + this.getWeight() + "%\n");
       
		System.out.println("\t\tItem \t\t| Grade  \t\t| In %");
        System.out.println("\t\t------------------------------------------------");
        for(Item t : listItems){
            System.out.printf("\t\t%s\t\t| %s\t\t|%.02f\n", t.getItemName(), t.getItemGrade(), t.getItemPercent());
            //System.out.println("\t\t" + t.getName() + "\t\t| " + t.getWeight() + "\t\t| " + t.getCurrentGrade());
        }
        System.out.println("\t\t************************************************");
        System.out.printf("\t\tCurrent grade: %.02f \t\tAverage: %.02f\n", this.getCatGrade(), this.listItemsAvg());

        return "";
	}
	
	public static void main(String[] args) {
		Item i1 = new Item("HW1", "99/100");
		Item i2 = new Item("DB1", "20/100");
		Item i3 = new Item("DB2", "80/100");
		
		Category cat1 = new Category("Homeworks", 20.0);
		cat1.addItem(i1);
		
		Category cat2 = new Category("Discussion", 15.0);
		cat2.addItem(i2);
		cat2.addItem(i3);
		
		System.out.println(cat1);
		System.out.println(cat2);
		
		
		System.out.println(cat2.deleteItem(i3));
	
		
		
		
	}

}

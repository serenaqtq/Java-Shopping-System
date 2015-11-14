/*
* Name:​Tianqi Qi,Yihan Lu, Shiyu Luo
* MacID:​qit3, luy27, luos
* Student Number:​1405930,1428072, 1317135
* Description:​This class build the shopping cart for each user
*/
import java.io.BufferedReader;//import BufferedReader
import java.io.BufferedWriter;//import BufferedWriter
import java.io.File;
import java.io.FileNotFoundException;//import FileNotFoundException
import java.io.FileReader;//import FileReader
import java.io.FileWriter;//import FileWriter
import java.io.IOException;//import IOException
import java.util.ArrayList;//import ArrayList
import java.util.Date;//Import Date Function
import java.text.DateFormat;//Import DateFormat
import java.text.SimpleDateFormat;//Import SimpleDateFormat

//Question, in the readable and audio text files, does an item still exist if it has 0 quantity? If it doesn't, how do we find the type of an item in the shopping cart if it's sold out?

public class ShoppingCart extends User{//public ShoopingCart
	
	private ArrayList<Readable> contentR = new ArrayList<Readable>();
	private ArrayList<Audio> contentA = new ArrayList<Audio>();
	
	private ArrayList<Book> b1 = new ArrayList<Book>();
	private ArrayList<MP3> m1 = new ArrayList<MP3>();
	private ArrayList<eBook> e1 = new ArrayList<eBook>();
	private ArrayList<CD> c1 = new ArrayList<CD>();
	private String temp1;
	
	public ShoppingCart(String name){//Constructor which take in a string as user name
		
		super(name);//call parent's constructor
		
		try {//try-catch structure to catch ant error
			BufferedReader input = new BufferedReader(new FileReader("Cart_" + this.getUsername() + ".txt"));//read in file
			
			String line = null;//define a string to store each line of the file
			while ((line = input.readLine()) != null) {//while loop to loop thorough the file
				String[] temp = line.split(", ");
				int quan = Integer.parseInt(temp[3]);
				temp1 = checkType(temp[1]);
				
				if (temp1.equals("Book")) {
					contentR.add(stringToBook(temp1, quan));
				}
				
				if (temp1.equals("eBook")) {
					contentR.add(stringToEbook(temp1, quan));
				}
				
				if (temp1.equals("CD")) {
					contentA.add(stringToCD(temp1,quan));
				}
				
				if (temp1.equals("MP3")) {
					contentA.add(stringToMP3(temp1,quan));
				}
			}
			
			input.close();//close the file
			
		} catch (FileNotFoundException f) {//catch FileNotFoundException
			try {
				File createShoppingCart = new File ("Cart_"+name+".txt"); //Create a new file object for user
				if(createShoppingCart.createNewFile()){//Create a new file for user's shopping cart an returns a boolean value true if the file is created successfully, vice versa
					return;
				}else {
			        System.out.println("File already exists.");
				}
			}catch(IOException e) {//catch IOException
				System.out.println("There is an error.");///print out the error message
			}
			
		} catch (IOException e) {
			System.out.println("There is an error");
		}
	}
	
	public void readBook() {
		try {//try-catch structure
			BufferedReader inputB = new BufferedReader(new FileReader("Books.txt"));//file reader
			
			String lineB = null;//create a string to store each line of the file
			while ((lineB = inputB.readLine()) != null) {//loop through the file
				b1.add(stringToBook(lineB));//add book to ArrayList
			}
			
			inputB.close();//close the file
			
		} catch(IOException e) {//catch IOException
			System.out.println("There is an error.");//print out the error message
		}
	}//read Book.txt into ArrayList
//
//	
//	public void read_eBook() {
//		try {//try-catch structure
//			BufferedReader inputE = new BufferedReader(new FileReader("EBooks.txt"));//file reader
//			
//			String lineE = null;//create a string to store each line of the file
//			while ((lineE = inputE.readLine()) != null) {//loop through the file
//				e1.add(stringToEbook(lineE));//add eBook to ArrayList
//			}
//			
//			inputE.close();//close the file
//		} catch(IOException e) {//catch IOException
//			System.out.println("There is an error.");//print out the error message
//		}
//	}//read eBook.txt into ArrayList
//	
//	public void readCD() {
//		
//		try {//try-catch structure
//			BufferedReader inputC = new BufferedReader(new FileReader("CDs.txt"));//file reader
//			
//			String lineC = null;//create a string to store each line of the file
//			while ((lineC = inputC.readLine()) != null) {//loop through the file
//				c1.add(stringToCD(lineC));//add CD to ArrayList
//			}
//		} catch(IOException e) {//catch IOException
//			System.out.println("There is an error.");///print out the error message
//		}
//	}//read CD.txt into ArrayList
//	
//	public void readMP3() {
//		
//		try {//try-catch structure
//			BufferedReader inputM= new BufferedReader(new FileReader("MP3.txt"));//file reader
//			
//			String lineM = null;//create a string to store each line of the file
//			while ((lineM = inputM.readLine()) != null) {//loop through the file
//				m1.add(stringToMP3(lineM));//add CD to ArrayList
//			}
//			
//			inputM.close();//close the file
//		} catch(IOException e) {//catch IOException
//			System.out.println("There is an error.");///print out the error message
//		}
//	}//read MP3.txt into ArrayList
	
//	public eBook stringToEbook(String str) {//Convert a string to ebook
//		
//		String[] temp = str.split(", ");//split the string array
//		eBook e1 = new eBook(Integer.parseInt(temp[0]), (double)Integer.parseInt(temp[1]), temp[2], temp[3], Integer.parseInt(temp[4]));//call the constructor
//		
//		return e1;//return the ebook
//	}
//	
//	public CD stringToCD(String str) {//Convert a string to CD
//		
//		String[] temp = str.split(", ");//split the string array
//		CD c1 = new CD(Integer.parseInt(temp[0]), (double)Integer.parseInt(temp[1]), temp[2], temp[3], Integer.parseInt(temp[4]));//call the constructor
//		
//		return c1;//return the CD
//	}
//	
//	public MP3 stringToMP3(String str) {//Convert a string to MP3
//		
//		String[] temp = str.split(", ");//split the string array
//		MP3 m1 = new MP3(Integer.parseInt(temp[0]), (double)Integer.parseInt(temp[1]), temp[2], temp[3], Integer.parseInt(temp[4]));//call the constructor
//		
//		return m1;//return the MP3
//	}
//
	
	public void readeBook() {
		try {//try-catch structure
			BufferedReader inputE = new BufferedReader(new FileReader("EBooks.txt"));//file reader
			
			String lineE = null;//create a string to store each line of the file
			while ((lineE = inputE.readLine()) != null) {//loop through the file
				e1.add(stringToEbook(lineE));//add eBook to ArrayList
			}
			
			inputE.close();//close the file
		} catch(IOException e) {//catch IOException
			System.out.println("There is an error.");//print out the error message
		}
	}//read eBook.txt into ArrayList
	
	public void readCD() {
		
		try {//try-catch structure
			BufferedReader inputC = new BufferedReader(new FileReader("CDs.txt"));//file reader
			
			String lineC = null;//create a string to store each line of the file
			while ((lineC = inputC.readLine()) != null) {//loop through the file
				c1.add(stringToCD(lineC));//add CD to ArrayList
			}
			
			inputC.close();
		} catch(IOException e) {//catch IOException
			System.out.println("There is an error.");///print out the error message
		}
	}//read CD.txt into ArrayList
	
	public void readMP3() {
		
		try {//try-catch structure
			BufferedReader inputM= new BufferedReader(new FileReader("MP3.txt"));//file reader
			
			String lineM = null;//create a string to store each line of the file
			while ((lineM = inputM.readLine()) != null) {//loop through the file
				m1.add(stringToMP3(lineM));//add CD to ArrayList
			}
			
			inputM.close();//close the file
		} catch(IOException e) {//catch IOException
			System.out.println("There is an error.");///print out the error message
		}
	}//read MP3.txt into ArrayList
	
	public Book stringToBook(String str) {

		String[] temp = str.split(", ");
		Book b1 = new Book(Integer.parseInt(temp[0]), (double)Integer.parseInt(temp[1]), temp[2], temp[3], Integer.parseInt(temp[4]));
		return b1;
	}
	
	public eBook stringToEbook(String str) {//Convert a string to ebook
		
		String[] temp = str.split(", ");//split the string array
		eBook e1 = new eBook(Integer.parseInt(temp[0]), (double)Integer.parseInt(temp[1]), temp[2], temp[3], Integer.parseInt(temp[4]));//call the constructor
		
		return e1;//return the ebook
	}
	
	public CD stringToCD(String str) {//Convert a string to CD
		
		String[] temp = str.split(", ");//split the string array
		CD c1 = new CD(Integer.parseInt(temp[0]), (double)Integer.parseInt(temp[1]), temp[2], temp[3], Integer.parseInt(temp[4]));//call the constructor
		
		return c1;//return the CD
	}
	
	public MP3 stringToMP3(String str) {//Convert a string to MP3
		
		String[] temp = str.split(", ");//split the string array
		MP3 m1 = new MP3(Integer.parseInt(temp[0]), (double)Integer.parseInt(temp[1]), temp[2], temp[3], Integer.parseInt(temp[4]));//call the constructor
		
		return m1;//return the MP3
	}

	public Book stringToBook(String str, int quan) {
		
		Book temp = null;
		for (int i = 0; i < b1.size(); i++) {
			
			if (b1.get(i).getTitle().equals(str)) {
				temp = new Book(b1.get(i).getsNo(), b1.get(i).getPrice(), str, b1.get(i).getAuthorName(), quan); 
			}
		}
		return temp;
	}
	
	public eBook stringToEbook(String str, int quan) {
		eBook temp = null;
		for (int i = 0; i < e1.size(); i++) {
			if (e1.get(i).getTitle().equals(str)) {
				temp = new eBook(e1.get(i).getsNo(), b1.get(i).getPrice(), str, b1.get(i).getAuthorName(),quan);
			}
		}
		return temp;
	}
	
	public CD stringToCD(String str, int quan) {
		CD temp = null;
		for (int i = 0; i < c1.size(); i++) {
			if (c1.get(i).getTitle().equals(str)) {
				temp = new CD(c1.get(i).getsNo(), c1.get(i).getPrice(), str, c1.get(i).getAuthorName(),quan);
			}
		}
		return temp;
	}
	
	public MP3 stringToMP3(String str, int quan) {
		MP3 temp = null;
		for (int i = 0; i < m1.size(); i++) {
			if (m1.get(i).getTitle().equals(str)) {
				temp = new MP3(m1.get(i).getsNo(), m1.get(i).getPrice(), str, m1.get(i).getAuthorName(),quan);
			}
		}
		return temp;
	}
	
	public String checkType(String str) {
		
		int check = 0;
		for (int i = 0; i < b1.size(); i++) {
			
			if (b1.get(i).getTitle().equals(str)) {
				check = 1; 
			}
		}
		
		for (int i = 0; i < e1.size(); i++) {
			
			if (e1.get(i).getTitle().equals(str)) {
				check = 2;
			}
		}
		
		for (int i = 0; i < m1.size(); i++) {
			
			if (m1.get(i).getTitle().equals(str)) {
				check = 3;
			}
		}
		
		for (int i = 0; i < c1.size(); i++) {
			
			if (c1.get(i).getTitle().equals(str)) {
				check = 4;
			}
		}
		
		if (check == 1) {return "Book";}
		if (check == 2) {return "eBook";}
		if (check == 3) {return "MP3";}
		if (check == 4) {return "CD";}
		else {return "No type match!";}
		
	}
	
//	public int getIndex(Item name, String str) {//return the index of the ArrayList of a given item
//		
//		int index = 0;
//		if (str.equals("Book") || str.equals("eBook")) {
//			index = contentR.indexOf((Readable)name);
//		}
//		if (str.equals("Book") || str.equals("eBook")) {
//			index = contentA.indexOf((Audio)name);
//		}
//		return index;
//	}
	
	public void addQuantity(int index, int quan, String str){//add given quantity to the item at given index
		
		if (str.equals("Book") || str.equals("eBook")) {
			(contentR.get(index)).changeQuantityC(quan);//update the quantity for the item
		}
		if (str.equals("Book") || str.equals("eBook")) {
			(contentA.get(index)).changeQuantityC(quan);//update the quantity for the item
		}
		
	}
	
	public void getContent() {//return the content of the cart as string
		
		try {//try-catch structure
			BufferedReader inputB = new BufferedReader(new FileReader("Cart_"+ getUsername()+ ".txt"));//file reader
			
			String lineB = null;//create a string to store each line of the file
			while ((lineB = inputB.readLine()) != null) {//loop through the file
				System.out.println(lineB);
			}
			
			inputB.close();//close the file
			
		} catch(IOException e) {//catch IOException
			System.out.println("There is an error in getContent().");//print out the error message
		}
	}
	
	public double getEnvirTax() {
		
		double tax = 0;
		for (int i = 0; i < contentR.size(); i++) {
			if (contentR.get(i).getType().equals("Book")) {
				tax += ((contentR.get(i)).getPrice() - contentR.get(i).getPrice()/1.02) * contentR.get(i).getQuantity();
			}
		}
		for (int i = 0; i < contentA.size(); i++) {
			if (contentA.get(i).getType().equals("CD")) {
				tax += ((contentA.get(i)).getPrice() - contentA.get(i).getPrice()/1.02) * contentA.get(i).getQuantity();
			}
		}
		return tax;
	}
	
	public double getShipping() {//return the shipping fee of the cart
		
		double shipping = 0;//create a double to store the shipping fee
		for (int i = 0; i < contentR.size();i++) {//loop through the ArrayList
			
			if (contentR.get(i).getType() == "Book") {//eBooks don't need shipping
				shipping += contentR.get(i).getQuantity() * contentR.get(i).getPrice();//increment the shipping by the quantity*price
		
			}
		}
		
		for (int i = 0; i < contentA.size();i++) {//loop through the ArrayList
			
			if (contentA.get(i).getType() == "CD") {//MP3s don't need shipping
				shipping += contentA.get(i).getQuantity() * contentA.get(i).getPrice();//increment the shipping by the quantity*price
		
			}
		}
		return shipping * 0.1;//return shipping * 10%
	}
	
	public double getHST() {//return the hst of the cart
		
		double hst = 0;//create a double to store the hst
		for (int i = 0; i < contentA.size();i++) {//loop through the ArrayList
			
			hst += contentA.get(i).getQuantity() * contentA.get(i).getPrice();//increment the hst by the quantity*price
		}
		
		for (int i = 0; i < contentR.size();i++) {//loop through the ArrayList
			
			hst += contentR.get(i).getQuantity() * contentR.get(i).getPrice();//increment the hst by the quantity*price
		}
		return hst * 0.13;//return hst * 13%
	}
	
	public double getTotal() {//return the total amount of the cart
		
		double total = 0;//create a double variable to store the total amount
		for (int i = 0; i < contentA.size();i++) {//loop through the ArrayList
			
			total += contentA.get(i).getQuantity() * contentA.get(i).getPrice();//increment the hst by the quantity*price
		}
		
		for (int i = 0; i < contentR.size();i++) {//loop through the ArrayList
			
			total += contentR.get(i).getQuantity() * contentR.get(i).getPrice();//increment the hst by the quantity*price
		}
		return total + getHST() + getShipping();//return the total price by adding hst, environmental tax and shipping
	}
	
	public void AddItem(String title, int quan){//add an item to the cart
		String temp1 = checkType(title);//Check the type of the item added
		int sNoDisplay = 0;
		//int quantityDisplay;
		
		if (temp1.equals("Book")) {
			for (int i = 0; i < b1.size(); i++) {
				if (temp1.equals(b1.get(i).getTitle())) {
					sNoDisplay = b1.get(i).getsNo();
					contentR.add(b1.get(i));
					addQuantity(contentR.indexOf(b1.get(i)), quan, "Book");
				}
			}
		}
		
		if (temp1.equals("eBook")) {
			for (int i = 0; i < e1.size(); i++) {
				if (temp1.equals(e1.get(i).getTitle())) {
					contentR.add(e1.get(i));
					sNoDisplay = e1.get(i).getsNo();
					addQuantity(contentR.indexOf(e1.get(i)), quan, "eBook");
				}
			}
		}
		
		if (temp1.equals("CD")) {
			for (int i = 0; i < c1.size(); i++) {
				if (temp1.equals(c1.get(i).getTitle())) {
					contentA.add(c1.get(i));
					sNoDisplay = c1.get(i).getsNo();
					addQuantity(contentA.indexOf(c1.get(i)), quan, "CD");
				}
			}
		}
		
		if (temp1.equals("MP3")) {
			for (int i = 0; i < m1.size(); i++) {
				if (temp1.equals(m1.get(i).getTitle())) {
					contentA.add(m1.get(i));
					sNoDisplay = m1.get(i).getsNo();
					addQuantity(contentA.indexOf(m1.get(i)), quan, "MP3");
				}
			}
		}
		
		
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter("Cart_" + getUsername() + ".txt"));
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Get current yyyy/MM/dd and display it as it is
			//get current date time with Date()
			Date date = new Date();
			
			String display = sNoDisplay + ", " + title + ", " + dateFormat.format(date) + ", " + quan;
			
			output.write(display);
			output.newLine();
			output.close();
		} catch (IOException e) {
			System.out.println("There is an error in AddItem()");
		}
	}
	
	public void clearCart(String userName) {
		File deleteShoppingCart = new File ("Cart_"+userName+".txt");//Create a file object for the user shopping cart text file
		try {
		if (deleteShoppingCart.delete()){//deletes the file and returns true if the file is deleted successfully
			try {
				if (deleteShoppingCart.createNewFile()) {//creates the file and returns true if the file is created successfully
					return;
				}else {
			        System.out.println("File already exist");//prints error message the error already exists
				}
			}catch (IOException e) {
		        System.out.println("File not created");//prints error message
			}
		}else {
	        System.out.println("File not deleted");//prints error message file can not be deleted
		}
		}catch (Exception e) {  
	        System.out.println("Some error occured while deleting file");//prints error messgae
		 } 
	}

	
//	public void writeCart() {//write the ArrayList back to the text file
//		
//		try {
//			BufferedWriter output = new BufferedWriter(new FileWriter("Cart_" + super.getUsername() + ".txt", false));//file writer
//			for (int i = 0; i < contentR.size(); i++) {
//				output.write(contentR.get(i).getsNo(),contentR.get(i).getTitle());
//				output.newLine();
//			}
//			output.close();//close the file
//		} catch (IOException e) {}//Catch IOException
//	}
}

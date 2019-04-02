import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;

public class Customer {
	/** Instance variables for the class that are set to default values. */ 
	private String name = "No Name";
	private int customerID = 0;
	
	public Customer() {}

	/** Constructor.
     * Allows instantiation of Customer to be able to obtain the values of 
     * instance variables from a specified text file.
     * @param reader Takes a BufferedReader as a parameter.
     * @throws IOException */    
	public Customer(BufferedReader reader) throws IOException {
		String name = reader.readLine();
		
		if (name.equals("null")) {
			System.out.println("No customer ID found in file");
			throw new IOException("Customer is null in file");
		}
		String idS = reader.readLine();
		if (idS == null) {
			System.out.println("No customer ID found in file");
			throw new IOException("No customer ID found in file");
		}
		int id = Integer.parseInt(idS);
		setName(name);
		setID(id);
	}

	/** Constructor.
     * @param name Takes the name of the customer as a parameter. */
	public Customer(String name){
		setName(name);
	}

	/** Constructor.
     * @param name Takes the name of the customer as a parameter. 
	 * @param customerID Takes the ID of the customer as a parameter.*/
	public Customer(String name, int customerID) {
		this(name);
		setID(customerID);
	}

    /** Copy constructor.
     * @param copy Takes a Customer object as an argument and creates a copy of it*/	
	public Customer(Customer copy) {
		this(copy.name, copy.customerID);
	}
	
    /** Getter. 
     * @return the instance variable <name>.*/	
	public String getName() {
		return new String(this.name);
	}

	/** Getter. 
     * @return the instance variable <customerID>.*/
	public int getID() {
		return new Integer(this.customerID);
	}

    /** Setter.
     * Sets the name of the customer.
     * @param newName This is their name.*/	
	public void setName(String newName) {
		this.name = new String(newName);
	}

    /** Setter.
     * Sets the ID of the customer.
     * @param newID This is their ID.*/		
	public void setID(int newID) {
		this.customerID = new Integer(newID);
	}

	/** Allows the instance variables to be written into a text file.
     * @param fileName
     * @throws IOException */
	public void save(PrintWriter writer) throws IOException {
		writer.write(getName() + System.getProperty("line.separator"));
		writer.write(getID()+ System.getProperty("line.separator"));
	}

	/** Allows the instance variables to be printed in an organized manner. */
	public String toString() {
		return getName() + " " + getID();
	}
}
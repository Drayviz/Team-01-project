import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;

public class Customer {
	private String name = "Mr. No Name";
	private int customerID = 0;
	
	public Customer() {}
	public Customer(BufferedReader reader) throws IOException {
		String name = reader.readLine();
		if (name.equals("null")) {
			throw new IOException("Customer is null in file");
		}
		String idS = reader.readLine();
		if (idS == null) {
			throw new IOException("No customer ID found in file");
		}
		int id = Integer.parseInt(idS);
		setName(name);
		setID(id);
	}
	public Customer(String name){
		setName(name);
	}
	public Customer(String name, int customerID) {
		this(name);
		setID(customerID);
	}
	public Customer(Customer copy) {
		this(copy.name, copy.customerID);
	}
	
	public String getName() {
		return new String(this.name);
	}
	public int getID() {
		return new Integer(this.customerID);
	}

	public void setName(String newName) {
		this.name = new String(newName);
	}
	public void setID(int newID) {
		this.customerID = new Integer(newID);
	}

	public void save(PrintWriter writer) throws IOException {
		writer.write(getName() + System.getProperty("line.separator"));
		writer.write(getID()+ System.getProperty("line.separator"));
	}
	public String toString() {
		return getName() + " " + getID();
	}
}
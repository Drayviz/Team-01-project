import java.util.Scanner;

public class Tutorial1 {
	public static void main(String[] args) {
		//System.out.println("Hello world!");
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		String name = input.nextLine();
		System.out.println("Hello " + name);	
	}
}

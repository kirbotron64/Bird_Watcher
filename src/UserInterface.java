import java.util.Scanner;

public class UserInterface {
	
	private Scanner scanner;
	private BirdWatcher birdWatcher;
	
	public UserInterface(Scanner scanner, BirdWatcher birdWatcher){
		this.scanner = scanner;
		this.birdWatcher = birdWatcher;
	}
	
	public void start() {
		while(true) {
			System.out.print("?");
			String userInput = scanner.nextLine();
			
			if(userInput.equals("Add")) {
				birdWatcher.add();
			}
			else if(userInput.equals("Observation")){
				System.out.print("Bird?");
				userInput = scanner.nextLine();
				birdWatcher.observe(userInput);
			}
			else if(userInput.equals("All")) {
				birdWatcher.printAll();
			}
			else if(userInput.equals("One")) {
				System.out.print("Bird?");
				userInput = scanner.nextLine();
				birdWatcher.printOne(userInput);
			}
			else if(userInput.equals("Quit")) {
				break;
			}
		}
		
	}
}

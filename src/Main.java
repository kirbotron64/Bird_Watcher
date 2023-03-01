import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		BirdWatcher birdWatcher = new BirdWatcher();
		Scanner scanner = new Scanner(System.in);
		UserInterface ui = new UserInterface(scanner, birdWatcher);
		
		ui.start();
	}
}

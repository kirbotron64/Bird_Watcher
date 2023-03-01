import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class BirdWatcher {
	private ArrayList<String> birdsEnglish;
	private ArrayList<String> birdsLatin;
	private Scanner scanner;
	// this is a comment
	public BirdWatcher() {
		this.birdsEnglish = new ArrayList<>();
		this.birdsLatin = new ArrayList<>();
		this.scanner = new Scanner(System.in);
	}
	
	
	public void add() {
		System.out.println("Name:");
		String input = scanner.nextLine();
		
		if(isBird(input) && inList(input) == false) {
			birdsEnglish.add(input);
		}
		else {
			System.out.println("Either not bird or already in list.");
			return;
		}
		
		System.out.print("Name in Latin:");
		input = scanner.nextLine();
		birdsLatin.add(input);
		writeToBirds();	
	}
	
	
	public void observe(String bird) {
		if(!isBird(bird)) {
			System.out.println("Not a bird!");
			return;
		}
		
		ArrayList<String> tempFile = new ArrayList<>();
		try(Scanner scanner = new Scanner(Paths.get("Birds"))){
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				tempFile.add(line);
			}
			int counter = -1;
			for(String lineItem : tempFile) {
				counter++;
				String[] lineSplit = lineItem.split(",");
				if(lineSplit[0].equals(bird)) {
					int sightings = Integer.valueOf(lineSplit[2]);
					sightings++;
					lineSplit[2] = String.valueOf(sightings);
					String newLine = lineSplit[0] + "," + lineSplit[1] + "," + lineSplit[2];
					tempFile.remove(counter);
					tempFile.add(counter, newLine);
				}	
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try(PrintWriter myWriter = new PrintWriter("Birds")){
			for(String line : tempFile) {
				myWriter.println(line);
			}
			myWriter.flush();
			myWriter.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public boolean isBird(String input) {
		try(Scanner scanner = new Scanner(Paths.get("PossibleBirds"))){
			while(scanner.hasNextLine()) {
				if(scanner.nextLine().equals(input)) {
					return true;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
	public boolean inList(String input) {
		
		try(Scanner scanner = new Scanner(Paths.get("Birds"))){
			while(scanner.hasNextLine()) {
				if(scanner.nextLine().contains(input)) {
					return true;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
	public void writeToBirds() {
		int lastIndex = birdsEnglish.size() - 1;
		ArrayList<String> tempFile = new ArrayList<>();
		
		try(Scanner scanner = new Scanner(Paths.get("Birds"))){
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.contains(birdsEnglish.get(lastIndex))) {
					return;
				}
				tempFile.add(line);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try(PrintWriter myWriter = new PrintWriter("Birds")){
			for(String line : tempFile) {
				myWriter.println(line);
			}
			myWriter.println(birdsEnglish.get(lastIndex) + "," + birdsLatin.get(lastIndex) + ",0");
			myWriter.flush();
			myWriter.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}  
	}
	
	
	public void printAll() {
		try(Scanner scanner = new Scanner(Paths.get("Birds"))){
			while(scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void printOne(String bird) {
		
		try(Scanner scanner = new Scanner(Paths.get("Birds"))){
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.contains(bird)) {
					String[] lineSplit = line.split(",");
					System.out.println(lineSplit[0] + " (" + lineSplit[1] + "):" + lineSplit[2] + " observations");
				}
				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

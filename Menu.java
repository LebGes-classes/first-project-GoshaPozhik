import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    
    public void display() {
        while(true) {
            clearConsole();
            System.out.println("=== MAZE GAME ===");
            System.out.println("1. New Game");
            System.out.println("2. Exit");
            System.out.print("Select: ");
            
            String choice = scanner.nextLine();
            if(choice.equals("1")) {
                new GameManager().start();
            } else if(choice.equals("2")) {
                return;
            }
        }
    }
    
    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch(Exception e) {
            System.out.print("\033[H\033[2J");
        }
        System.out.flush();
    }
}
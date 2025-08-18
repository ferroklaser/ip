import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        String logo = "Echo";
        System.out.println("Hello! I'm " + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("What can I do for you?");

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                System.out.println(command);
            }
        }
        System.out.println("Byeeee, cya!");
    }
}

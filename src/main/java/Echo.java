import java.util.Scanner;
import java.util.ArrayList;

public class Echo {
    public static void main(String[] args) {
        String logo = "Echo";
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Hello! I'm " + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("What can I do for you?");

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                int index = 1;
                for (String task : list) {
                    System.out.println(index + ". " + task);
                    index++;
                }
            } else {
                list.add(command);
                System.out.println("added: " + command);
            }
        }
        System.out.println("Byeeee, cya!");
    }
}

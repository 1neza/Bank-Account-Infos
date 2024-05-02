import java.util.Scanner;
import java.io.IOException;
import java.net.URL;

public class BankAccountInfo {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String bankAccountDigits;

            while (true) {
                System.out.print("Enter the first three digits of your bank account (or 'exit' to quit): ");
                bankAccountDigits = scanner.nextLine();

                if (bankAccountDigits.equalsIgnoreCase("exit")) {
                    break;
                }

                // Load the contents of the file from the URL
                URL url = new URL("https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt");
                Scanner fileScanner = new Scanner(url.openStream());
                String line;
                String bankName = null;

                // Find the bank number and bank name based on the provided bank account digits
                while (fileScanner.hasNextLine()) {
                    line = fileScanner.nextLine();
                    if (line.startsWith(bankAccountDigits)) {
                        String[] parts = line.split("\\s+");
                        bankName = parts[1];
                        break;
                    }
                }

                // Print the bank name
                if (bankName != null) {
                    System.out.println("You have an account in " + bankName);
                } else {
                    System.out.println("No bank information found for the provided bank account digits.");
                }

                // Close the scanner
                fileScanner.close();
            }

            // Close the input scanner
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
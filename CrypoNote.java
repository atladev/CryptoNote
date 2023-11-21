import java.util.Scanner;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.util.Base64;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CryptoNote {

    private static Key secretKey;
    private static final String ACCESS_PASSWORD = "password123";
    private static StringBuilder notes = new StringBuilder();

    public static void main(String[] args) {
        Main.initiateApp();
    }

    public static void initiateApp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to CryptoNote!");

        // Request the access password
        System.out.println("Enter the access password:");
        String enteredPassword = scanner.nextLine();
        
        // Verify the access password
        if (!verifyPassword(enteredPassword)) {
        System.out.println("Incorrect password. Exiting the application.");
        System.exit(0);
        }

        // Generate secret key for encryption
        generateSecretKey();

        // Call the main menu
        mainMenu(scanner);
    }
    
    private static boolean verifyPassword(String enteredPassword) {
        return enteredPassword.equals(ACCESS_PASSWORD);
    }

    private static void mainMenu(Scanner scanner) {
        System.out.println("\nChoose an option:");
        System.out.println("1. Create note");
        System.out.println("2. View notes");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the pending newline

        switch (choice) {
            case 1:
                createNote(scanner);
                break;
            case 2:
                viewNotes();
                break;
            case 3:
                System.out.println("Exiting the application. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }

        // After the choice, display the main menu again
        mainMenu(scanner);
    }

    private static void generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            secretKey = keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createNote(Scanner scanner) {
        System.out.println("Enter your note:");
        scanner.nextLine(); // Consume the pending newline
        String noteText = scanner.nextLine();

        // Adding date and time to the note
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = now.format(formatter);

        // Encrypt the note
        byte[] encryptedNote = encrypt((dateTime + "\n" + noteText).getBytes());

        // Store the encrypted note
        notes.append(Base64.getEncoder().encodeToString(encryptedNote)).append("\n");

        System.out.println("Note created successfully!");
    }

    private static void viewNotes() {
        System.out.println("\nNotes:");

        // Decrypt and display the notes
        String[] notesArray = notes.toString().split("\n");
        for (String encryptedNote : notesArray) {
            if (!encryptedNote.isEmpty()) {
                byte[] decryptedNote = decrypt(Base64.getDecoder().decode(encryptedNote));
                System.out.println(new String(decryptedNote));
            }
        }
    }

    private static byte[] encrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] decrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

import java.util.Scanner;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.util.Base64;

public class CryptoNote {

    private static Key chaveSecreta;
    private static StringBuilder notas = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to CryptoNote!");


        gerarChaveSecreta(); // Generate the secret key for encryption

        while (true) {
            System.out.println("\nchoose an option:");
            System.out.println("1. Create note");
            System.out.println("2. My notes");
            System.out.println("3. Exit");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    criarNota(scanner);
                    break;
                case 2:
                    visualizarNotas();
                    break;
                case 3:
                    System.out.println("Exiting the app. See you later!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    private static void gerarChaveSecreta() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            chaveSecreta = keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void criarNota(Scanner scanner) {
        System.out.println("Digite sua nota:");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String textoNota = scanner.nextLine();

        // Criptografar a nota
        byte[] notaCriptografada = criptografar(textoNota.getBytes());

        // Armazenar nota criptografada
        notas.append(Base64.getEncoder().encodeToString(notaCriptografada)).append("\n");

        System.out.println("Nota criada com sucesso!");
    }
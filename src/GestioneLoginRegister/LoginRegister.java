package GestioneLoginRegister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginRegister {
    BufferedReader br;
    private ArrayList<User> users;
    User currentUser;

    public LoginRegister() {
        br = new BufferedReader(new InputStreamReader(System.in));
        this.users = new ArrayList<>();
        users.add(new User("admin", "adminPassword", "admin"));
        this.currentUser = null;
    }

    public User displayMenu() {

        while (true) {
            System.out.println("Benvenuto!");
            System.out.println("1. Login");
            System.out.println("2. Registrazione");
            System.out.println("3. Esci");
            System.out.println("Scegli l'operazione: ");

            int scelta = 0;
            while(true){
                try {
                    scelta = Integer.parseInt(br.readLine());
                    while (scelta < 1 || scelta > 3) {
                        System.err.println("ERRORE: Inserire un numero tra 1 e 3.");
                        System.out.println("\nScegli l'operazione: ");
                        scelta = Integer.parseInt(br.readLine());
                    }
                    break;
                } catch (NumberFormatException | IOException e) {
                    System.err.println("ERRORE: Inserire un numero valido.");
                    System.out.println("\nScegli l'operazione: ");
                }
            }

            switch (scelta) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Arrivederci!");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Scelta non valida. Riprova.\n");
            }

            if (currentUser != null) {
                return currentUser;
            }
        }
    }

    private User login() {
        System.out.println("\nInserisci username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        System.out.println("Inserisci password: ");
        String password = scanner.nextLine();

        User user = getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login effettuato con successo!\n");

            if (user.getRole().equals("admin")) {
                adminMenu();
            } else {
                userMenu();
            }
            return currentUser = user;
        } else {
            System.err.println("Credenziali non valide. Riprova.\n");
            logout();
            return null;
        }
    }

    private User register() {
        System.out.println("\nInserisci username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        // Verifica se l'username è già presente
        if (getUserByUsername(username) != null || username.equalsIgnoreCase("admin")) {
            System.err.println("Username già in uso o non consentito. Riprova.\n");
            logout();
            return null;
        }

        System.out.println("Inserisci password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password, "user");
        users.add(newUser);

        System.out.println("Registrazione completata con successo!\n");
        logout();
        return null;
    }

    private void adminMenu() {
        System.out.println("Benvenuto Admin!\n");
    }

    private void userMenu() {
        System.out.println("Benvenuto Utente!\n");
    }

    private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User u) {
        this.currentUser = u;
    }

    public void logout() {
        this.currentUser = null;
    }
}

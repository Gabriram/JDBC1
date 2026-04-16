import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        GuestManager gManager = new GuestManager();
        ReservationManager rManager = new ReservationManager();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Menu Principale ===");
            System.out.println("1. Visualizza tutte le prenotazioni");
            System.out.println("2. Visualizza tutti gli ospiti");
            System.out.println("3. Aggiungi nuovo ospite");
            System.out.println("4. Crea prenotazione");
            System.out.println("5. Visualizza ospiti per stanza");
            System.out.println("6. Esci");
            System.out.print("Scegli un'opzione: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> rManager.getAllReservations();
                case 2 -> gManager.getAllGuests();
                case 3 -> {
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Telefono: ");
                    String phone = scanner.nextLine();
                    gManager.insertGuest(name, email, phone);
                }
                case 4 -> rManager.createReservation();
                case 5 -> {
                    System.out.print("Numero stanza: ");
                    int room = scanner.nextInt();
                    rManager.getOspitiRoom(room);
                }
                case 6 -> running = false;
                default -> System.out.println("Opzione non valida");
            }
        }

        scanner.close();

    }
}
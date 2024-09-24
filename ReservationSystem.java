import java.util.*;  // This import should always be at the top of the file

// Main class for the reservation system
public class ReservationSystem {

    static Scanner sc = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>();  // Stores user login info
    static List<Reservation> reservations = new ArrayList<>();  // Stores reservation data
    static int pnrCounter = 1000;  // Keeps track of PNR number

    public static void main(String[] args) {
        // Predefined users for login
        users.put("admin", "admin123");
        users.put("user", "user123");

        System.out.println("Welcome to the Online Reservation System!");

        if (login()) {
            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Reserve a Ticket");
                System.out.println("2. Cancel a Ticket");
                System.out.println("3. View All Reservations");
                System.out.println("4. Logout");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        reserveTicket();
                        break;
                    case 2:
                        cancelTicket();
                        break;
                    case 3:
                        viewAllReservations();
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            }
        } else {
            System.out.println("Login failed. Exiting system...");
        }
    }

    // Method to login users
    public static boolean login() {
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    // Method to reserve a ticket
    public static void reserveTicket() {
        sc.nextLine();  // Consume newline
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = sc.nextLine();
        System.out.print("Enter class type: ");
        String classType = sc.nextLine();
        System.out.print("Enter journey date (dd/mm/yyyy): ");
        String journeyDate = sc.nextLine();
        System.out.print("Enter 'From' location: ");
        String from = sc.nextLine();
        System.out.print("Enter 'To' destination: ");
        String to = sc.nextLine();

        Reservation newReservation = new Reservation(name, trainNumber, classType, journeyDate, from, to, pnrCounter++);
        reservations.add(newReservation);
        System.out.println("Ticket reserved successfully! Your PNR number is: " + newReservation.getPnr());
    }

    // Method to cancel a ticket
    public static void cancelTicket() {
        System.out.print("Enter your PNR number to cancel: ");
        int pnr = sc.nextInt();
        boolean found = false;

        for (Reservation r : reservations) {
            if (r.getPnr() == pnr) {
                reservations.remove(r);
                System.out.println("Reservation cancelled successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("PNR not found.");
        }
    }

    // Method to view all reservations
    public static void viewAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation r : reservations) {
                System.out.println(r);
            }
        }
    }
}

// Reservation class to represent a reservation
class Reservation {
    private String name;
    private String trainNumber;
    private String classType;
    private String journeyDate;
    private String from;
    private String to;
    private int pnr;

    public Reservation(String name, String trainNumber, String classType, String journeyDate, String from, String to, int pnr) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.journeyDate = journeyDate;
        this.from = from;
        this.to = to;
        this.pnr = pnr;
    }

    public int getPnr() {
        return pnr;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + ", Name: " + name + ", Train: " + trainNumber + ", Class: " + classType + 
               ", Date: " + journeyDate + ", From: " + from + ", To: " + to;
    }
}

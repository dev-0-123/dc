import java.rmi.Naming;
import java.util.Scanner;

public class HotelClient {
    public static void main(String[] args) {
        try {
            HotelServerInterface hotelServer = (HotelServerInterface) Naming.lookup("rmi://localhost/HotelServer");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Book Room | 2. Cancel Booking | 3. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                if (choice == 3) break;

                System.out.print("Enter guest name: ");
                String guestName = scanner.next();

                if (choice == 1) {
                    System.out.print("Enter room number (1-10): ");
                    int roomNumber = scanner.nextInt();
                    System.out.println(hotelServer.bookRoom(guestName, roomNumber) ? "Room booked!" : "Booking failed.");
                } else if (choice == 2) {
                    System.out.print("Enter room number to cancel: ");
                    int roomNumber = scanner.nextInt();
                    System.out.println(hotelServer.cancelBooking(guestName, roomNumber) ? "Booking canceled!" : "Cancellation failed.");
                } else {
                    System.out.println("Invalid choice.");
                }
            }
            System.out.println("Goodbye!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
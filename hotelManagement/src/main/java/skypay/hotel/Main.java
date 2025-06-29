package skypay.hotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Jansi imports for colored output in terminal
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class Main {

    // Helper to parse dates with only year, month, day as required
    // Format: dd/MM/yyyy
    private static Date parseDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false); // Strict parsing
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString + ". Use dd/MM/yyyy.", e);
        }
    }

    public static void main(String[] args) {
        // Force Jansi output even inside IDE consoles
        System.setProperty("jansi.force", "true");
        // Initialize Jansi for console colors
        AnsiConsole.systemInstall();

        Service hotelService = new Service();

        // Initialize Rooms
        AnsiConsole.out().println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("\n--- Initializing Rooms ---").reset());
        hotelService.setRoom(1, RoomType.STANDARD_SUITE, 1000);
        hotelService.setRoom(2, RoomType.JUNIOR_SUITE, 2000);
        hotelService.setRoom(3, RoomType.MASTER_SUITE, 3000);
        AnsiConsole.out().println(Ansi.ansi().fg(Ansi.Color.GREEN).a("✔ Rooms initialized").reset());
        hotelService.printAll();
        AnsiConsole.out().println();

        // Initialize Users
        AnsiConsole.out().println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("--- Initializing Users ---").reset());
        hotelService.setUser(1, 5000);
        hotelService.setUser(2, 10000);
        AnsiConsole.out().println(Ansi.ansi().fg(Ansi.Color.GREEN).a("✔ Users initialized").reset());
        hotelService.printAllUsers();
        AnsiConsole.out().println();

        AnsiConsole.out().println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("--- Running Test Cases ---").reset());

        // Test cases
        attemptBooking(hotelService, 1, 2, "30/06/2026", "07/07/2026");
        attemptBooking(hotelService, 1, 2, "07/07/2026", "30/06/2026");
        attemptBooking(hotelService, 1, 1, "07/07/2026", "08/07/2026");
        attemptBooking(hotelService, 2, 1, "07/07/2026", "09/07/2026");
        attemptBooking(hotelService, 2, 3, "07/07/2026", "08/07/2026");

        // Modify room price
        AnsiConsole.out().println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("\n--- Calling setRoom(1, MASTER_SUITE, 10000) ---").reset());
        hotelService.setRoom(1, RoomType.MASTER_SUITE, 10000);
        AnsiConsole.out().println(Ansi.ansi().fg(Ansi.Color.GREEN).a("✔ Room 1 updated to MASTER_SUITE @ 10000 (old bookings unaffected)").reset());
        hotelService.printAll();
        AnsiConsole.out().println();

        // Final state
        AnsiConsole.out().println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("--- Final State ---").reset());
        hotelService.printAll();
        AnsiConsole.out().println();
        hotelService.printAllUsers();

        // Clean up
        AnsiConsole.systemUninstall();
    }

    private static void attemptBooking(Service service, int userId, int roomNumber, String in, String out) {
        Date checkIn = parseDate(in);
        Date checkOut = parseDate(out);
        AnsiConsole.out().println(Ansi.ansi()
                .fg(Ansi.Color.CYAN)
                .a("Attempting: User " + userId + " booking Room " + roomNumber + " from " + in + " to " + out)
                .reset()
        );
        try {
            service.bookRoom(userId, roomNumber, checkIn, checkOut);
            AnsiConsole.out().println(Ansi.ansi().fg(Ansi.Color.GREEN).a("✔ Booking successful for User " + userId).reset());
        } catch (Exception e) {
            AnsiConsole.err().println(Ansi.ansi().fg(Ansi.Color.RED).a("✖ Booking failed: ").a(e.getMessage()).reset());
        }
        service.printAllUsers();
        service.printAll();
    }
}






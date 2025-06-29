package skypay.hotel;

// Service.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID; // For unique booking IDs
import java.time.temporal.ChronoUnit; // For calculating days between dates
import java.time.ZoneId;
import java.time.LocalDate;

public class Service {

    // Store entities in ArrayLists as required
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>(); // To store all bookings

    // Helper to find a room by ID
    private Room findRoomById(int roomNumber) {
        for (Room room : rooms) {
            if (room.getId() == roomNumber) {
                return room;
            }
        }
        return null; // Room not found
    }

    // Helper to find a user by ID
    private User findUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null; // User not found
    }

    // Helper to check room availability (considering only year, month, day)
    // This is a simplified check. For real systems, we'd need more robust date handling.
    private boolean isRoomFree(int roomNumber, Date checkin, Date checkOut) {
        // Convert Date to LocalDate for easier comparison (ignoring time)
        LocalDate localCheckIn = checkin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCheckOut = checkOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Ensure checkOut is after checkIn
        if (localCheckOut.isBefore(localCheckIn) || localCheckOut.isEqual(localCheckIn)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }

        for (Booking booking : bookings) {
            if (booking.getBookedRoomId() == roomNumber) {
                LocalDate bookedCheckIn = booking.getCheckIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate bookedCheckOut = booking.getCheckOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // Check for overlap:
                // (StartA < EndB) && (EndA > StartB)
                boolean overlaps = (localCheckIn.isBefore(bookedCheckOut) && localCheckOut.isAfter(bookedCheckIn));

                if (overlaps) {
                    return false; // Room is not free during the requested period
                }
            }
        }
        return true; // Room is free
    }

    // Helper to calculate number of nights (considering only year, month, day)
    private long calculateNights(Date checkin, Date checkOut) {
        LocalDate localCheckIn = checkin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCheckOut = checkOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(localCheckIn, localCheckOut);
    }


    // --- Required Functions ---

//    [cite_start]// [cite: 19]
    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
//        [cite_start]// [cite: 12] Creates a room if it does not already exist.
        boolean found = false;
        for (Room room : rooms) {
            if (room.getId() == roomNumber) {
                // Room exists, update its type and price
                room.setType(roomType);
                room.setPricePerNight(roomPricePerNight);
                found = true;
                break;
            }
        }
        if (!found) {
            // Room does not exist, create a new one and add it
            rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
        }
        //Important: This function should NOT impact previously created bookings
        // This is handled by Booking storing a snapshot of Room details, not a reference.
    }


    public void setUser(int userId, int balance) {
//  Creates a user if it does not already exist.
        boolean found = false;
        for (User user : users) {
            if (user.getId() == userId) {
                // User exists, update balance
                user.setBalance(balance);
                found = true;
                break;
            }
        }
        if (!found) {
            // User does not exist, create a new one and add it
            users.add(new User(userId, balance));
        }
    }


    public void bookRoom(int userId, int roomNumber, Date checkin, Date checkOut) {
    //  A User can book a room for a specific period if he has enough balance for the specified period and the room is free on that period.

        User user = findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");// Handle Exceptions
        }

        Room room = findRoomById(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room with ID " + roomNumber + " not found.");
        }

        //  Calculate number of nights [cite: 17] (consider only year, month and day)
        long numberOfNights = calculateNights(checkin, checkOut);
        if (numberOfNights <= 0) {
            throw new IllegalArgumentException("Check-out date must be after check-in date."); //  Exceptions
        }

        long totalCost = (long) room.getPricePerNight() * numberOfNights;

        // Check balance
        if (user.getBalance() < totalCost) {
            throw new IllegalArgumentException("User " + userId + " has insufficient balance. Required: " + totalCost + ", Available: " + user.getBalance()); // [cite: 18] Handle Exceptions
        }

        // Check room availability
        if (!isRoomFree(roomNumber, checkin, checkOut)) {
            throw new IllegalArgumentException("Room " + roomNumber + " is not free for the period " + checkin + " to " + checkOut + "."); // [cite: 18] Handle Exceptions
        }

        // If all checks pass, proceed with booking
        int userBalanceBeforeBooking = user.getBalance();
        user.setBalance(user.getBalance() - (int) totalCost); //  If the booking is successful, the user balance is updated.

        // Create a Booking instance with a snapshot of Room and User details
        Booking newBooking = new Booking(
                UUID.randomUUID().toString(), // Generate a unique booking ID
                checkin,
                checkOut,
                (int) numberOfNights,
                room.getId(),
                room.getType(), // Snapshot of current room type
                room.getPricePerNight(), // Snapshot of current room price
                user.getId(),
                userBalanceBeforeBooking,
                user.getBalance() // User balance after this booking
        );
        bookings.add(newBooking);
        System.out.println("Booking successful: " + newBooking.getBookingId());
    }

    public void printAll() {
//  print all rooms data and bookings data both from the latest created to the oldest created.

        System.out.println("--- All Rooms (Latest to Oldest) ---");
        // Rooms are added sequentially, so reverse the list to print latest to oldest
        List<Room> reversedRooms = new ArrayList<>(rooms);
        Collections.reverse(reversedRooms);
        for (Room room : reversedRooms) {
            System.out.println(room);
        }

        System.out.println("\n--- All Bookings (Latest to Oldest) ---");
        List<Booking> reversedBookings = new ArrayList<>(bookings);
        Collections.reverse(reversedBookings);
        for (Booking booking : reversedBookings) {
        // The booking data should contain all the information about the room and user when the booking was done.
            System.out.println(
                    "Booking ID: " + booking.getBookingId() +
                            ", Check-in: " + booking.getCheckIn() +
                            ", Check-out: " + booking.getCheckOut() +
                            ", Nights: " + booking.getNumberOfNights() +
                            ", Booked Room ID: " + booking.getBookedRoomId() +
                            ", Booked Room Type: " + booking.getBookedRoomType() +
                            ", Booked Room Price/Night: " + booking.getBookedRoomPricePerNight() +
                            ", Booked User ID: " + booking.getBookedUserId() +
                            ", User Balance Before Booking: " + booking.getUserBalanceBeforeBooking() +
                            ", User Balance After Booking: " + booking.getUserBalanceAfterBooking()
            );
        }
    }

    public void printAllUsers() {
    //   prints all user data from the latest created to the oldest created.
        System.out.println("--- All Users (Latest to Oldest) ---");
        List<User> reversedUsers = new ArrayList<>(users);
        Collections.reverse(reversedUsers);
        for (User user : reversedUsers) {
            System.out.println(user);
        }
    }
}
package skypay.hotel;

// Booking.java
import java.util.Date;

public class Booking {
    private String bookingId;
    private Date checkIn;
    private Date checkOut;
    private int numberOfNights;

    // Snapshot of Room details at the time of booking
    private int bookedRoomId;
    private RoomType bookedRoomType;
    private int bookedRoomPricePerNight;

    // Snapshot of User details at the time of booking (at least their ID and initial balance used for calc)
    private int bookedUserId;
    private int userBalanceBeforeBooking; //we  Store this for auditing/debugging

    // To store the final user balance after booking
    private int userBalanceAfterBooking;

    public Booking(String bookingId, Date checkIn, Date checkOut, int numberOfNights,
                   int bookedRoomId, RoomType bookedRoomType, int bookedRoomPricePerNight,
                   int bookedUserId, int userBalanceBeforeBooking, int userBalanceAfterBooking) {
        this.bookingId = bookingId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfNights = numberOfNights;
        this.bookedRoomId = bookedRoomId;
        this.bookedRoomType = bookedRoomType;
        this.bookedRoomPricePerNight = bookedRoomPricePerNight;
        this.bookedUserId = bookedUserId;
        this.userBalanceBeforeBooking = userBalanceBeforeBooking;
        this.userBalanceAfterBooking = userBalanceAfterBooking;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public int getBookedRoomId() {
        return bookedRoomId;
    }

    public RoomType getBookedRoomType() {
        return bookedRoomType;
    }

    public int getBookedRoomPricePerNight() {
        return bookedRoomPricePerNight;
    }

    public int getBookedUserId() {
        return bookedUserId;
    }

    public int getUserBalanceBeforeBooking() {
        return userBalanceBeforeBooking;
    }

    public int getUserBalanceAfterBooking() {
        return userBalanceAfterBooking;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", numberOfNights=" + numberOfNights +
                ", bookedRoomId=" + bookedRoomId +
                ", bookedRoomType=" + bookedRoomType +
                ", bookedRoomPricePerNight=" + bookedRoomPricePerNight +
                ", bookedUserId=" + bookedUserId +
                ", userBalanceBeforeBooking=" + userBalanceBeforeBooking +
                ", userBalanceAfterBooking=" + userBalanceAfterBooking +
                '}';
    }
}

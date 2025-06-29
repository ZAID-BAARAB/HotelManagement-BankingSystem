package skypay.hotel;

// Room.java
public class Room {
    private int id; // Corresponds to roomNumber in setRoom
    private RoomType type;
    private int pricePerNight;

    public Room(int id, RoomType type, int pricePerNight) {
        this.id = id;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    public int getId() {
        return id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "Room{" +
                "ID=" + id +
                ", Type=" + type +
                ", Price/Night=" + pricePerNight +
                '}';
    }
}

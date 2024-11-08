public class Room {
    int number_of_beds;
    double price;
    String type;
    public Room(int number_of_beds, double price, String type) {
        this.number_of_beds = number_of_beds;
        this.price = price
    }

    @Override
    public String toString() {
        return "Room{" +
                "number_of_beds=" + number_of_beds +
                ", price=" + price;
    }
}

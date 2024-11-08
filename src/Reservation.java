import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Reservation {
    Client client;
    Date arrival_date;
    Date departure_date;
    ArrayList<Room> rooms;
    public Reservation(Client client, Date arrival_date, Date departure_date, ArrayList<Room> rooms) {
        this.client = client;
        this.arrival_date = arrival_date;
        this.departure_date = departure_date;
        this.rooms = rooms;
    }

}

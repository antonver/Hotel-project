import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Hotel {
    String name;
    ArrayList<Room> rooms;
    String address;
    String city;
    String country;
    ArrayList<Reservation> reservations;
    int number_of_stars;
    String telephone_number;
    String email;
    public Hotel(String name, Room[] rooms, String city, String country, int number_of_stars, String telephone_number,
                 String email, String address) {
        this.name = name;
        this.rooms = new ArrayList<>(Arrays.asList(rooms));
        this.city = city;
        this.country = country;
        this.reservations = new ArrayList<Reservation>();
        this.number_of_stars = number_of_stars;
        this.telephone_number = telephone_number;
        this.email = email;
        this.address = address;
    }

    public ArrayList<Room> find_available_rooms(Date arrival_date, Date departure_date){
        ArrayList<Room> rooms_occupied = new ArrayList<>();
        ArrayList<Room> all_rooms = new ArrayList<>(this.rooms);
        for (Reservation reservation: this.reservations){
            if (((reservation.departure_date.after(arrival_date) && (reservation.departure_date.before(departure_date)) || reservation.departure_date.equals(departure_date))) ||
            (reservation.arrival_date.equals(arrival_date)) || (reservation.arrival_date.after(arrival_date) &&  reservation.arrival_date.before(departure_date))){
                rooms_occupied.addAll(reservation.rooms);
            }
        }
        all_rooms.removeAll(rooms_occupied);
        return all_rooms;
    }

    public ArrayList<Room> find_correct_rooms(
                                              Date arrival_date,
                                              Date departure_date,
                                              int number_of_persons,
                                              double price,
                                              String room_type){
        ArrayList<Room> available_rooms = find_available_rooms(arrival_date, departure_date);
        ArrayList<Room> correct_rooms = new ArrayList<>();
        for (Room room: available_rooms){
            if (
                    number_of_persons == room.number_of_beds &&
                    Objects.equals(room.type.toLowerCase(), room_type.toLowerCase()) &&
                    room.price <= price
            ){
                correct_rooms.add(room);
            }
        }
        return correct_rooms;
    }

    @Override
    public String toString() {
        return this.name + ". Number of stars: " + this.number_of_stars;
    }
}

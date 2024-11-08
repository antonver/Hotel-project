import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){
        Room[] rooms = new Room[9];
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        String type;
        Date today = new Date();
        for (int i = 1; i < 10; i++){
            if(i == 1){
                type = "Simple";
            } else if (i==2) {
                type = "Couple";
            } else if ( i <= 5) {
                type = "Family";
            }else {
                type = "Luxury";
            }
            Room room = new Room(i, 50 * i, type);
            rooms[i-1] = room;
        }

        Hotel hotel1 = new Hotel("Hotel1", rooms, "Paris", "France", 5,"+33 891-060-52", "hotel1@gmail.com", "rue de Paris1");
        hotels.add(hotel1);
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Do you want to book the apartments in the hotel?");
            String answer = sc.nextLine();
            if (Objects.equals(answer.toLowerCase(), "yes")){
                System.out.println("To which country  do you want to go");
                String country = sc.nextLine();
                System.out.println("To which city  do you want to go");
                String city = sc.nextLine();
                System.out.println("Hotels available: ");
                for (Hotel hotel: hotels){
                    if (country.equalsIgnoreCase(hotel.country) && city.equalsIgnoreCase(hotel.city)){
                        System.out.println(hotel.toString());
                    }
                }
                System.out.println("Choose the hotel by input his name");
                String hotel_name = sc.nextLine();
                Hotel hotel_choosed = null;
                for (Hotel hotel: hotels){
                    if (Objects.equals(hotel.name, hotel_name)){
                        hotel_choosed = hotel;
                    }
                if (hotel_choosed == null){
                    System.out.println("The name of the hotel is not correct");
                    continue;
                } else{
                    System.out.println("Write the arrival date in this format dd-MM-yyyy");
                    String arrival_date = sc.nextLine();
                    System.out.println("Write the departure date in this format dd-MM-yyyy");
                    String departure_date = sc.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date arrival_date_ = null;
                    Date departure_date_ = null;
                    System.out.println("All rooms available");
                    try {
                        arrival_date_ = dateFormat.parse(arrival_date);
                        departure_date_ = dateFormat.parse(departure_date);
                    } catch (ParseException e) {
                        System.out.println("Mistake: " + e.getMessage());
                    }
                    if (arrival_date_.after(departure_date_)){
                        System.out.println("Arrival date must be before the departure date");
                        return;
                    }
                    if (arrival_date_.after(today) || arrival_date_.equals(today)){
                        System.out.println("Arrival date must from today");
                        return ;
                    }


                    ArrayList<Room> availavle_rooms = hotel_choosed.find_available_rooms(arrival_date_, departure_date_);
                    if (hotel_choosed != null && arrival_date_ != null && departure_date_ != null){
                        int index = 1;
                        for (Room room: availavle_rooms){

                            System.out.println(index + ". " + room.toString());
                            index++;
                        }
                    }
                    System.out.println("print yes if you dont need filtration\n" +
                            "print filter if you want to filter\n" +
                            "print not if you dont want to make the reservation"
                    );
                    answer = sc.nextLine();
                    if (Objects.equals(answer, "yes")){
                        System.out.println("Input your name");
                        String name = sc.nextLine();
                        System.out.println("Input your last name");
                        String last_name = sc.nextLine();
                        System.out.println("Input your age");
                        int age = sc.nextInt();
                        System.out.println(age);
                        System.out.println("Write the numbers of rooms which you want to reserve in the format '1 2 3'");
                        ArrayList<Room> choosed_rooms = new ArrayList<Room>();
                        sc.nextLine();
                        String rooms__ = sc.nextLine();
                        for (String num: rooms__.trim().split(" ")){
                            int num_ = Integer.parseInt(num);
                            choosed_rooms.add(availavle_rooms.get(num_ -1));
                        }
                        Client client = new Client(name, last_name, age);
                        Reservation reservation = new Reservation(client, arrival_date_, departure_date_, choosed_rooms);
                        hotel_choosed.reservations.add(reservation);
                    } else if (Objects.equals(answer, "filter")) {
                        System.out.println("Input your name");
                        String name = sc.nextLine();
                        System.out.println("Input your last name");
                        String last_name = sc.nextLine();
                        System.out.println("Input your age");
                        int age = sc.nextInt();
                        System.out.println("Write number of persons");
                        int number_of_persons = sc.nextInt();
                        System.out.println("Write the maximum price");
                        double price = sc.nextDouble();
                        System.out.println("Write the room type: Simple, Family, Luxury, Couple");
                        sc.nextLine();
                        String room_type = sc.nextLine();
                        ArrayList<Room> filtered_rooms =  hotel_choosed.find_correct_rooms(arrival_date_, departure_date_,number_of_persons,  price, room_type);
                        if (filtered_rooms.size() == 0){
                            System.out.println("There is no hotels with these qualities");
                            return;
                        }
                        int index = 1;
                        for (Room room: filtered_rooms){
                            System.out.println(index + ". " + room.toString());
                            index++;
                        }
                        System.out.println("Write the numbers of rooms which you want to reserve in the format '1 2 3'");
                        String rooms__ = sc.nextLine();
                        ArrayList<Room> choosed_rooms = new ArrayList<Room>();
                        for (String num: rooms__.trim().split(" ")){
                            int num_ = Integer.parseInt(num);
                            choosed_rooms.add(filtered_rooms.get(num_ - 1));
                        }
                        Client client = new Client(name, last_name, age);
                        Reservation reservation = new Reservation(client, arrival_date_, departure_date_, choosed_rooms);
                        hotel_choosed.reservations.add(reservation);
                    } else{
                        return;
                    }
                }
                }
                System.out.println(hotels.get(0).reservations);
            } else{
                return;
            }

        }

        }
    }

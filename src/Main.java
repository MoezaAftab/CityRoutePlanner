import graph.Graph;
import models.Location;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Graph city = new Graph();

        Location hospital = new Location("Hospital");
        Location school = new Location("School");
        Location market = new Location("Market");
        Location airport = new Location("Airport");

        city.addLocation(hospital);
        city.addLocation(school);
        city.addLocation(market);
        city.addLocation(airport);


        city.addRoad(hospital, school, 5);
        city.addRoad(hospital, market, 2);
        city.addRoad(market, school, 1);
        city.addRoad(school, airport, 3);
           
    Scanner input = new Scanner(System.in);
    
    System.out.println();
System.out.println("       CITY ROUTE PLANNER");
System.out.println("........................................");
System.out.println("Welcome!");
System.out.println("Find the shortest routes across the city.");
System.out.println(".........................................");int choice;

do {
System.out.println("\n");
System.out.println(" MAIN MENU");
System.out.println("..............................");
System.out.println("1. Display City Map");
System.out.println("2. Find Shortest Route");
System.out.println("3. BFS Traversal");
System.out.println("4. DFS Traversal");
System.out.println("5. Exit");
System.out.print("Enter your choice: ");
choice = input.nextInt();
input.nextLine();

switch (choice) {

    case 1:{
        city.displayCity();
        break;}

    case 2:{
        System.out.println("\n.........SHORTEST ROUTE..........");
        System.out.println("\n.........Available Locations...........");
        city.displayLocations();
        Location start = getLocationFromUser(
        city,
        input,
        "Enter starting location: ");

Location destination = getLocationFromUser(
        city,
        input,
        "Enter destination: ");

if (start != null && destination != null) {

    city.dijkstra(start, destination);

}

        break;}

    case 3:{
        System.out.println("\n..........BFS TRAVERSAL...........");
        city.displayLocations();

    Location start = getLocationFromUser(city, input, "Enter starting location: ");
Location destination = getLocationFromUser(city, input, "Enter destination: ");

if (start != null && destination != null) {
    city.bfs(start, destination);
}

        break;}

    case 4:{
        System.out.println("\n...........DFS TRAVERSAL...........");
        city.displayLocations();

    Location start = getLocationFromUser(city, input, "Enter starting location: ");

if (start != null) {
    city.dfs(start);
}

        break;}

    case 5:{
        System.out.println("Thank you for using Smart City Navigation System!");

        break;}

    default:
        System.out.println("Invalid choice!");
}
} while (choice != 5);

input.close();}
public static Location getLocationFromUser(Graph city, Scanner input, String message) {

    System.out.print(message);

    String name = input.nextLine();

    Location location = city.findLocation(name);

    if (location == null) {

        System.out.println("Location not found.");

    }

    return location;
}
}

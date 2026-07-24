package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Stack;

import models.Edge;
import models.Location;

public class Graph {

    private HashMap<Location, ArrayList<Edge>> cityMap;

    public Graph() {
        cityMap = new HashMap<>();
    }
    public void addLocation(Location location) {

        cityMap.put(location, new ArrayList<>());

    }
    public void addRoad(Location from, Location to, int distance) {
    cityMap.get(from).add(new Edge(to, distance));
    cityMap.get(to).add(new Edge(from, distance)); 
    }
    public void displayCity() {

    System.out.println("\n.........DA CITY MAP.........");

    for (Location location : cityMap.keySet()) {

        System.out.println("\n" + location);

        for (Edge edge : cityMap.get(location)) {

            System.out.println("   -> " +
                    edge.getDestination() +
                    " (" + edge.getDistance() + " km)");

        }
    }

    }
    public void displayLocations() {

    System.out.println("\nAvailable Locations:");

    for (Location location : cityMap.keySet()) {

        System.out.println("• " + location);

    }
}
    public boolean bfs(Location start, Location destination) 
    {

    Queue<Location> queue = new LinkedList<>();

    HashSet<Location> visited = new HashSet<>();

    queue.offer(start);
    visited.add(start);
    while (!queue.isEmpty()) {

    Location current = queue.poll();
    if (current.equals(destination)) {
    
    System.out.println("Destination found: " + destination);

    return true;

}

System.out.println("Visiting: " + current);

    for (Edge edge : cityMap.get(current)) {

    Location neighbour = edge.getDestination();

    if (!visited.contains(neighbour)) {

        queue.offer(neighbour);
        visited.add(neighbour);

    }
}
}
System.out.println("Destination not found.");

return false;
}
public void dfs(Location current, HashSet<Location> visited) {

    visited.add(current);

    System.out.println("Visiting: " + current);
for (Edge edge : cityMap.get(current)) {

    Location neighbour = edge.getDestination();

    if (!visited.contains(neighbour)) {

        dfs(neighbour, visited);

    }
}
}
public void dfs(Location start) {

    HashSet<Location> visited = new HashSet<>();

    dfs(start, visited);

}
public void dfsIterative(Location start) {

    Stack<Location> stack = new Stack<>();

    HashSet<Location> visited = new HashSet<>();

    stack.push(start);

    while (!stack.isEmpty()) {

        Location current = stack.pop();

        if (!visited.contains(current)) {

            visited.add(current);

            System.out.println("Visiting: " + current);

            ArrayList<Edge> neighbours = cityMap.get(current);

            for (int i = neighbours.size() - 1; i >= 0; i--) {

                Location neighbour = neighbours.get(i).getDestination();  
                if (!visited.contains(neighbour)) {

                    stack.push(neighbour);

                }
            }
        }
    }
}
public void dijkstra(Location start, Location destination) {
HashMap<Location, Integer> distances = new HashMap<>();
HashMap<Location, Location> previous = new HashMap<>();
for (Location location : cityMap.keySet()) {

    distances.put(location, Integer.MAX_VALUE);

}
distances.put(start, 0);
PriorityQueue<Location> priorityQueue =
    new PriorityQueue<>((a, b) -> distances.get(a) - distances.get(b));
    priorityQueue.offer(start);
    while (!priorityQueue.isEmpty()) {

    Location current = priorityQueue.poll();

    for (Edge edge : cityMap.get(current)) {

        Location neighbour = edge.getDestination();

        int newDistance = distances.get(current) + edge.getDistance();

        if (newDistance < distances.get(neighbour)) {

            distances.put(neighbour, newDistance);
            previous.put(neighbour, current);

            priorityQueue.offer(neighbour);

        }
    }
}
/*
// Debugging output
System.out.println("\nShortest Distances:");

for (Location location : distances.keySet()) {

    System.out.println(location + " : " + distances.get(location));

}
    */
System.out.println("\nShortest Path:");

if (previous.get(destination) == null && !destination.equals(start)) {
    System.out.println("No path found.");
    return;
}
Stack<Location> path = new Stack<>();

Location current = destination;

while (current != null) {

    path.push(current);

    current = previous.get(current);

}

System.out.println("\nShortest Distance: "
        + distances.get(destination) + " km");
        System.out.print("Shortest Path: ");

while (!path.isEmpty()) {

    System.out.print(path.pop());

    if (!path.isEmpty()) {
        System.out.print(" -> ");
    }

}

System.out.println();
}
public Location findLocation(String name) {

    for (Location location : cityMap.keySet()) {

        if (location.getName().equalsIgnoreCase(name)) {

            return location;

        }

    }

    return null;
}
}

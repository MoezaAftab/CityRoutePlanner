package models;

public class Edge {

    private Location destination;
    private int distance;

    public Edge(Location destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }

    public Location getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return destination + " (" + distance + " km)";
    }
}


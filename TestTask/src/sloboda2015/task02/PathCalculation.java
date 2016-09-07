package sloboda2015.task02;

import java.util.*;

/**
 * Created Yurii Mikhailichenko on 15.08.2015
 * for Sloboda Studio. Test task #02
 *
 * This class is based on the Dijkstra Algorithm. The Dijkstra Algorithm is the algorithm for finding the shortest paths
 *      between nodes in a graph.
 * You can read more on http://en.wikipedia.org/wiki/Dijkstra's_algorithm.
 *
 * @since 1.0.2
 */


public class PathCalculation {

    private ArrayList<Road> roads;
    private HashSet<City> unVisitedCity;
    private HashSet<City> visitedCity;
    private HashMap<City, City> predecessors;
    private HashMap<City, Integer> distance;

    public PathCalculation(ArrayList<City> cities, ArrayList<Road> roads) {
        this.roads = roads;
    }

    public void dijkstra(City start, City finish) {
        unVisitedCity = new HashSet<>();
        visitedCity = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        unVisitedCity.add(start);
        distance.put(start, 0);
        while (unVisitedCity.size() > 0) {
            City nearestCity = getNearestCity(unVisitedCity);
            visitedCity.add(nearestCity);
            unVisitedCity.remove(nearestCity);
            findMinimalDistance(nearestCity);
        }
        for (City city : getPath(finish)) {
            System.out.println(city.getName());
        }
        printVenicle(finish);
    }

    private City getNearestCity(HashSet<City> cities) {
        City nearestCity = cities.iterator().next();
        for (City city : cities) {
            if (getDistance(city) < getDistance(nearestCity)) {
                nearestCity = city;
            }
        }
        return nearestCity;
    }

    /*
     * Get distance from the first city to other city.
     */
    private Integer getDistance(City city) {
        Integer distance = this.distance.get(city);
        if (distance == null) {
            /*
             * if path to city not found. Distance to city is infinity. For simplicity, this value is Integer.MAX_VALUE
             */
            return Integer.MAX_VALUE;
        }
        return distance;
    }

    /*
     * Get distance from one city to other city.
     */
    private int getDistance(City nearestCity, City city) {
        for (Road road : roads) {
            if (road.getStart().equals(nearestCity) && road.getDestination().equals(city)) {
                return road.getCost();
            } else if (road.getDestination().equals(nearestCity) && road.getStart().equals(nearestCity)) {
                return road.getCost();
            }
        }
        return Integer.MAX_VALUE;
    }

    /*
     * Find minimal distance for all neighbours of nearestCity.
     * Compare with existing value. If the existing value is less than the current value, update it.
     */
    private void findMinimalDistance(City nearestCity) {
        ArrayList<City> neighbours = getNeighbours(nearestCity);
        for (City city : neighbours) {
            if (getDistance(city) > getDistance(nearestCity) + getDistance(nearestCity, city)) {
                distance.put(city, getDistance(nearestCity) + getDistance(nearestCity, city));
                predecessors.put(city, nearestCity);
                unVisitedCity.add(city);
            }
        }
    }

    /*
     * Get neighbours for currentCity.
     */
    private ArrayList<City> getNeighbours(City currentCity) {
        ArrayList<City> neighbours = new ArrayList<>();
        for (Road road : roads) {
            if (road.getDestination().equals(currentCity) && !isVisited(road.getStart())) {
                neighbours.add(road.getStart());
            }
            /*
             * We can add second variant of neighbours (getStart() and getDestination()).
             * This will reduce the number of input data.
             *
             * if (road.getStart().equals(currentCity) && !isVisited(road.getDestination())) {
             *     neighbours.add(road.getDestination());
             * }
             */
        }
        return neighbours;
    }


    private boolean isVisited(City start) {
        return visitedCity.contains(start);
    }

    public LinkedList<City> getPath(City end) {
        LinkedList<City> path = new LinkedList<City>();
        City step = end;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }


    public void printVenicle(City finish) {
        if (distance.get(finish) > 200000) {
            System.out.println("Too large value! (" + distance.get(finish) + ")");
        }
        else if (distance.get(finish) != null) {
            System.out.println(distance.get(finish));
        } else {
            System.out.println("The path not found!");
        }
    }
}

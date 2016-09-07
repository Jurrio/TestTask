package sloboda2015.task02;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Yurii Mikhailichenko on 15.08.2015
 * for Sloboda Studio. Test task #02
 *
 * this application finds the paths of minimum cost between pairs of cities
 *
 * run Solution.main() for start work with app
 *
 * @since 1.0.1
 */
public class Solution {

    private static int id = 0;

    private ArrayList<City> cities;
    private ArrayList<Road> roads;
    private ArrayList<String[]> neighbours;
    private ArrayList<String> paths;
    private static boolean isHelp;

    public static boolean isHelp() {
        return isHelp;
    }

    public static void main(String[] args) throws IOException {
        Input input = new Input();
        /*
         * For enable help mode - uncomment the line below
         */
        isHelp = input.checkHelp();
        if (isHelp()) {
            System.out.print("Input number of tests: ");
        }
        /*
         * [the number of tests <= 10]
         */
        int numberOfTests = input.inputInteger(10);
        Solution[] solutions = new Solution[numberOfTests];
        for (int countTests = 0; countTests < numberOfTests; countTests++) {
            Solution solution = new Solution();
            solution.inputValues();
            solutions[countTests] = solution;
        }
        for (Solution solution : solutions) {
            //long start = System.currentTimeMillis();
            solution.output();
            //System.out.println("Time for test: " + (System.currentTimeMillis() - start) + "ms");
        }

    }


    /*
     * The method for input values. If you use manual input, you can enable helper. Don't use helper, if you use the autotests
     */
    public void inputValues() throws IOException {

        Input input = new Input();
        if (isHelp()) {
            System.out.print("Input number of cities: ");
        }
        /*
         * [the number of cities <= 10000]
         */
        int numberOfCities = input.inputInteger(10000);

        /*
         * For each test initialize ArrayLists cities, roads and neighbours.
         */
        cities = new ArrayList<>();
        roads = new ArrayList<>();
        neighbours = new ArrayList<>();
        for (int i = 0; i < numberOfCities; i++) {
            if (isHelp()) {
                System.out.print("Input city #" + (i + 1) + " from " + numberOfCities + ": ");
            }
            String name = input.inputCityName();
            cities.add(new City(nextID(), name));
            if (isHelp()) {
                System.out.print("Count neighbours of " + cities.get(i).getName() + ": ");
            }
            int numberOfNeibours = input.inputInteger(numberOfCities - 1);
            for (int j = 0; j < numberOfNeibours; j++) {
                if (isHelp()) {
                    System.out.print("Input neighbour" + (j + 1) + " for " + cities.get(i).getName() +
                            ". Index of city and the transportation cost: ");
                }
                String s = input.inputDoubleInteger(numberOfCities) + " " + (i);
                String[] strings = s.split(" ");
                neighbours.add(strings);
            }
        }

        addRoads();
        if (isHelp()) {
            System.out.print("The number of paths to find: ");
        }
        int numberOfPathToFind = input.inputInteger(100);
        paths = new ArrayList<>();
        for (int i = 0; i < numberOfPathToFind; i++) {
            if (isHelp()) {
                System.out.print("Path" + i + ": ");
            }
            paths.add(input.inputPath());
        }
    }

    public void output() {
        PathCalculation pathCalculation = new PathCalculation(cities, roads);
        /*
         * for all paths get start city, end city and calculate minimal path
         */
        for (String string : paths) {
            String[] path = string.split(" ");
            City start = null;
            City end = null;
            for (City city : cities) {
                if (city.getName().equals(path[0])) {
                    start = city;
                }
                if (city.getName().equals(path[1])) {
                    end = city;
                }
            }
            if (isHelp()) {
                System.out.print("The shortest path from " + start.getName() + " to " + end.getName() + " is ");
            }
            pathCalculation.dijkstra(start, end);
        }
    }


    /*
     * The each city get ID from 1 to @numberOfCities
     */
    private int nextID() {
        id++;
        return id;
    }

    /*
     * connect the cities by roads
     */
    private void addRoads() {
        for (String[] strings : neighbours) {
            int[] ints = new int[3];
            ints[0] = Integer.parseInt(strings[0]);         // the num is a (index+1) of destination city
            ints[1] = Integer.parseInt(strings[1]);         // the transportation cost
            ints[2] = Integer.parseInt(strings[2]);         // the index of start city
            roads.add(new Road(("Road_" + cities.get(ints[2]).getId() + "_" + cities.get((ints[0]) - 1).getId()),
                    cities.get(ints[2]), cities.get((ints[0]) - 1), ints[1]));
        }
    }
}

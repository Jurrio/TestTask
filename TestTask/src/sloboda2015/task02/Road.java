package sloboda2015.task02;

/**
 * Created by Yurii Mikhailichenko on 15.08.2015.
 * For Sloboda Studio. Test task #02
 *
 * This class describe road that connects the cities.
 *
 * @since 1.0.2
 */

public class Road {
    private City start;
    private City destination;
    private int cost;

    public Road(String name, City from, City to, int cost) {
        this.start = from;
        this.destination = to;
        this.cost = cost;
    }

    public City getStart() {
        return start;
    }

    public City getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }
}

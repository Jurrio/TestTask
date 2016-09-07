package sloboda2015.task02;

/**
 * Created by Yurii Mikhailichenko on 15.08.2015.
 * For Sloboda Studio. Test task #02
 *
 * This class describes city
 *
 * @since 1.0.1
 */

public class City {
    private int id;
    private String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return this.hashCode() == city.hashCode();

    }

    @Override
    public int hashCode() {
        return 1 * 31 + id != 0 ? id : 0;
    }
}

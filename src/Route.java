import java.util.Objects;

public class Route implements Comparable<Route> {
    private String id;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    private MyArrayList<String> locationPoints;

    public Route(String id, double distance, int popularity, boolean isFavorite, MyArrayList<String> locationPoints) {
        setId(id);
        setDistance(distance);
        setPopularity(popularity);
        setFavorite(isFavorite);
        setLocationPoints(locationPoints);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero");
        }
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        if (popularity < 0) {
            throw new IllegalArgumentException("Popularity cannot be negative");
        }
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        this.isFavorite = favorite;
    }

    public MyArrayList<String> getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(MyArrayList<String> locationPoints) {
        if (locationPoints == null || locationPoints.size() < 2) {
            throw new IllegalArgumentException("Location points cannot be null and must contain at least two points");
        }
        this.locationPoints = new MyArrayList<>(locationPoints.size());
        for (String point : locationPoints) {
            if (point == null || point.isEmpty()) {
                throw new IllegalArgumentException("Location points cannot contain null or empty values");
            }
            this.locationPoints.add(point);
        }
    }

    public void increasePopularity() {
        setPopularity(this.popularity + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(route.distance, distance) == 0 &&
                Objects.equals(locationPoints.get(0), route.locationPoints.get(0)) &&
                Objects.equals(locationPoints.get(locationPoints.size() - 1), route.locationPoints.get(route.locationPoints.size() - 1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationPoints.get(0), locationPoints.get(locationPoints.size() - 1), distance);
    }

    @Override
    public String toString() {
        return "Route : " +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", popularity=" + popularity +
                ", isFavorite=" + isFavorite +
                ", locationPoints=" + locationPoints;
    }

    @Override
    public int compareTo(Route o) {
        return 0;
    }
}

import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {
    @Override
    public int compare(Route r1, Route r2) {
        return r1.getId().compareTo(r2.getId());
    }
}

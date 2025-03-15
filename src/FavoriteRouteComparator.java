import java.util.Comparator;

public class FavoriteRouteComparator implements Comparator<Route> {
    @Override
    public int compare(Route r1, Route r2) {
        Integer firstdis = (int) r1.getDistance();
        Integer seconddis = (int) r2.getDistance();
        int result = firstdis.compareTo(seconddis);
        if (result != 0) {
            return result;
        }

        result = Integer.valueOf(r2.getPopularity()).compareTo(Integer.valueOf(r1.getPopularity()));
        return result;
    }
}






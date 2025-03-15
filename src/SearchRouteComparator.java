import java.util.Comparator;

public class SearchRouteComparator implements Comparator<Route> {
    @Override
    public int compare(Route r1, Route r2) {
        if (r1.isFavorite() && !r2.isFavorite()) {
            return -1;
        }
        if (!r1.isFavorite() && r2.isFavorite()) {
            return 1;
        }

        int firstLocSize = r1.getLocationPoints().size();
        int secondLocSize = r2.getLocationPoints().size();
        int distanceComparison = Integer.valueOf(firstLocSize).compareTo(secondLocSize); // croissant
        if (distanceComparison != 0) {
            return distanceComparison;
        }

        return Integer.valueOf(r2.getPopularity()).compareTo(r1.getPopularity()); // décroissant
    }
}
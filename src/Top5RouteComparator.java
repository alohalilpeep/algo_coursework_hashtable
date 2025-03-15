import java.util.Comparator;

public class Top5RouteComparator implements Comparator<Route> {
    @Override
    public int compare(Route r1, Route r2) {
        String str1 = (r1.isFavorite() ? "true" : "false");
        String str2 = (r2.isFavorite() ? "true" : "false");
        Integer condition0m1 = str1.length();
        Integer condition0m2 = str2.length();
        int result = condition0m1.compareTo(condition0m2);
        if (result != 0) {
            return result;
        }

        result = Integer.valueOf(r2.getPopularity()).compareTo(Integer.valueOf(r1.getPopularity()));
        if (result != 0) {
            return result;
        }

        Integer firstdis = (int) r1.getDistance();
        Integer seconddis = (int) r2.getDistance();
        result = firstdis.compareTo(seconddis);
        if (result != 0) {
            return result;
        }

        Integer firstloc = r1.getLocationPoints().size();
        Integer secondloc = r2.getLocationPoints().size();
        result = firstloc.compareTo(secondloc);
        return result;
    }
}

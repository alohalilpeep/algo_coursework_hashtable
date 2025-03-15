import java.util.Comparator;

public class PrintAllComparator implements Comparator<Route> {

    @Override
    public int compare(Route m1, Route m2) {
        String str1 = (m1.isFavorite() ? "true" : "false");
        String str2 = (m2.isFavorite() ? "true" : "false");
        Integer condition0m1 = str1.length();
        Integer condition0m2 = str2.length();
        int result = condition0m1.compareTo(condition0m2);
        return result;
    }
}
